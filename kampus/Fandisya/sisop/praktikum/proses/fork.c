#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
  pid_t child_id;
  int status;

  printf("Ini adalah program untuk melakukan fork() systemcall, PPID dari "
         "process ini adalah %d\n",
         (int)getppid());

  child_id = fork();

  // wait(&status);

  printf("\nIni akan kepanggil 2 kali!\n");

  if (child_id != 0) {
    printf("\nParent Process: \nPID: %d, Child's PID: %d\n", (int)getpid(),
           (int)child_id);
  } else {
    printf("\nChild Process: \nPID: %d, Parent's PID: %d\n", (int)getpid(),
           (int)getppid());
  }

  return 0;
}
