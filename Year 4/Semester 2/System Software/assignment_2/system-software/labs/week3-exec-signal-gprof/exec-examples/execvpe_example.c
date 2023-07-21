#include <unistd.h>
#include <stdio.h>

int main(void)
{
    char *argv[] = { "sh", "-c", "env", 0 };
    char *envp[] =
    {
        "HOME=/",
        "PATH=/bin:/usr/bin",
        "TZ=UTC0",
        "USER=jmccarthy",
        "LOGNAME=logfile",
        0
    };
    execvpe(argv[0], &argv[0], envp);
    fprintf(stderr, "Error.....!\n");
    return -1;
}