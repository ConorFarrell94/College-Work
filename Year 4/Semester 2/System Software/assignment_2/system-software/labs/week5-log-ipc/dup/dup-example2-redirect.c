#include <stdio.h>

main()
{
    int    fd;
    fpos_t pos;

    printf("stdout, ");

    fflush(stdout);
    fgetpos(stdout, &pos);
    fd = dup(fileno(stdout));
    freopen("stdout.out", "w", stdout);

    f();

    fflush(stdout);
    dup2(fd, fileno(stdout));
    close(fd);
    clearerr(stdout);
    fsetpos(stdout, &pos);        /* for C9X */

    printf("stdout again\n");
}

f()
{
printf("stdout in f()");
}