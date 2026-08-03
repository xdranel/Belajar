#include "stdio.h"

int kelipatan(int n);

int main() {

  int angka;

  printf("Masukan Angka : ");
  scanf("%d", &angka);

  if (angka == 0 || angka < 0) {
    printf("Angka %d kurang dari 0 atau sama dengan 0 atau negatif\n", angka);
  } else {
    printf("Hasil Kelipatan Dari %d Adalah %d\n", angka, kelipatan(angka));
  }

  return 0;
}

int kelipatan(int n) {
  int result = n * n;
  return result;
}
