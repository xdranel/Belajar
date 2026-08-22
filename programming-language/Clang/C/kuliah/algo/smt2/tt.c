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

typedef struct Node {
  data info;
  struct Node *prev;
  struct Node *next;
} Node;

typedef struct {
  Node *head;
  int size;
} CircularDoublyLinkedList;

Node *createNode(data info) {
  Node *newNode = (Node *)malloc(sizeof(Node));
  newNode->info = info;
  newNode->prev = newNode->next = NULL;
  return newNode;
}

void insertFirst(CircularDoublyLinkedList *list, data info) {
  Node *newNode = createNode(info);
  if (list->head == NULL) {
    list->head = newNode;
    newNode->prev = newNode->next = newNode;
  } else {
    newNode->next = list->head;
    newNode->prev = list->head->prev;
    list->head->prev->next = newNode;
    list->head->prev = newNode;
    list->head = newNode;
  }
  list->size++;
}

void insertLast(CircularDoublyLinkedList *list, data info) {
  Node *newNode = createNode(info);
  if (list->head == NULL) {
    list->head = newNode;
    newNode->prev = newNode->next = newNode;
  } else {
    newNode->prev = list->head->prev;
    newNode->next = list->head;
    list->head->prev->next = newNode;
    list->head->prev = newNode;
  }
  list->size++;
}

void insertAfter(CircularDoublyLinkedList *list, Node *node, data info) {
  Node *newNode = createNode(info);
  newNode->prev = node;
  newNode->next = node->next;
  node->next->prev = newNode;
  node->next = newNode;
  list->size++;
}

void insertBefore(CircularDoublyLinkedList *list, Node *node, data info) {
  Node *newNode = createNode(info);
  newNode->prev = node->prev;
  newNode->next = node;
  node->prev->next = newNode;
  node->prev = newNode;
  list->size++;
}

void deleteFirst(CircularDoublyLinkedList *list) {
  if (list->head == NULL)
    return;
  Node *temp = list->head;
  list->head = list->head->next;
  list->head->prev = temp->prev;
  temp->prev->next = list->head;
  free(temp);
  list->size--;
}

void deleteLast(CircularDoublyLinkedList *list) {
  if (list->head == NULL)
    return;
  Node *temp = list->head->prev;
  list->head->prev = temp->prev;
  temp->prev->next = list->head;
  free(temp);
  list->size--;
}

void printList(CircularDoublyLinkedList *list) {
  Node *temp = list->head;
  do {
    printf("Nama: %s, Nomor Tiket: %s, Tujuan: %s, Maskapai: %s\n",
           temp->info.nama, temp->info.nomor_tiket, temp->info.tujuan,
           temp->info.maskapai);
    temp = temp->next;
  } while (temp != list->head);
}

Node *printSpecificData(CircularDoublyLinkedList *list, char *nama) {
  Node *temp = list->head;
  do {
    if (temp->info.nama == nama) {
      printf("Nama: %s, Nomor Tiket: %s, Tujuan: %s, Maskapai: %s\n",
             temp->info.nama, temp->info.nomor_tiket, temp->info.tujuan,
             temp->info.maskapai);
      break;
    }
    temp = temp->next;
  } while (temp != list->head);
  return NULL;
}

int main() {
  printf("\e[H\e[2J\e[3J");
  CircularDoublyLinkedList list;
  list.head = NULL;
  list.size = 0;
  Node *node;
  int x;
  char *notif = "===== Penerbangan Menuju Bandara C =====\n";
  printf("%s", notif);

  data info1 = {"Gendhi", "Germany", "Qatar Airways", "AZ6"};
  data info2 = {"Hafizhah", "Dubai", "Garuda Indonesia", "ABC"};

  insertFirst(&list, info1);
  printList(&list);
  sleep(3);
  printf("\e[H\e[2J\e[3J");

  printf("===== Penerbangan Dari Bandara A Menuju Bandara B =====\n");
  insertLast(&list, info2);
  printSpecificData(&list, "Hafizhah");
  sleep(3);
  printf("\e[H\e[2J\e[3J");

  // node = list.head;
  // data info3 = {"Bob", 789, "Male", "Sriwijaya"};
  // insertAfter(&list, node, info3);
  // printList(&list);
  // printf("\n");
  //
  // node = list.head->prev; // node points to the second node in the list
  // data info4 = {"Alice", 234, "Female", "Batik Air"};
  // insertBefore(&list, node, info4);
  // printList(&list);
  // printf("\n");
  //
  // deleteFirst(&list);
  // printList(&list);
  // printf("\n");
  //
  // deleteLast(&list);
  // printList(&list);
  // printf("\n");
  //
  return 0;
}
