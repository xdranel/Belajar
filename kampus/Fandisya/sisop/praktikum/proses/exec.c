#include <stdio.h>
#include <unistd.h>

int main() {

  // argv[n] = { {your-program-name}, {argument[1]},
  // {argument[2]},.....,{argument[n-2]}, NULL }
  char *argv[4] = {"list", "-l", "/home/", NULL};

  execv("/bin/ls", argv);

  printf("Baris ini tidak akan dieksekusi!\n");

  return 0;
}
