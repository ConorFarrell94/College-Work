#include <unistd.h>
#include <stdio.h>

int main(void)
{
    char *argv[] = { "/bin/ls", "-al", NULL };

    execv(argv[0], &argv[0]);
    fprintf(stderr, "Error.....!\n");
    return -1;
}