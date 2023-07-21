#include<stdio.h>
#include<signal.h>
#include<unistd.h>

void sig_handler(int sigNum)
{
  if (sigNum == SIGINT)
    printf("SIGINT Interrupt Received\n");
}

int main(void)
{
  if (signal(SIGINT, sig_handler) == SIG_ERR) 
  {
     printf("\nSomething went wrong!!\n");
  }
  
  // Infinite Loop
  while(1) { 
    sleep(1);
  }
  
return 0;
}

