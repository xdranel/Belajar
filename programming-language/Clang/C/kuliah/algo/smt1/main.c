#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

struct jadwal {
  char tanggal[20];
  char jam[20];
  char maskapai[30];
};

struct jadwal inputData();
void tampilkanData(struct jadwal arr[]);
void cariData(struct jadwal arr[], int n);
void bubbleSort(struct jadwal arr[], int n);
void ascending(struct jadwal arr[], int n);
void descending(struct jadwal arr[], int n);

int main() {
  struct jadwal *arr;
  int n = 0, i, menu;
  arr = malloc(sizeof(struct jadwal) * (n + 1));
  bool isRunning = true;

  while (isRunning) {
    printf("\e[H\e[2J\e[3J");
    printf("1. Input Data\n");
    printf("2. Tampilkan Data\n");
    printf("3. Cari Data\n");
    printf("4. Urutkan Data\n");
    printf("5. Exit\n");
    printf("Pilih menu: ");
    scanf("%d", &menu);

    char chooseInp, chooseInp1, chooseInp2;
    char menu1;
    switch (menu) {
    case 1:
      do {
        printf("\e[H\e[2J\e[3J");
        arr = realloc(arr, sizeof(struct jadwal) * (n + 1));
        arr[n] = inputData();
        n++;
        printf("====================================\n");
        printf("Apakah anda ingin memasukkan data lagi? (y/n): ");
        scanf(" %c", &chooseInp);
        chooseInp = tolower(chooseInp);
        printf("\e[H\e[2J\e[3J");
      } while (chooseInp == 'y');
      break;
    case 2:
      do {
        printf("\e[H\e[2J\e[3J");
        printf("====================================\n");
        printf("         Jadwal Penerbangan         \n");
        printf("====================================\n");
        printf("Nama Maskapai               Tanggal                Jam\n");
        for (i = 0; i < n; i++) {
          tampilkanData(&arr[i]);
        }
        printf("====================================\n");
        printf("Kembali ke menu awal? (y/n): ");
        scanf(" %c", &chooseInp);
        chooseInp = tolower(chooseInp);
        printf("\e[H\e[2J\e[3J");
      } while (chooseInp == 'n');
      break;
    case 3:
      do {
        printf("\e[H\e[2J\e[3J");
        cariData(arr, n);
        printf("Kembali ke menu awal? (y/n): ");
        scanf(" %c", &chooseInp1);
        chooseInp1 = tolower(chooseInp1);
        printf("\e[H\e[2J\e[3J");
      } while (chooseInp1 == 'n');
      break;
    case 4:
      printf("\e[H\e[2J\e[3J");
      printf("Aapakah Anda Ingin Mengurutkan Data (y/n): ");
      scanf(" %c", &chooseInp2);
      while (chooseInp2 == 'y') {
        printf("\e[H\e[2J\e[3J");
        bubbleSort(arr, n);
        printf("\e[H\e[2J\e[3J");
        printf("Apakah Anda Ingin Mengurutkan Lagi? (y/n): ");
        scanf(" %c", &chooseInp2);
        printf("\e[H\e[2J\e[3J");
      }
      break;
    case 5:
      printf("\e[H\e[2J\e[3J");
      isRunning = false;
      printf("Terima kasih telah menggunakan program ini!\n");
      sleep(1);
      printf("\e[H\e[2J\e[3J");
      break;
    default:
      printf("\e[H\e[2J\e[3J");
      printf("Input Tidak Ada\nSilahkan Pilih Menu Lain\n");
      sleep(1);
      printf("\e[H\e[2J\e[3J");
      break;
    }
  }
  return 0;
}

struct jadwal inputData() {
  int i;
  char c;
  struct jadwal arr;
  while ((c = getchar()) != '\n' && c != EOF)
    ;
  printf("\e[H\e[2J\e[3J");
  printf("Tanggal keberangkatan (dd/mm/yyyy): ");
  fgets(arr.tanggal, 20, stdin);
  arr.tanggal[strlen(arr.tanggal) - 1] = '\0';

  printf("Waktu keberangkatan (hh:mm): ");
  fgets(arr.jam, 20, stdin);
  arr.jam[strlen(arr.jam) - 1] = '\0';

  printf("Maskapai Penerbangan: ");
  fgets(arr.maskapai, 30, stdin);
  arr.maskapai[strlen(arr.maskapai) - 1] = '\0';
  return arr;
}

void tampilkanData(struct jadwal arr[]) {
  printf("%-28s%-23s%s\n", arr->maskapai, arr->tanggal, arr->jam);
}

