#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <mqueue.h>
#include <time.h>

// int main()
void message_queue()
{
    mqd_t mq;
    struct mq_attr queue_attributes;
    char buffer[1024 + 1];
    int terminate = 0;

    /* set queue attributes */
    queue_attributes.mq_flags = 0;
    queue_attributes.mq_maxmsg = 10;
    queue_attributes.mq_msgsize = 1024;
    queue_attributes.mq_curmsgs = 0;

    /* create queue */
    mq = mq_open("/daemon_queue", O_CREAT | O_RDONLY, 0644, &queue_attributes);

    do
    {
        ssize_t bytes_read;

        // receive message from queue
        bytes_read = mq_receive(mq, buffer, 1024, NULL);
        buffer[bytes_read] = '\0';

        // if queue recieves a message with "successful" or "unsuccessful" in it, print it to the console and write it to the log file
        if (strstr(buffer, "successful") || strstr(buffer, "unsuccessful"))
        {
            FILE *logfile = fopen("log.txt", "a");
            printf("DAEMON RECIEVED: %s\n", buffer);

            // add timestamp to log message
            time_t current_time;
            char timestamp[20];
            struct tm *tm_info;
            time(&current_time);
            tm_info = localtime(&current_time);
            strftime(timestamp, 20, "%Y-%m-%d %H:%M:%S", tm_info);

            // write message to log file with timestamp and "DAEMON" tag at the start
            fprintf(logfile, "%s - DAEMON: %s\n", timestamp, buffer);
            fclose(logfile);
        }
    } while (!terminate);

    mq_close(mq);
    mq_unlink("/daemon_queue");
}
