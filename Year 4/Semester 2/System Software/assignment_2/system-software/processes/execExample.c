#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* for fork */
#include <sys/types.h> /* for pid_t */
#include <sys/wait.h> /* for wait */

int main()
{

   char *argv[]={"echo","Echo is running now....",NULL};
   //argv[0] is the command to run: echo
   //argv[1] text to be echoed
   //argv[2] null, needed!!
   execv("/bin/echo",argv);
   printf("Im the last thing in this program to output!!"); 

   return 0;
}