void cariData(struct jadwal arr[], int n) {
  char tanggal[20];
  char jam[20];
  char maskapai[20];
  bool cariBerd = true;
  int menuBerd, poin1, poin2, poin3, i1, i2, i3;

  while (cariBerd) {
    printf("\e[H\e[2J\e[3J");
    printf("1. Cari Berdasarkan Tanggal\n");
    printf("2. Cari Berdasarkan Jam\n");
    printf("3. Cari Berdasarkan Maskapai\n");
    printf("4. Exit\n");
    printf("Pilih menu: ");
    scanf(" %d", &menuBerd);

    switch (menuBerd) {
    case 1:
      printf("\e[H\e[2J\e[3J");
      printf("Masukan Tanggal Yang Di Cari: ");
      scanf(" %[^\n]", tanggal);
      printf("====================================\n");
      i1 = 0;
      poin1 = 0;
      while (i1 < n) {
        if (strstr(arr[i1].tanggal, tanggal)) {
          tampilkanData(&arr[i1]);
          poin1++;
        }
        i1++;
      }
      if (poin1 == 0) {
        printf("\nTanggal Yang Anda Cari Tidak Ada.\n");
      }
      sleep(2);
      printf("\e[H\e[2J\e[3J");
      break;
    case 2:
      printf("\e[H\e[2J\e[3J");
      printf("Masukan Waktu Yang Di Cari: ");
      scanf(" %[^\n]", jam);
      printf("====================================\n");
      i2 = 0;
      poin2 = 0;
      while (i2 < n) {
        if (strstr(arr[i2].jam, jam)) {
          tampilkanData(&arr[i2]);
          poin2++;
        }
        i2++;
      }
      if (poin2 == 0) {
        printf("\nWaktu Yang Anda Cari Tidak Ada.\n");
      }
      sleep(2);
      break;
    case 3:
      printf("\e[H\e[2J\e[3J");
      printf("Masukan Nama Maskapai Yang Di Cari: ");
      scanf(" %[^\n]", maskapai);
      printf("====================================\n");
      i3 = 0;
      poin3 = 0;
      while (i3 < n) {
        if (strstr(arr[i3].maskapai, maskapai)) {
          tampilkanData(&arr[i3]);
          poin3++;
        }
        i3++;
      }
      if (poin3 == 0) {
        printf("\nNama Maskapai Yang Anda Cari Tidak Ada.\n");
      }
      sleep(2);
      break;
    case 4:
      printf("\e[H\e[2J\e[3J");
      cariBerd = false;
      break;
    default:
      printf("\e[H\e[2J\e[3J");
      printf("Input Tidak Ada\nSilahkan Pilih Menu Lain\n");
      sleep(1);
      printf("\e[H\e[2J\e[3J");
      break;
    }
  }
}

void bubbleSort(struct jadwal arr[], int n) {
  char bubSortMenu;
  printf("Bubble Sort\nMengurutkan Secara Ascending/Descending (A/D)? : ");
  scanf(" %c", &bubSortMenu);
  bubSortMenu = toupper(bubSortMenu);
  if (bubSortMenu == 'A') {
    ascending(arr, n);
  } else if (bubSortMenu == 'D') {
    descending(arr, n);
  } else {
    printf("Input tidak valid\n");
  }
}

void ascending(struct jadwal arr[], int n) {
  int i, j;
  struct jadwal temp;
  for (i = 0; i < n - 1; i++) {
    for (j = 0; j < n - i - 1; j++) {
      if (strcmp(arr[j].tanggal, arr[j + 1].tanggal) > 0) {
        temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      } else if (strcmp(arr[j].tanggal, arr[j + 1].tanggal) == 0) {
        if (strcmp(arr[j].jam, arr[j + 1].jam) > 0) {
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        } else if (strcmp(arr[j].jam, arr[j + 1].jam) == 0) {
          if (strcmp(arr[j].maskapai, arr[j + 1].maskapai) > 0) {
            temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
          }
        }
      }
    }
  }
}

void descending(struct jadwal arr[], int n) {
  int i, j;
  struct jadwal temp;
  for (i = 0; i < n - 1; i++) {
    for (j = 0; j < n - i - 1; j++) {
      if (strcmp(arr[j].tanggal, arr[j + 1].tanggal) < 0) {
        temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      } else if (strcmp(arr[j].tanggal, arr[j + 1].tanggal) == 0) {
        if (strcmp(arr[j].jam, arr[j + 1].jam) < 0) {
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        } else if (strcmp(arr[j].jam, arr[j + 1].jam) == 0) {
          if (strcmp(arr[j].maskapai, arr[j + 1].maskapai) < 0) {
            temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
          }
        }
      }
    }
  }
}
