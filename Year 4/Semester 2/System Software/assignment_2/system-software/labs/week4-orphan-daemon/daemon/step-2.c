// Orphan Example
// The child process is adopted by init process, when parent process dies.
#include<stdio.h>
#include <stdlib.h>
 
int main()
{
    // Create a child process      
    int pid = fork();
 
    if (pid > 0) {
        // if PID > 0 :: this is the parent
        // this process performs printf and finishes
        printf("Parent process");
        sleep(10);  // uncomment to wait 10 seconds before process ends
        exit(EXIT_SUCCESS); // Kill the parent, needed to make orphan
    } else if (pid == 0) {
       // Step 1: Create the orphan process
       printf("Child process");
       
       // Step 2: Elevate the orphan process to session leader, to loose controlling TTY
       // This command runs the process in a new session
       if (setsid() < 0) { exit(EXIT_FAILURE); }

       // Orphan Logic goes here!! 
       // Keep process running with infinite loop
       // When the parent finishes after 10 seconds, 
       // the getppid() will return 1 as the parent (init process)
       while(1) {
          sleep(1);
          printf("child 1: my parent is: %i\n", getppid());
       }
    }
 
    return 0;
}
