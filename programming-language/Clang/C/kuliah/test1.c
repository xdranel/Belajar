#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

struct jadwal {
  char tanggal[20];
  char jam[20];
  char maskapai[30];
};

struct jadwal inputData();

void tampilkanData(struct jadwal arr[]);
void cariData(struct jadwal arr[], int n);

void ascending(struct jadwal arr[], int n);
void recursiveAscendingSort(struct jadwal arr[], int low, int high);

int compareJadwal(struct jadwal a, struct jadwal b);
void swap(struct jadwal *a, struct jadwal *b);
int partition(struct jadwal arr[], int low, int high);

int main() {
  struct jadwal *arr;
  int n = 0, i, menu;
  arr = malloc(sizeof(struct jadwal) * (n + 1));
  bool isRunning = true;

  while (isRunning) {
    printf("\e[H\e[2J\e[3J");
    printf("1. Input Data Otomatis\n");
    printf("2. Tampilkan Data\n");
    printf("3. Cari Data\n");
    printf("4. Exit\n");
    printf("Pilih menu: ");
    scanf("%d", &menu);

    char chooseInp;
    switch (menu) {
    case 1:
      do {
        printf("\e[H\e[2J\e[3J");
        printf("Masukkan jumlah data (N): ");
        int N;
        scanf("%d", &N);
        for (int i = 0; i < N; i++) {
          arr = realloc(arr, sizeof(struct jadwal) * (n + 1));
          snprintf(arr[n].tanggal, 20, "%02d-12-2025", rand() % 28 + 1);
          snprintf(arr[n].jam, 20, "%02d:%02d", rand() % 24, rand() % 60);
          const char *maskapaiList[] = {"Garuda", "Citilink", "Batik", "Asia",
                                        "Lion"};
          int totalMaskapai = sizeof(maskapaiList) / sizeof(maskapaiList[0]);
          strcpy(arr[n].maskapai, maskapaiList[rand() % totalMaskapai]);
          n++;
        }
        printf("%d data berhasil di-generate!\n", N);

        clock_t start, end;
        double timeIterative, timeRecursive;

        struct jadwal *copyArr = malloc(sizeof(struct jadwal) * n);
        memcpy(copyArr, arr, sizeof(struct jadwal) * n);

        start = clock();
        ascending(arr, n);
        end = clock();
        timeIterative = ((double)(end - start)) / CLOCKS_PER_SEC;

        start = clock();
        recursiveAscendingSort(copyArr, 0, n - 1);
        end = clock();
        timeRecursive = ((double)(end - start)) / CLOCKS_PER_SEC;

        printf("====================================\n");
        printf("Waktu eksekusi Bubble Sort: %.5f detik\n", timeIterative);
        printf("Waktu eksekusi Quick Sort: %.5f detik\n", timeRecursive);
        printf("====================================\n");

        free(copyArr);

        printf("Apakah anda ingin memasukkan data lagi? (y/n): ");
        scanf(" %c", &chooseInp);
        chooseInp = tolower(chooseInp);
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
      } while (chooseInp == 'n');
      break;
    case 3:
      do {
        printf("\e[H\e[2J\e[3J");
        cariData(arr, n);
        printf("Kembali ke menu awal? (y/n): ");
        scanf(" %c", &chooseInp);
        chooseInp = tolower(chooseInp);
        printf("\e[H\e[2J\e[3J");
      } while (chooseInp == 'n');
      break;
    case 4:
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
  free(arr);
  return 0;
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
      printf("====================================\n");
      printf("Tekan sembarang tombol untuk kembali...");
      getchar();
      getchar();
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
      printf("====================================\n");
      printf("Tekan sembarang tombol untuk kembali...");
      getchar();
      getchar();
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
      printf("Tekan sembarang tombol untuk kembali...");
      getchar();
      getchar();
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

void ascending(struct jadwal arr[], int n) {
  for (int i = 0; i < n - 1; i++) {
    for (int j = 0; j < n - i - 1; j++) {
      if (strcmp(arr[j].tanggal, arr[j + 1].tanggal) > 0 ||
          (strcmp(arr[j].tanggal, arr[j + 1].tanggal) == 0 &&
           strcmp(arr[j].jam, arr[j + 1].jam) > 0)) {
        swap(&arr[j], &arr[j + 1]);
      }
    }
  }
}

void recursiveAscendingSort(struct jadwal arr[], int low, int high) {
  if (low < high) {
    int dump = partition(arr, low, high);
    recursiveAscendingSort(arr, low, dump - 1);
    recursiveAscendingSort(arr, dump + 1, high);
  }
}

int compareJadwal(struct jadwal a, struct jadwal b) {
  int dateCompare = strcmp(a.tanggal, b.tanggal);
  return dateCompare;

  int timeCompare = strcmp(a.jam, b.jam);
  if (timeCompare != 0)
    return timeCompare;
  if (dateCompare != 0)

    return strcmp(a.maskapai, b.maskapai);
}

void swap(struct jadwal *a, struct jadwal *b) {
  struct jadwal temp = *a;
  *a = *b;
  *b = temp;
}

int partition(struct jadwal arr[], int low, int high) {
  struct jadwal pivot = arr[high];
  int i = (low - 1);
  for (int j = low; j < high; j++) {
    if (compareJadwal(arr[j], pivot) <= 0) {
      i++;
      swap(&arr[i], &arr[j]);
    }
  }
  swap(&arr[i + 1], &arr[high]);
  return (i + 1);
}
