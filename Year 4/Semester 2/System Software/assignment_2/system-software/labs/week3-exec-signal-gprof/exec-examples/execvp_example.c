#include <unistd.h>
#include <stdio.h>

int main() {
    char *argv[] = { "/bin/ls", "-al", NULL };
    execvp(argv[0], &argv[0]);
    printf("This will never appear....(note this works without a full path)");
}