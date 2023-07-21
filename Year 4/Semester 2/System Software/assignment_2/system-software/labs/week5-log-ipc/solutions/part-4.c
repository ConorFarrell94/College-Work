#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void exec_ls();
void exec_grep();
void exec_grep_route_to_stdout();

int pid;
int pipe1[2];
int pipe2[2];

void main() {
  // ls -la | awk { print $3 } | sort -u	

  // create pipe1
  if (pipe(pipe1) == -1) {
    perror("bad pipe1");
    exit(1);
  }

  // fork (ls -la)
  if ((pid = fork()) == -1) {
    perror("bad fork1");
    exit(1);
  } else if (pid == 0) {
    // stdin --> ls --> pipe1
    exec_ls();
  }
  // parent

  // create pipe2
  if (pipe(pipe2) == -1) {
    perror("bad pipe2");
    exit(1);
  }

  // fork (awk { print $1 })
  if ((pid = fork()) == -1) {
    perror("bad fork2");
    exit(1);
  } else if (pid == 0) {
    // pipe1 --> grep --> pipe2
    exec_awk();
  }
  // parent

  // close unused fds
  close(pipe1[0]);
  close(pipe1[1]);

  // fork (grep sbin)
  if ((pid = fork()) == -1) {
    perror("bad fork3");
    exit(1);
  } else if (pid == 0) {
    // pipe2 --> grep --> stdout
    exec_grep_route_to_stdout();
  }
  // parent


}


void exec_ls() {
    // stdin --> ls --> pipe1
  // input from stdin (already done)
  // output to pipe1
  dup2(pipe1[STDOUT_FILENO], STDOUT_FILENO);
  // close fds
  close(pipe1[STDIN_FILENO]);
  close(pipe1[STDOUT_FILENO]);
  // exec
  execlp("ls", "ls", "-al", NULL);
  // exec didn't work, exit
  perror("bad exec ps");
  exit(1);
}

void exec_awk() {
    // pipe1 --> awk --> pipe2
  // input from pipe1
  dup2(pipe1[STDIN_FILENO], STDIN_FILENO);
  // output to pipe2
  dup2(pipe2[STDOUT_FILENO], STDOUT_FILENO);
  // close fds
  close(pipe1[STDIN_FILENO]);
  close(pipe1[STDOUT_FILENO]);
  close(pipe2[STDIN_FILENO]);
  close(pipe2[STDOUT_FILENO]);
  // exec
  execlp("awk", "awk", "{ print $3 }", NULL);
  // exec didn't work, exit
  perror("bad exec awk");
  exit(1);
}

void exec_grep_route_to_stdout() {
  // input from pipe2
  // pipe2 --> grep --> stdout
  dup2(pipe2[STDIN_FILENO], STDIN_FILENO);
  // output to stdout (already done)
  // close fds
  close(pipe2[STDIN_FILENO]);
  close(pipe2[STDOUT_FILENO]);
  // exec
  execlp("sort", "sort", "-u", NULL);
  // exec didn't work, exit
  perror("bad exec sort");
  _exit(1);
}
