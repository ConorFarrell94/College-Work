#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <syslog.h>
#include <mqueue.h>
#include <string.h>

void lock_directories()
{
    // printf("lock directory functionality should go here. fork/chmod will be used here to change permissions");
    int result;

    // Set permissions on /home/conor/plant_state/shared/
    result = chmod("/home/conor/plant_state/shared/", S_IRUSR | S_IWUSR);
    if (result != 0)
    {
        syslog(LOG_INFO, "Error: could not change permissions on /home/conor/plant_state/shared/\n");

        mqd_t mq;
        char buffer[1024];
        /* open the message queue */
        mq = mq_open("/daemon_queue", O_WRONLY);
        strcpy(buffer, "lock_directories | unsuccessful");
        mq_send(mq, buffer, 1024, 0);
        mq_close(mq);
    }

    // Set permissions on /home/conor/plant_state/dashboard/reporting/
    result = chmod("/home/conor/plant_state/dashboard/reporting/", S_IRUSR | S_IWUSR);
    if (result != 0)
    {
        syslog(LOG_INFO, "Error: could not change permissions on /home/conor/plant_state/dashboard/reporting/\n");

        mqd_t mq;
        char buffer[1024];
        mq = mq_open("/daemon_queue", O_WRONLY);
        strcpy(buffer, "lock_directories | unsuccessful");
        mq_send(mq, buffer, 1024, 0);
        mq_close(mq);
    }

    mqd_t mq;
    char buffer[1024];
    mq = mq_open("/daemon_queue", O_WRONLY);
    strcpy(buffer, "lock_directories | successful");
    mq_send(mq, buffer, 1024, 0);
    mq_close(mq);

    syslog(LOG_INFO, "Permissions changed successfully (locked directories)\n");
}