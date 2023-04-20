#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <syslog.h>
#include <mqueue.h>
#include <string.h>

void unlock_directories()
{
    int result;

    // Set permissions on /home/conor/plant_state/shared/
    result = chmod("/home/conor/plant_state/shared/", S_IRWXU | S_IRWXG | S_IRWXO);
    if (result != 0)
    {
        syslog(LOG_INFO, "Error: could not change permissions on /home/conor/plant_state/shared/\n");

        mqd_t mq;
        char buffer[1024];
        /* open the message queue */
        mq = mq_open("/daemon_queue", O_WRONLY);
        strcpy(buffer, "unlock_directories | unsuccessful");
        mq_send(mq, buffer, 1024, 0);
        mq_close(mq);
    }

    // Set permissions on /home/conor/plant_state/dashboard/reporting/
    result = chmod("/home/conor/plant_state/dashboard/reporting/", S_IRWXU | S_IRWXG | S_IRWXO);
    if (result != 0)
    {
        syslog(LOG_INFO, "Error: could not change permissions on /home/conor/plant_state/dashboard/reporting/\n");
        mqd_t mq;
        char buffer[1024];
        mq = mq_open("/daemon_queue", O_WRONLY);
        strcpy(buffer, "unlock_directories | unsuccessful");
        mq_send(mq, buffer, 1024, 0);
        mq_close(mq);
    }

    mqd_t mq;
    char buffer[1024];
    mq = mq_open("/daemon_queue", O_WRONLY);
    strcpy(buffer, "unlock_directories | successful");
    mq_send(mq, buffer, 1024, 0);
    mq_close(mq);

    syslog(LOG_INFO, "Permissions changed successfully (unlocked directories)\n");
}
