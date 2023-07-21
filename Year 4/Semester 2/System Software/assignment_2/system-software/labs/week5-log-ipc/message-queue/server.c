#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <mqueue.h>

int main()
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
    mq = mq_open("/dt228_queue", O_CREAT | O_RDONLY, 0644, &queue_attributes);
 
    do {
        ssize_t bytes_read;

        /* receive message */
        bytes_read = mq_receive(mq, buffer, 1024, NULL);

        buffer[bytes_read] = '\0';
        if (! strncmp(buffer, "exit", strlen("exit")))
        { terminate = 1; }
        else
        { printf("Received: %s\n", buffer); }
    } while (!terminate);

    mq_close(mq);
    mq_unlink("/dt228_queue");
    return 0;

}


