#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Mahasiswa {
  char NIM[20];
  char Nama[50];
  char Alamat[100];
  char Jurusan[50];
} Mahasiswa;

typedef struct Node {
  Mahasiswa data;
  struct Node *left;
  struct Node *right;
} Node;

Node *createNode(Mahasiswa data) {
  Node *newNode = (Node *)malloc(sizeof(Node));
  newNode->data = data;
  newNode->left = NULL;
  newNode->right = NULL;
  return newNode;
}

Node *insertNode(Node *root, Mahasiswa data) {
  if (root == NULL) {
    return createNode(data);
  }
  if (strcmp(data.NIM, root->data.NIM) < 0) {
    root->left = insertNode(root->left, data);
  } else if (strcmp(data.NIM, root->data.NIM) > 0) {
    root->right = insertNode(root->right, data);
  }
  return root;
}

Node *searchNode(Node *root, char NIM[]) {
  if (root == NULL || strcmp(root->data.NIM, NIM) == 0) {
    return root;
  }
  if (strcmp(NIM, root->data.NIM) < 0) {
    return searchNode(root->left, NIM);
  }
  return searchNode(root->right, NIM);
}

void inorderTraversal(Node *root) {
  if (root != NULL) {
    inorderTraversal(root->left);
    printf("NIM: %s, Nama: %s, Alamat: %s, Jurusan: %s\n", root->data.NIM,
           root->data.Nama, root->data.Alamat, root->data.Jurusan);
    inorderTraversal(root->right);
  }
}

void inputMahasiswa(Node **root) {
  Mahasiswa data;
  printf("Masukkan NIM: ");
  scanf("%s", data.NIM);
  printf("Masukkan Nama: ");
  scanf("%s", data.Nama);
  printf("Masukkan Alamat: ");
  scanf("%s", data.Alamat);
  printf("Masukkan Jurusan: ");
  scanf("%s", data.Jurusan);
  *root = insertNode(*root, data);
}

int main() {
  Node *root = NULL;
  int choice;
  char NIM[20];
  Node *searchResult;

  do {
    printf("\nMenu:\n");
    printf("1. Tambah Mahasiswa\n");
    printf("2. Cari Mahasiswa\n");
    printf("3. Tampilkan Semua Mahasiswa\n");
    printf("4. Bersihkan Jendela Terminal\n");
    printf("5. Keluar\n");
    printf("Pilihan: ");
    scanf("%d", &choice);

    switch (choice) {
    case 1:
      inputMahasiswa(&root);
      break;
    case 2:
      printf("Masukkan NIM yang dicari: ");
      scanf("%s", NIM);
      searchResult = searchNode(root, NIM);
      if (searchResult != NULL) {
        printf("Data Mahasiswa Ditemukan:\n");
        printf("NIM: %s, Nama: %s, Alamat: %s, Jurusan: %s\n",
               searchResult->data.NIM, searchResult->data.Nama,
               searchResult->data.Alamat, searchResult->data.Jurusan);
      } else {
        printf("Mahasiswa dengan NIM %s tidak ditemukan.\n", NIM);
      }
      break;
    case 3:
      printf("Data Semua Mahasiswa:\n");
      inorderTraversal(root);
      break;
    case 4:
      system("clear");
      break;
    case 5:
      printf("Keluar dari program.\n");
      break;
    default:
      printf("Pilihan tidak valid. Coba lagi.\n");
    }
  } while (choice != 5);

  return 0;
}
