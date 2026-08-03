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

int search(struct node *root, int value) {
  if (root == NULL) {
    return 0;
  } else if (root->item == value) {
    printf("%d->", root->item);
    return 1;
  }
  if (search(root->left, value)) {
    printf("%d->", root->item);
    return 1;
  } else if (search(root->right, value)) {
    printf("%d->", root->item);
    return 1;
  }
  return 0;
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

  printf("\nSearch 30\n");
  search(root, 30);

  printf("\nSearch 6\n");
  search(root, 6);
  printf("\n");

  return 0;
}
