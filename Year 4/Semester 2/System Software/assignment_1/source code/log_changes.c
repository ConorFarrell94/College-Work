#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/inotify.h>
#include <time.h>
#include <unistd.h>

#define EVENT_SIZE (sizeof(struct inotify_event))
#define BUF_LEN (1024 * (EVENT_SIZE + 16))

void log_changes()
{
    int length, i = 0;
    int fd;
    int wd;
    char buffer[BUF_LEN];

    fd = inotify_init();
    if (fd < 0)
    {
        perror("inotify_init");
        exit(EXIT_FAILURE);
    }

    wd = inotify_add_watch(fd, "/home/conor/plant_state/shared/", IN_MODIFY | IN_CREATE | IN_DELETE | IN_MOVED_FROM | IN_MOVED_TO);

    while (1)
    {
        i = 0;
        length = read(fd, buffer, BUF_LEN);
        if (length < 0)
        {
            perror("read");
            exit(EXIT_FAILURE);
        }

        while (i < length)
        {
            struct inotify_event *event = (struct inotify_event *)&buffer[i];

            if (event->mask & IN_CREATE)
            {
                time_t t;
                struct tm *tm_info;
                char timestamp[20];

                time(&t);
                tm_info = localtime(&t);
                strftime(timestamp, 20, "%Y-%m-%d %H:%M:%S", tm_info);

                FILE *fp = fopen("/home/conor/plant_state/dashboard/log.txt", "a");
                if (fp != NULL)
                {
                    fprintf(fp, "%s - %s - %s - created\n", timestamp, getenv("USER"), event->name);
                    fclose(fp);
                }
                else
                {
                    perror("fopen");
                }
            }
            else if (event->mask & IN_MOVED_TO)
            {
                if (!(event->mask & IN_ISDIR))
                {
                    time_t t;
                    struct tm *tm_info;
                    char timestamp[20];

                    time(&t);
                    tm_info = localtime(&t);
                    strftime(timestamp, 20, "%Y-%m-%d %H:%M:%S", tm_info);

                    FILE *fp = fopen("/home/conor/plant_state/dashboard/log.txt", "a");
                    if (fp != NULL)
                    {
                        fprintf(fp, "%s - %s - %s - moved\n", timestamp, getenv("USER"), event->name);
                        fclose(fp);
                    }
                    else
                    {
                        perror("fopen");
                    }
                }
            }
            else if (event->mask & IN_MODIFY)
            {
                time_t t;
                struct tm *tm_info;
                char timestamp[20];

                time(&t);
                tm_info = localtime(&t);
                strftime(timestamp, 20, "%Y-%m-%d %H:%M:%S", tm_info);

                FILE *fp = fopen("/home/conor/plant_state/dashboard/log.txt", "a");
                if (fp != NULL)
                {
                    fprintf(fp, "%s - %s - %s - modified\n", timestamp, getenv("USER"), event->name);
                    fclose(fp);
                }
                else
                {
                    perror("fopen");
                }
            }
            else if (event->mask & IN_MOVED_FROM)
            {
                if (!(event->mask & IN_ISDIR))
                {
                    time_t t;
                    struct tm *tm_info;
                    char timestamp[20];

                    time(&t);
                    tm_info = localtime(&t);
                    strftime(timestamp, 20, "%Y-%m-%d %H:%M:%S", tm_info);
                    FILE *fp = fopen("/home/conor/plant_state/dashboard/log.txt", "a");
                    if (fp != NULL)
                    {
                        fprintf(fp, "%s - %s - %s - moved\n", timestamp, getenv("USER"), event->name);
                        fclose(fp);
                    }
                    else
                    {
                        perror("fopen");
                    }
                }
            }
            else if (event->mask & IN_DELETE || event->mask & IN_DELETE_SELF)
            {
                if (!(event->mask & IN_ISDIR))
                {
                    time_t t;
                    struct tm *tm_info;
                    char timestamp[20];

                    time(&t);
                    tm_info = localtime(&t);
                    strftime(timestamp, 20, "%Y-%m-%d %H:%M:%S", tm_info);

                    FILE *fp = fopen("/home/conor/plant_state/dashboard/log.txt", "a");
                    if (fp != NULL)
                    {
                        fprintf(fp, "%s - %s - %s - deleted\n", timestamp, getenv("USER"), event->name);
                        fclose(fp);
                    }
                    else
                    {
                        perror("fopen");
                    }
                }
            }

            i += EVENT_SIZE + event->len;
        }
    }

    (void)inotify_rm_watch(fd, wd);
    (void)close(fd);
}
