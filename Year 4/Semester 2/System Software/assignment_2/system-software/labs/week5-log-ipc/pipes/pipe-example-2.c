#include <stdlib.h>
#include <unistd.h>

void exec1();
void exec2();


int pid;
int pipefd[2];

void main() {

  // create pipe1
  if (pipe(pipefd) == -1) {
    perror("Error Init Pipe");
    exit(1);
  }

  // fork (ps aux)
  if ((pid = fork()) == -1) {
    perror("Error Init Fork 1");
    exit(1);
  } else if (pid == 0) {
    exec1();
  }
  
  // fork (grep login)
  if ((pid = fork()) == -1) {
    perror("Error Init Fork 1");
    exit(1);
  } else if (pid == 0) {
    exec2();
  }
  
  close(pipefd[0]);
  close(pipefd[1]);
} // close main


void exec1() {
  // input from stdin - ok
  // output to pipe
  dup2(pipefd[1], 1);
  // close fds
  close(pipefd[0]);
  close(pipefd[1]);
  // exec
  execlp("ps", "ps", "aux", NULL);
  // exec didn't work, exit
  perror("Error with ps aux");
  exit(1);
}

void exec2() {
  // input from pipe
  dup2(pipefd[0], 0);
  // output to stdout - default
  
  // close fds
  close(pipefd[0]);
  close(pipefd[1]);

  // exec
  execlp("grep", "grep", "login", NULL);
  // exec didn't work, exit
  perror("Error with grep login");
  exit(1);
}


