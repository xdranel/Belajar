#include <stdio.h>
#include <string.h>

int main() {
  // Allocate space for a small string of up to 5 characters (+ 1 for the null
  // terminator '\0')
  char buffer[6];

  // We try to copy a string that is way too long (13 characters) into our
  // 6-byte buffer
  char *too_long = "Hello, World!";

  // strcpy does not check bounds! It keeps writing past the end of 'buffer'
  strcpy(buffer, too_long);

  printf("Buffer says: %s\n", buffer);

  return 0;
}
