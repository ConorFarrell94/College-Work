#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <syslog.h>
#include <string.h>
#include <time.h>

extern void backup(void);




// function to backup the live website
void backup(void){
    printf("Backup Happening!!\n");
}

