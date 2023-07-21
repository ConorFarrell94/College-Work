#include  <stdio.h>
#include  <sys/types.h>
#include <stdlib.h>

int main(int argc,char *argv[]) {
     char* ps_list[] = {"ps","aux", NULL};
     char* kill_list[] = {"kill","-9", 0, NULL};
     pid_t  pid;
     
     // Your error checking for parameters  goes here!!!!
     if (argc == 1) { // only command no params
        pid = fork();
        if (pid == 0) // child
        {
           printf ("Executing command ps...\n");
           execvp ("/bin/ps", ps_list);
        }
        else // parent
        {
          int status=0;
          wait(&status);
          printf ("Child process is returned with: %d.\n",status);
        }
     } else { // we got params, prob should do some error checking too!!!

            kill_list[2] = argv[2]; // assign the process id from the command line to the array
            if (pid == 0) // child
            {
            printf ("Executing command kill...\n");
            execvp ("/bin/kill", kill_list);
            }
            else // parent
            {
            int status=0;
            wait(&status);
            printf ("Child process is returned with: %d.\n",status);
            }
     }
}

