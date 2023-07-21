#include  <stdio.h>
#include  <sys/types.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>



void sig_handler(int sigNum)
{
  if (sigNum == SIGTERM)
    printf("SIGINT Interrupt Received - File IO ain't working\n");
}

void  main(void)
{
     pid_t  pid;
     int ret; 
     int ppid;
     
     if (signal(SIGTERM, sig_handler) == SIG_ERR) 
     {
	    printf("\nSomething went wrong!!\n");
     } else {
        printf ("Child process is returned with: %d.\n",0);
     }

     pid = fork();

     if (pid == 0) // child
     {
        FILE *pFile;
        char buffer[256];
        ppid = getpid();

        pFile=fopen("logfile.txt", "a+");
        if(pFile==NULL) {
           perror("Error opening file.");
            kill(ppid , SIGTERM); 
        }
        else {
           char mode[] = "0777";
           char buf[100] = "logfile.txt";
           int i;
           i = strtol(mode, 0, 8);
           if (chmod (buf,i) < 0)
           {
              // error
              exit(1);
           }
           fprintf(pFile, "%s", "Some sample content here..");
        //    kill(ppid , SIGTERM); // uncomment to simulate error
        }

        fclose(pFile);
     } else // parent
     {
       // parent logic here
     }
}