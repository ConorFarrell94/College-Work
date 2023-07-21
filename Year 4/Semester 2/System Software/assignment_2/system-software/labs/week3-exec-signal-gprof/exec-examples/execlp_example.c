#include <unistd.h>
#include <stdio.h>

int main() {
    printf("execl example\n");
    printf("execlp searches for the file (1st argument of execlp) in those directories pointed by PATH\n");
    execlp("ls", "ls", "-la", NULL);
    printf("This will never appear....(note this works without a full path)");
}