#include <stdio.h>

int main() {

  int val = 10;

  int *result1 = &val;
  printf("Value: %d\n", *result1);
  printf("Value: %p\n", &result1);

  int **result2 = &result1;
  *result1 = 5;
  printf("Value: %d\n", **result2);
  printf("Value: %p\n", &result2);

  return 0;
}
