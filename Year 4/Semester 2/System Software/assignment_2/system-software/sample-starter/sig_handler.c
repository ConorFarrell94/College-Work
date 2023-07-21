#include <unistd.h>
#include <syslog.h>
#include "daemon_task.h"
#include <signal.h>
#include <stdlib.h>

void sig_handler(int sigNum)
{
	if (sigNum == SIGINT) {
		syslog(LOG_INFO, "RECEIVED SIGNAL INTERRUPT, INITIATING BACKUP AND TRANSFER");
		lock_directories();
		collect_reports();
		backup_dashboard();
		sleep(30);
		unlock_directories();	
	}

}