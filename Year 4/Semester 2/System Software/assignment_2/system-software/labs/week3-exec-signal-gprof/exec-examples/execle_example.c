#include <unistd.h>
#include <stdio.h>

int main() {
    char *env[] = { "HOME=/usr/home", "LOGNAME=home", NULL };

    execle ("/bin/ls", "ls", "-l", NULL, env);

}
