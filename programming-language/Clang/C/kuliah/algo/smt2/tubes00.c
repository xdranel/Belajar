#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

typedef struct {
  char *nama;
  char *tujuan;
  char *maskapai;
  char *nomor_tiket;
} data;

typedef struct elemen {
  data info;
  struct elemen *next;
  struct elemen *prev;
} elemen;

typedef struct list {
  elemen *first;
  elemen *last;
} list;

void createlist(list *list_data) {
  list_data->first = NULL;
  list_data->last = NULL;
}

elemen *createml(data isi) {
  elemen *p = (elemen *)malloc(sizeof(elemen));
  p->info = isi;
  p->next = NULL;
  p->prev = NULL;
  return p;
}

void insertFirst(list *list_data, elemen *p) {
  if ((list_data->first == NULL) && (list_data->last == NULL)) {
    list_data->first = p;
    list_data->last = p;
    p->next = list_data->first;
    p->prev = list_data->last;
  } else {
    p->next = list_data->first;
    list_data->first->prev = p;
    list_data->first = p;
    list_data->first->prev = list_data->last;
    list_data->last->next = list_data->first;
  }
}

void insertLast(list *list_data, elemen *p) {
  if ((list_data->first == NULL) && (list_data->last == NULL)) {
    list_data->first = p;
    list_data->last = p;
    p->next = list_data->first;
    p->prev = list_data->last;
  } else {
    p->prev = list_data->last;
    list_data->last->next = p;
    list_data->last = p;
    list_data->first->prev = list_data->last;
    list_data->last->next = list_data->first;
  }
}

void insertAfter(list *list_data, elemen *prev, elemen *p) {
  if (prev == list_data->last) {
    insertLast(list_data, p);
  } else {
    p->next = prev->next;
    prev->next->prev = p;
    prev->next = p;
    p->prev = prev;
  }
}

void insertBefore(list *list_data, elemen *next, elemen *p) {
  if (next == list_data->first) {
    insertFirst(list_data, p);
  } else {
    p->prev = next->prev;
    next->prev->next = p;
    p->next = next;
    next->prev = p;
  }
}

void deleteFirst(list *list_data) {
  elemen *p = list_data->first;
  if ((list_data->first != NULL) && (list_data->last != NULL)) {
    if (list_data->first == list_data->last) {
      list_data->first = NULL;
      list_data->last = NULL;
      p->next = NULL;
      p->prev = NULL;
    } else {
      list_data->first = p->next;
      list_data->first->prev = list_data->last;
      list_data->last->next = list_data->first;
    }
  }
}

void deleteLast(list *list_data) {
  elemen *p = list_data->last;
  if ((list_data->first != NULL) && (list_data->last != NULL)) {
    if (list_data->first == list_data->last) {
      list_data->first = NULL;
      list_data->last = NULL;
      p->next = NULL;
      p->prev = NULL;
    } else {
      list_data->last = p->prev;
      list_data->last->next = list_data->first;
    }
  }
}

void cetak_list(list list_data) {
  if (list_data.first == NULL) {
    printf("[]");
  } else {
    elemen *curent = list_data.first;
    while (curent->next != list_data.first) {
      printf("Nama : %s, Tujuan : %s, Maskapai : %s, Nomor Tiket : %s",
             curent->info.nama, curent->info.tujuan, curent->info.maskapai,
             curent->info.nomor_tiket);
      curent = curent->next;
      printf("\n");
    }
    printf("Nama : %s, Tujuan : %s, Maskapai : %s, Nomor Tiket : %s",
           curent->info.nama, curent->info.tujuan, curent->info.maskapai,
           curent->info.nomor_tiket);
  }
  printf("\n");
}

void cetak_nama(list list_data, char *nama) {
  elemen *curent = list_data.first;
  bool found = false;
  while (curent->next != list_data.first) {
    if (strcmp(curent->info.nama, nama) == 0) {
      printf("Nama : %s, Tujuan : %s, Maskapai : %s, Nomor Tiket : %s\n",
             curent->info.nama, curent->info.tujuan, curent->info.maskapai,
             curent->info.nomor_tiket);
      found = true;
    }
    curent = curent->next;
  }
  if (strcmp(curent->info.nama, nama) == 0) {
    printf("Nama : %s, Tujuan : %s, Maskapai : %s, Nomor Tiket : %s\n",
           curent->info.nama, curent->info.tujuan, curent->info.maskapai,
           curent->info.nomor_tiket);
    found = true;
  }
  if (!found) {
    printf("Data tidak ditemukan\n");
  }
}

int main() {
  printf("\e[H\e[2J\e[3J");
  list list_data;
  createlist(&list_data);

  data d1 = {"Gendhi", "Germany", "Garuda", "G123"};
  data d2 = {"Hafizhah", "Dubai", "Lion", "L456"};
  data d3 = {"Bram", "Germany", "Sriwijaya", "S789"};
  data d4 = {"Putri", "Germany", "Citilink", "C012"};

  elemen *p1 = createml(d1);
  elemen *p2 = createml(d2);
  elemen *p3 = createml(d3);
  elemen *p4 = createml(d4);

  insertFirst(&list_data, p1);
  cetak_list(list_data);
  printf("\n");

  insertLast(&list_data, p2);
  cetak_list(list_data);
  printf("\n");

  insertAfter(&list_data, p1, p3);
  cetak_list(list_data);
  printf("\n");

  insertBefore(&list_data, p3, p4);
  cetak_list(list_data);
  printf("\n");

  deleteLast(&list_data);
  cetak_list(list_data);
  printf("\n");

  deleteFirst(&list_data);
  cetak_list(list_data);

  getchar();
  printf("\e[H\e[2J\e[3J");
  return 0;
}
