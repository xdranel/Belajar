#include <stdio.h>
#include <stdlib.h> // Required for malloc and free

int main() {
  int rows = 3;
  int cols = 4;

  // 1. Allocate memory for an array of row pointers (double pointer)
  int **matrix = (int **)malloc(rows * sizeof(int *));

  if (matrix == NULL) {
    printf("Memory allocation failed!\n");
    return 1;
  }

  // 2. Allocate memory for each row (columns)
  for (int i = 0; i < rows; i++) {
    matrix[i] = (int *)malloc(cols * sizeof(int));

    if (matrix[i] == NULL) {
      printf("Memory allocation failed for row %d!\n", i);
      return 1;
    }
  }

  // 3. Use the allocated memory (assign and print values)
  int count = 1;
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      matrix[i][j] = count++;
      printf("%2d ", matrix[i][j]);
    }
    printf("\n");
  }

  // 4. Free the memory (always free in reverse order of allocation)

  // First, free each individual row
  for (int i = 0; i < rows; i++) {
    free(matrix[i]);
  }

  // Second, free the array of row pointers
  free(matrix);

  // Good practice: set pointer to NULL after freeing
  matrix = NULL;

  printf("\nMemory successfully allocated, used, and freed!\n");

  return 0;
}
