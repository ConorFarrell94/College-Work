#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <syslog.h>
#include <limits.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <errno.h>
#include <sys/stat.h>
#include <mqueue.h>

#define SOURCE_DIR "/home/conor/plant_state/shared/"
#define DEST_DIR "/home/conor/plant_state/dashboard/reporting/"

void collect_reports(char **xml_files, int num_xml_files)
{
    DIR *dirp;
    struct dirent *dp;
    char *src_file_path, *dest_file_path;
    struct stat st;
    size_t path_max;

    // Determine the maximum file path length
    path_max = pathconf(SOURCE_DIR, _PC_PATH_MAX);
    if (path_max == -1)
    {
        // If the maximum length cannot be determined, use a default value
        path_max = 4096;
    }

    // Allocate memory for the file paths
    src_file_path = malloc(path_max);
    dest_file_path = malloc(path_max);
    if (src_file_path == NULL || dest_file_path == NULL)
    {
        syslog(LOG_ERR, "Error: Unable to allocate memory for file paths.\n");
    }

    // Open the source directory
    dirp = opendir(SOURCE_DIR);
    if (dirp == NULL)
    {
        syslog(LOG_ERR, "Error: Unable to open source directory: %s\n", strerror(errno));
    }

    // Loop through all files in the source directory
    while ((dp = readdir(dirp)) != NULL)
    {
        if (strcmp(dp->d_name, ".") == 0 || strcmp(dp->d_name, "..") == 0)
        {
            // Skip the "." and ".." directories
            continue;
        }

        int found = 0;
        // Loop through xml_files array to check if the current file name matches
        for (int i = 0; i < num_xml_files; i++)
        {
            if (strcmp(dp->d_name, xml_files[i]) == 0)
            {
                found = 1;
                break;
            }
        }

        if (found == 0)
        {
            // The file name does not match any xml_files, skip it
            continue;
        }

        // Construct the source and destination file paths
        snprintf(src_file_path, path_max, "%s/%s", SOURCE_DIR, dp->d_name);
        snprintf(dest_file_path, path_max, "%s/%s", DEST_DIR, dp->d_name);

        // Get information about the file
        if (stat(src_file_path, &st) == -1)
        {
            syslog(LOG_ERR, "Error: Unable to get information about file: %s\n", strerror(errno));
            continue;
        }

        if (S_ISREG(st.st_mode))
        {
            // The file is a regular file, so move it to the destination directory
            if (rename(src_file_path, dest_file_path) == -1)
            {
                syslog(LOG_ERR, "Error: Unable to move file: %s\n", strerror(errno));

                mqd_t mq;
                char buffer[1024];
                mq = mq_open("/daemon_queue", O_WRONLY);
                strcpy(buffer, "collect_reports | unsuccessful");
                mq_send(mq, buffer, 1024, 0);
                mq_close(mq);
            }
            else
            {
                syslog(LOG_INFO, "Moved file: %s\n", dp->d_name);
            }
        }
        else
        {
            // The file is not a regular file, so skip it
            syslog(LOG_INFO, "Skipping file: %s (not a regular file)\n", dp->d_name);
        }
    }

    // Close the source directory
    closedir(dirp);

    // Free the memory used for the file paths
    free(src_file_path);
    free(dest_file_path);

    mqd_t mq;
    char buffer[1024];
    mq = mq_open("/daemon_queue", O_WRONLY);
    strcpy(buffer, "collect_reports | successful");
    mq_send(mq, buffer, 1024, 0);
    mq_close(mq);
}
