#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int jumlah_genap = 0;
int jumlah_ganjil = 0;

int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
int

    arr_size = 10;

pthread_mutex_t mutex_genap = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_ganjil = PTHREAD_MUTEX_INITIALIZER;

void *hitung_genap(void *arg) {
  pthread_mutex_lock(&mutex_genap);

  for (int i = 0; i < arr_size; i++) {
    if (arr[i] % 2 == 0) {
      jumlah_genap++;
    }
  }

  printf("Thread 1: Jumlah angka genap adalah %d\n", jumlah_genap);
  pthread_mutex_unlock(&mutex_genap);

  return NULL;
}

void *hitung_ganjil(void *arg) {
  pthread_mutex_lock(&mutex_ganjil);

  for (int i = 0; i < arr_size; i++) {
    if (arr[i] % 2 != 0) {
      jumlah_ganjil++;
    }
  }

  printf("Thread 2: Jumlah angka ganjil adalah %d\n", jumlah_ganjil);
  pthread_mutex_unlock(&mutex_ganjil);

  return NULL;
}

int main() {
  pthread_t thread1, thread2;

  pthread_create(&thread1, NULL, hitung_genap, NULL);
  pthread_create(&thread2, NULL, hitung_ganjil, NULL);

  pthread_join(thread1, NULL);
  pthread_join(thread2, NULL);

  printf("\nProgram Utama: Total elemen dalam array adalah %d\n",
         jumlah_genap + jumlah_ganjil);

  pthread_mutex_destroy(&mutex_genap);
  pthread_mutex_destroy(&mutex_ganjil);

  return 0;
}
