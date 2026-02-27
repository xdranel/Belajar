#include <stdio.h>
#include <stdlib.h>

struct node {
  int item;
  struct node *left;
  struct node *right;
};

struct node *createNode(int value) {
  struct node *newNode = (struct node *)malloc(sizeof(struct node));
  newNode->item = value;
  newNode->left = NULL;
  newNode->right = NULL;
  return newNode;
}

struct node *insertleft(struct node *root, int value) {
  root->left = createNode(value);
  return root;
}

struct node *insertright(struct node *root, int value) {
  root->right = createNode(value);
  return root;
}

void inorderTraversal(struct node *root) {
  if (root == NULL) {
    return;
  }
  inorderTraversal(root->left);
  printf("%d -> ", root->item);
  inorderTraversal(root->right);
}

void preorderTraversal(struct node *root) {
  if (root == NULL) {
    return;
  }
  printf("%d -> ", root->item);
  preorderTraversal(root->left);
  preorderTraversal(root->right);
}

void postorderTraversal(struct node *root) {
  if (root == NULL) {
    return;
  }
  postorderTraversal(root->left);
  postorderTraversal(root->right);
  printf("%d -> ", root->item);
}

void search(struct node *root, int value, int path[], int pathlen) {
  if (root == NULL) {
    return;
  }
  path[pathlen] = root->item;
  pathlen++;
  if (root->item == value) {
    for (int i = 0; i < pathlen; i++) {
      printf("%d -> ", path[i]);
    }
    printf("\n");
    return;
  }
  search(root->left, value, path, pathlen);
  search(root->right, value, path, pathlen);
}

int main() {
  struct node *root = createNode(50);
  root = insertleft(root, 12);
  root = insertright(root, 9);

  struct node *temp;
  temp = root->left;
  temp = insertleft(temp, 51);
  temp = insertright(temp, 5);

  temp = root->right;
  temp = insertleft(temp, 6);
  temp = insertright(temp, 4);

  temp = root->left->left;
  temp = insertleft(temp, 20);
  temp = insertright(temp, 30);

  printf("Inorder Traversal \n");
  inorderTraversal(root);

  printf("\nPreorder Traversal \n");
  preorderTraversal(root);

  printf("\nPostorder Traversal \n");
  postorderTraversal(root);

  int path[100];

  printf("\n\nSearch 30\n");
  search(root, 30, path, 0);

  printf("Search 6\n");
  search(root, 6, path, 0);
  return 0;
}
