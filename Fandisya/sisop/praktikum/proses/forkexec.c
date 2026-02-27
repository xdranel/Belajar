#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
  pid_t child_id;

  child_id = fork();

  if (child_id < 0) {
    exit(EXIT_FAILURE); // Jika gagal membuat proses baru, program akan berhenti
  }

  if (child_id == 0) {
    // this is child process
    char *argv[] = {"cp", "/var/log/apt/history.log",
                    "/home/xdx-1o1/Belajar/Sisop/praktikum/log/child/", NULL};
    execv("/bin/cp", argv);
  } else {
    // this is parent processs
    char *argv[] = {"cp", "/var/log/dpkg.log",
                    "/home/xdx-1o1/Belajar/Sisop/praktikum/log/parent/", NULL};
    execv("/bin/cp", argv);
  }

  return 0;
}
