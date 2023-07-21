#include "termios.h"
#include "unistd.h"

//The program below disables the interrupt 
// character and sets the end-of-file character to Control-B.
int main(void)
{
    struct termios term;
    long vdisable;
    if (isatty(STDIN_FILENO) == 0)
        puts("standard input is not a terminal device");

    if ((vdisable = fpathconf(STDIN_FILENO, _PC_VDISABLE)) < 0) 
        puts("fpathconf error or _POSIX_VDISABLE not in effect");

    if (tcgetattr(STDIN_FILENO, &term) < 0) /* fetch tty state */ 
        puts("tcgetattr error");

    term.c_cc[VINTR] = vdisable; /* disable INTR character */ 
    term.c_cc[VEOF] = 2; /* EOF is Control-B */
    
    if (tcsetattr(STDIN_FILENO, TCSAFLUSH, &term) < 0) 
       puts("tcsetattr error");
    
    while(1) {
        sleep(1);
    }

} // end main


