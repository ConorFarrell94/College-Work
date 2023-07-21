#include <unistd.h>
#include <stdio.h>

// gcc -o execl_example execl_example.c

int main() {
    printf("execl example\n");
    execl("/bin/ls", "ls", "-la", NULL);
    printf("This will never appear....(try remove the absolute path for a demo)");
}