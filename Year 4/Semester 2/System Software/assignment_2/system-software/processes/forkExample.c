#include <stdio.h>
#include <unistd.h>

int main(int argc, char **argv)
{
    printf("Start\n");
    //fork();
    pid_t pid = fork();

    printf("\nMy PID is: %d", pid);

    if (pid == 0)
    {
	printf("\nHello from the child process!!\n\n\n");
    }
    else if (pid > 0)
    {
        printf("\nHello from the parent process!!\n\n\n");
    }
    else
    {
        printf("Something went wrong!!");
        return 1;
    }

    printf("End");

    return 0;
}
