/*
 *
 * At 11:30 pm , there will be a check for the xml files that have been uploaded or 
 * files that have not been upladed
 *
 * At 1:00 am, the xml reports will be backed up from xml_upload_directory to 
 * dashboard_directory
 *
 * Directories are locked during transfer / backup
 * 
 * Kill -2 signal will enable the daemon to transfer / backup at any given time
 *
 * */


// Orphan Example
// The child process is adopted by init process, when parent process dies.
#include<stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <syslog.h>
#include <sys/stat.h>
#include <time.h>
#include "daemon_task.h"
#include <signal.h>

int main()
{
    time_t now;
    struct tm backup_time;
    time(&now);  /* get current time; same as: now = time(NULL)  */
    backup_time = *localtime(&now);
    backup_time.tm_hour = 1; 
    backup_time.tm_min = 0; 
    backup_time.tm_sec = 0;

    // Implementation for Singleton Pattern if desired (Only one instance running)

    // Create a child process      
    int pid = fork();
 
    if (pid > 0) {
        // if PID > 0 :: this is the parent
        // this process performs printf and finishes
        //sleep(10);  // uncomment to wait 10 seconds before process ends
        exit(EXIT_SUCCESS);
    } else if (pid == 0) {
       // Step 1: Create the orphan process
       
       // Step 2: Elevate the orphan process to session leader, to loose controlling TTY
       // This command runs the process in a new session
       if (setsid() < 0) { exit(EXIT_FAILURE); }

       // We could fork here again , just to guarantee that the process is not a session leader
       int pid = fork();
       if (pid > 0) {
          exit(EXIT_SUCCESS);
       } else {
       
          // Step 3: call umask() to set the file mode creation mask to 0
          umask(0);

          // Step 4: Change the current working dir to root.
          // This will eliminate any issues of running on a mounted drive, 
          // that potentially could be removed etc..
          if (chdir("/") < 0 ) { exit(EXIT_FAILURE); }

          // Step 5: Close all open file descriptors
          /* Close all open file descriptors */
          int x;
          for (x = sysconf(_SC_OPEN_MAX); x>=0; x--)
          {
             close (x);
          } 

          // Signal Handler goes here

          // Log file goes here
          // TODO create your logging functionality here to a file


          // Orphan Logic goes here!! 
          // Keep process running with infinite loop
          // When the parent finishes after 10 seconds, 
          // the getppid() will return 1 as the parent (init process)
          
	  struct tm check_uploads_time;
	  time(&now);  /* get current time; same as: now = time(NULL)  */
	  check_uploads_time = *localtime(&now);
	  check_uploads_time.tm_hour = 23; 
	  check_uploads_time.tm_min = 30; 
	  check_uploads_time.tm_sec = 0;
	
  	  while(1) {
	  	sleep(1);

		if(signal(SIGINT, sig_handler) == SIG_ERR) {
			syslog(LOG_ERR, "ERROR: daemon.c : SIG_ERR RECEIVED");
		} 

		
		//countdown to 23:30
	  	time(&now);
		double seconds_to_files_check = difftime(now,mktime(&check_uploads_time));
		//syslog(LOG_INFO, "%.f seconds until check for xml uploads", seconds_to_files_check);
		if(seconds_to_files_check == 0) {
			check_file_uploads();

			//change to tommorow's day
			update_timer(&check_uploads_time);
		}
				

		//countdown to 1:00
		time(&now);
		double seconds_to_transfer = difftime(now, mktime(&backup_time));
		//syslog(LOG_INFO, "%.f seconds until backup", seconds_to_files_check);
		if(seconds_to_transfer == 0) {
			lock_directories();
			collect_reports();	  
			backup_dashboard();
			sleep(30);
			unlock_directories();
			generate_reports();
			//after actions are finished, start counting to next day
			update_timer(&backup_time);
		}	
	  }
	}	
	closelog();
       return 0;
    }
}