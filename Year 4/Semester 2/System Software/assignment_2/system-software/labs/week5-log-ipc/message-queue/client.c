#include <stdio.h>
#include <string.h>
#include <mqueue.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
    mqd_t mq;
    char buffer[1024];

    /* open the message queue */
    mq = mq_open("/dt228_queue", O_WRONLY);

    printf("Send message to server (enter 'exit' to terminate):\n");

    do {
        printf(">> ");
        fflush(stdout);

        memset(buffer, 0, 1024);
        fgets(buffer, 1024, stdin);
        mq_send(mq, buffer, 1024, 0);

    } while (strncmp(buffer, "exit", strlen("exit")));

    mq_close(mq); 
    return 0;
}
