// Orphan Example
// The child process is adopted by init process, when parent process dies.
#include<stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <syslog.h>
 
int main()
{
    // Implementation for Singleton Pattern if desired (Only one instance running)

    // Create a child process      
    int pid = fork();
 
    if (pid > 0) {
        // if PID > 0 :: this is the parent
        // this process performs printf and finishes
        printf("Parent process");
        sleep(10);  // uncomment to wait 10 seconds before process ends
        exit(EXIT_FAILURE);
    } else if (pid == 0) {
       // Step 1: Create the orphan process
       printf("Child process");
       
       // Step 2: Elevate the orphan process to session leader, to loose controlling TTY
       // This command runs the process in a new session
       if (setsid() < 0) { exit(EXIT_FAILURE); }

       // Step 3: call umask() to set the file mode creation mask to 0
       // This will allow the daemon to read and write files with the permissions/access required 
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

       /* Orphan Logic goes here!! 
          Keep process running with infinite loop
          When the parent finishes after 10 seconds, 
          the getppid() will return 1 as the parent (init process) */
       while(1) {
          sleep(1);
          printf("child 1: my parent is: %i\n", getppid());
       }
    }
 
    return 0;
}
