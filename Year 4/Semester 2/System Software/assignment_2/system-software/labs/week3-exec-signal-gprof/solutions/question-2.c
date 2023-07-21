#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> /* for fork */
#include <sys/types.h> /* for pid_t */
#include <sys/wait.h> /* for wait */

int main()
{
    /*Spawn a child to run the program.*/
    pid_t pid=fork();
    if (pid==0) { /* child process */
        char *argv[] = { "/bin/ls", "-al", NULL };
        execv(argv[0], &argv[0]);
        exit(127); /* only if execv fails */
    }
    else { /* pid!=0; parent process */
        printf("The parent keeps doing whats its doign!!");
	    sleep(3);
    }
    return 0;
}
