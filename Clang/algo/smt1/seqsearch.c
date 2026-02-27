#include <stdio.h>
#include <unistd.h>

int sequentialSearch(int arr[], int k, int arraysize) {
  for (int i = 0; i < arraysize; i++) {
    if (k == arr[i]) {
      return i;
    }
  }
  return -1;
}

void inputArray(int arr[], int n) {
  for (int i = 0; i < n; i++) {
    printf("Masukan angka ke-%d : ", i + 1);
    scanf("%d", &arr[i]);
  }
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

int searchNumb() {
  int k;
  printf("Masukan angka yang ingin dicari : ");
  scanf("%d", &k);

  return k;
}

int main() {
  int n = arraySize();

  int dummyArray[n];
  inputArray(dummyArray, n);

  int k = searchNumb();

  int hasil = sequentialSearch(dummyArray, k, n);

  if (hasil == -1) {
    printf("Angka %d tidak ditemukan\n", k);
    printf("Maka Terserah\n");
  } else {
    printf("Angka %d ditemukan pada index ke-%d\n", k, hasil);
  }
  return 0;
}
