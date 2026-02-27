#include <stdio.h>
#include <unistd.h>

void selectionsort(int arr[], int n) {
  for (int i = 0; i < n - 1; i++) {
    int min = i;
    for (int j = i + 1; j < n; j++) {
      if (arr[j] < arr[min]) {
        min = j;
      }
    }
    int temp = arr[i];
    arr[i] = arr[min];
    arr[min] = temp;
  }
}

void inputArray(int arr[], int n) {
  for (int i = 0; i < n; i++) {
    printf("Masukan angka ke-%d : ", i + 1);
    scanf("%d", &arr[i]);
  }
}

void printArray(int arr[], int n) {
  for (int i = 0; i < n; i++) {
    printf("%d, ", arr[i]);
  }
  printf("\n");
}

int arraySize() {
  int n;
  printf("Masukan ukuran array : ");
  scanf("%d", &n);
  printf("Ukuran array : %d\n", n);
  sleep(2);
  printf("\e[H\e[2J\e[3J");

  return n;
}

int main() {
  int n = arraySize();

  int dummyArray[n];
  inputArray(dummyArray, n);

  selectionsort(dummyArray, n);

  printArray(dummyArray, n);

  return 0;
}
