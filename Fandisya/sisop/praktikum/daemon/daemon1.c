#include <fcntl.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>

void write_log(const char *message) {
  FILE *logfile = fopen("/tmp/daemon.log", "a");
  if (logfile == NULL) {
    exit(EXIT_FAILURE);
  }
  fprintf(logfile, "%s\n", message);
  fclose(logfile);
}

void signal_handler(int sig) {
  if (sig == SIGTERM) {
    write_log("Daemon terminated.");
    exit(EXIT_SUCCESS);
  }
}

int main() {
  pid_t pid, sid;
  pid = fork();
  if (pid < 0) {
    exit(EXIT_FAILURE);
  }
  if (pid > 0) {
    exit(EXIT_SUCCESS);
  }

  umask(0);

  sid = setsid();
  if (sid < 0) {
    exit(EXIT_FAILURE);
  }

  if ((chdir("/")) < 0) {
    exit(EXIT_FAILURE);
  }

  close(STDIN_FILENO);
  close(STDOUT_FILENO);
  close(STDERR_FILENO);

  signal(SIGTERM, signal_handler);

  while (1) {
    time_t now = time(NULL);
    char *time_str = ctime(&now);
    time_str[strlen(time_str) - 1] = '\0';
    write_log(time_str);

    sleep(10);
  }

  exit(EXIT_SUCCESS);
}
