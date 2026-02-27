#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
  char nama[20];
  int nomor_tiket;
  char jenis_kelamin[10];
  char maskapai[30];
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

// Function to create a new node
Node *createNode(data info) {
  Node *newNode = (Node *)malloc(sizeof(Node));
  newNode->info = info;
  newNode->prev = newNode->next = NULL;
  return newNode;
}

// Function to insert a new node at the beginning of the list
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

// Function to insert a new node at the end of the list
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

// Function to insert a new node after a given node
void insertAfter(CircularDoublyLinkedList *list, Node *node, data info) {
  Node *newNode = createNode(info);
  newNode->prev = node;
  newNode->next = node->next;
  node->next->prev = newNode;
  node->next = newNode;
  list->size++;
}

// Function to insert a new node before a given node
void insertBefore(CircularDoublyLinkedList *list, Node *node, data info) {
  Node *newNode = createNode(info);
  newNode->prev = node->prev;
  newNode->next = node;
  node->prev->next = newNode;
  node->prev = newNode;
  list->size++;
}

// Function to delete the first node in the list
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

// Function to delete the last node in the list
void deleteLast(CircularDoublyLinkedList *list) {
  if (list->head == NULL)
    return;
  Node *temp = list->head->prev;
  list->head->prev = temp->prev;
  temp->prev->next = list->head;
  free(temp);
  list->size--;
}

// Function to delete a node with a given nomor_tiket
void deleteByNomorTiket(CircularDoublyLinkedList *list, int nomor_tiket) {
  Node *temp = list->head;
  do {
    if (temp->info.nomor_tiket == nomor_tiket) {
      if (temp == list->head) {
        deleteFirst(list);
      } else if (temp == list->head->prev) {
        deleteLast(list);
      } else {
        temp->prev->next = temp->next;
        temp->next->prev = temp->prev;
        free(temp);
        list->size--;
      }
      return;
    }
    temp = temp->next;
  } while (temp != list->head);
}

// Function to search for a node with a given nomor_tiket
Node *searchByNomorTiket(CircularDoublyLinkedList *list, int nomor_tiket) {
  Node *temp = list->head;
  do {
    if (temp->info.nomor_tiket == nomor_tiket) {
      return temp;
    }
    temp = temp->next;
  } while (temp != list->head);
  return NULL;
}

// Function to print the list
void printList(CircularDoublyLinkedList *list) {
  Node *temp = list->head;
  do {
    printf("Nama: %s, Nomor Tiket: %d, Jenis Kelamin: %s, Maskapai: %s\n",
           temp->info.nama, temp->info.nomor_tiket, temp->info.jenis_kelamin,
           temp->info.maskapai);
    temp = temp->next;
  } while (temp != list->head);
}

int main() {
  CircularDoublyLinkedList list;
  list.head = NULL;
  list.size = 0;
  Node *node;

  data info1 = {"John", 123, "Male", "Garuda"};
  data info2 = {"Jane", 456, "Female", "Lion Air"};
  data info3 = {"Bob", 789, "Male", "Sriwijaya"};
  data info4 = {"Alice", 234, "Female", "Batik Air"};

  insertFirst(&list, info1);
  printList(&list);
  printf("\n");

  insertLast(&list, info2);
  printList(&list);
  printf("\n");

  node = list.head;
  insertAfter(&list, node, info3);
  printList(&list);
  printf("\n");

  node = list.head->prev; // node points to the second node in the list
  insertBefore(&list, node, info4);
  printList(&list);
  printf("\n");

  deleteFirst(&list);
  printList(&list);
  printf("\n");

  deleteLast(&list);
  printList(&list);
  printf("\n");

  Node *found = searchByNomorTiket(&list, 789);
  if (found != NULL) {
    printf("Found node with nomor_tiket %d\n", found->info.nomor_tiket);
  } else {
    printf("Node with nomor_tiket %d not found\n", 789);
  }

  deleteByNomorTiket(&list, 456);
  printList(&list);
  printf("\n");

  return 0;
}
