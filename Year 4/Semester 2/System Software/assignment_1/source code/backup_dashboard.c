#include <time.h>
#include <unistd.h>
#include <stdio.h>
#include <syslog.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>

#include <limits.h>
#include <string.h>
#include <dirent.h>
#include <errno.h>
#include <sys/stat.h>
#include <mqueue.h>

#define SOURCE_DIR "/home/conor/plant_state/dashboard/reporting/"
#define DEST_DIR "/home/conor/plant_state/backup/"

void backup_dashboard(void)
{

    DIR *dirp;
    struct dirent *dp;
    char *src_file_path, *dest_file_path;
    struct stat st;
    size_t path_max;

    mqd_t mq1;
    char buffer1[1024];
    /* open the message queue */
    mq1 = mq_open("/daemon_queue", O_WRONLY);
    strcpy(buffer1, "backup_dashboard | started");
    mq_send(mq1, buffer1, 1024, 0);
    mq_close(mq1);

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

    // Get the current date
    time_t t = time(NULL);
    struct tm *tm = localtime(&t);
    char date_str[20];
    strftime(date_str, sizeof(date_str), "%Y-%m-%d", tm);

    // Create the destination directory
    char dest_dir_path[path_max];
    snprintf(dest_dir_path, path_max, "%s/%s", DEST_DIR, date_str);
    if (mkdir(dest_dir_path, 0777) == -1)
    {
        syslog(LOG_ERR, "Error: Unable to create destination directory: %s\n", strerror(errno));
    }

    // Open the source directory
    dirp = opendir(SOURCE_DIR);
    if (dirp == NULL)
    {
        syslog(LOG_ERR, "Error: Unable to open source directory: %s\n", strerror(errno));

        mqd_t mq;
        char buffer[1024];
        mq = mq_open("/daemon_queue", O_WRONLY);
        strcpy(buffer, "backup_dashboard | unsuccessful | Unable to open source directory");
        mq_send(mq, buffer, 1024, 0);
        mq_close(mq);
    }

    // Loop through all files in the source directory
    while ((dp = readdir(dirp)) != NULL)
    {
        if (strcmp(dp->d_name, ".") == 0 || strcmp(dp->d_name, "..") == 0)
        {
            // Skip the "." and ".." directories
            continue;
        }

        // Construct the source and destination file paths
        snprintf(src_file_path, path_max, "%s/%s", SOURCE_DIR, dp->d_name);
        snprintf(dest_file_path, path_max, "%s/%s", dest_dir_path, dp->d_name);

        // Get information about the file
        if (stat(src_file_path, &st) == -1)
        {
            syslog(LOG_ERR, "Error: Unable to get information about file: %s\n", strerror(errno));
            continue;
        }

        if (S_ISREG(st.st_mode))
        {
            // The file is a regular file, so copy it to the destination directory
            FILE *src_file = fopen(src_file_path, "rb");
            if (src_file == NULL)
            {
                syslog(LOG_ERR, "Error: Unable to open source file: %s\n", strerror(errno));

                mqd_t mq;
                char buffer[1024];
                mq = mq_open("/daemon_queue", O_WRONLY);
                strcpy(buffer, "backup_dashboard | unsuccessful | Unable to open source file");
                mq_send(mq, buffer, 1024, 0);
                mq_close(mq);

                continue;
            }

            FILE *dest_file = fopen(dest_file_path, "wb");
            if (dest_file == NULL)
            {
                syslog(LOG_ERR, "Error: Unable to open destination file: %s\n", strerror(errno));
                mqd_t mq;

                char buffer[1024];
                mq = mq_open("/daemon_queue", O_WRONLY);
                strcpy(buffer, "backup_dashboard | unsuccessful | Unable to open destination file");
                mq_send(mq, buffer, 1024, 0);
                mq_close(mq);

                fclose(src_file);
                continue;
            }

            char buffer[1024];
            size_t bytes_read;

            while ((bytes_read = fread(buffer, 1, sizeof(buffer), src_file)) > 0)
            {
                fwrite(buffer, 1, bytes_read, dest_file);
            }

            fclose(src_file);
            fclose(dest_file);

            syslog(LOG_INFO, "(backup) Copied file: %s\n", dp->d_name);
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
    strcpy(buffer, "backup_dashboard | successful");
    mq_send(mq, buffer, 1024, 0);
    mq_close(mq);
}
