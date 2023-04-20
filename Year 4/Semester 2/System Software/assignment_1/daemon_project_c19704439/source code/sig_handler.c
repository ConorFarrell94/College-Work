#include <unistd.h>
#include <syslog.h>
#include "daemon_task.h"
#include <signal.h>
#include <stdlib.h>
#include <mqueue.h>
#include <string.h>
#include <stdio.h>

mqd_t mq;
char buffer[1024];

void sig_handler(int sigNum)
{
	if (sigNum == SIGINT)
	{
		syslog(LOG_INFO, "RECEIVED SIGNAL INTERRUPT, INITIATING BACKUP AND TRANSFER");

		// add timestamp to log message
		time_t current_time;
		char timestamp[20];
		struct tm *tm_info;
		time(&current_time);
		tm_info = localtime(&current_time);
		strftime(timestamp, 20, "%Y-%m-%d %H:%M:%S", tm_info);

		mq = mq_open("/daemon_queue", O_WRONLY);
		strcpy(buffer, "manual backup & transfer | started | successful\n");
		mq_send(mq, buffer, sizeof(buffer), 0);
		mq_close(mq);

		lock_directories();
		manual_backup();
		backup_dashboard();
		unlock_directories();

		mq = mq_open("/daemon_queue", O_WRONLY);
		strcpy(buffer, "\nmanual backup & transfer | ended | successful\n");
		mq_send(mq, buffer, sizeof(buffer), 0);
		mq_close(mq);

	}
}
