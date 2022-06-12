#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "agenda-avl-tree-lib.h"

int main() {
  Node *agenda = NULL;
  int menu = 1, phoneNumber;
  char name[30];

  while (menu >= 1 && menu <= 7) {
    printf("\n---------------AGENDA MENU----------------\n");
    printf("\n1. Incluir\n");
    printf("2. Buscar\n");
    printf("3. Excluir\n");
    printf("4. Imprimir ordenado\n");
    printf("5. Imprimir a altura da árvore\n");
    printf("6. Imprimir árvore\n");
    printf("7. Imprimir o total de elementos na árvore\n");
    printf("\n-----------------------------------------\n");
    scanf("%d", &menu);

    if (menu == 1) {
      printf("\nQual nome você deseja salvar na agenda?\n");
      scanf("%s", name);
      printf("\nE qual o telefone?\n");
      scanf("%d", &phoneNumber);

      agenda = insert(agenda, phoneNumber, name);

      printf("\nValor inserido com sucesso!\n");
    } else if (menu == 2) {
      printf("\nQual telefone você está buscando?\n");
      scanf("%d", &phoneNumber);
      findByPhoneNumber(agenda, phoneNumber);
    } else if (menu == 3) {
      printf("\nQual telefone você gostaria de remover?\n");
      scanf("%d", &phoneNumber);

      deleteNodeByPhoneNumber(agenda, phoneNumber);
      printf("\nValor removido com sucesso!\n");
    } else if (menu == 4) {
      printf("\nValores ordenados: \n");
      printOrdered(agenda);
      printf("\n");
    } else if (menu == 5) {
      printf("\nA altura da árvore é: %d \n", height(agenda));
    } else if (menu == 6) {
      printAsTree(agenda);
    } else if (menu == 7) {
      printf("\nO número total de elementos na árvore é: %d\n",
             totalElementsCount(agenda));
    }
  }

  return 0;
}

int height(Node *node) {
  if (node == NULL)
    return 0;

  int leftHeight = height(node->left);
  int rightHeight = height(node->right);

  int maxValue = getMaxValue(leftHeight, rightHeight);

  return 1 + maxValue;
}

int getMaxValue(int v1, int v2) { return (v1 > v2) ? v1 : v2; }

Node *createNode(int phoneNumber, char name[30]) {
  Node *node = (Node *)malloc(sizeof(Node));

  node->phoneNumber = phoneNumber;
  strcpy(node->name, name);
  node->left = NULL;
  node->right = NULL;
  node->height = 0;

  return node;
}

Node *rightRotation(Node *node) {
  Node *leftNode = node->left, *rightNode = leftNode->right;

  leftNode->right = node;
  node->left = rightNode;

  node->height = height(node);
  leftNode->height = height(leftNode);

  return leftNode;
}

Node *leftRotation(Node *node) {
  Node *rightNode = node->right, *leftNode = rightNode->left;

  rightNode->left = node;
  node->right = leftNode;

  node->height = height(node);
  rightNode->height = height(rightNode);

  return rightNode;
}

int getBalance(Node *node) {
  if (node == NULL)
    return 0;

  int leftHeight = height(node->left);
  int rightHeight = height(node->right);

  return leftHeight - rightHeight;
}

Node *insert(Node *node, int phoneNumber, char name[30]) {
  if (node == NULL)
    return createNode(phoneNumber, name);

  int shouldInsertLeft = phoneNumber < node->phoneNumber;
  int shouldInsertRight = phoneNumber > node->phoneNumber;

  if (shouldInsertLeft)
    node->left = insert(node->left, phoneNumber, name);
  else if (shouldInsertRight)
    node->right = insert(node->right, phoneNumber, name);
  else
    return node;

  node->height = height(node);

  node = rebalance(node, phoneNumber);

  return node;
}

Node *rebalance(Node *node, int phoneNumber) {
  int balance = getBalance(node);

  int isLeftHeavy = balance > 1;
  if (isLeftHeavy)
    return rebalanceLeft(node, phoneNumber);

  int isRightHeavy = balance < -1;
  if (isRightHeavy)
    return rebalanceRight(node, phoneNumber);

  return node;
}

Node *rebalanceLeft(Node *node, int phoneNumber) {
  int isLeftCase = phoneNumber < node->left->phoneNumber;
  if (isLeftCase)
    return rightRotation(node);

  int isLeftRightCase = phoneNumber > node->left->phoneNumber;
  if (isLeftRightCase) {
    node->left = leftRotation(node->left);

    return rightRotation(node);
  }

  return node;
}

Node *rebalanceRight(Node *node, int phoneNumber) {
  int isRightCase = phoneNumber > node->right->phoneNumber;
  if (isRightCase)
    return leftRotation(node);

  int isRightLeftCase = phoneNumber < node->right->phoneNumber;
  if (isRightLeftCase) {
    node->right = rightRotation(node->right);
    return leftRotation(node);
  }

  return node;
}

void printAsTree(Node *node) {
  int heightTree = height(node);

  printf("\nValores em formato de árvore: \n");

  for (int level = 1; level <= heightTree; level++) {
    printAsTreeRecursive(node, level);
    printf("\n");
  }
}

void printAsTreeRecursive(Node *node, int level) {
  if (node == NULL) {
    printf(". ");
    return;
  }

  if (level == 1) 
    printf("%d ", node->phoneNumber);
  else if (level > 1) {
    printAsTreeRecursive(node->left, level - 1);
    printAsTreeRecursive(node->right, level - 1);
  }
}

int totalElementsCount(Node *node) {
  if (node == NULL)
    return 0;

  int totalElementsCountLeft = totalElementsCount(node->left);
  int totalElementsCountRight = totalElementsCount(node->right);

  return 1 + totalElementsCountLeft + totalElementsCountRight;
}

void printOrdered(Node *node) {
  if (node != NULL) {
    printOrdered(node->left);

    printf("%d ", node->phoneNumber);

    printOrdered(node->right);
  }
}

Node *minValueNode(Node *node) {
  Node *current = node;

  while (current->left != NULL)
    current = current->left;

  return current;
}

Node *deleteNodeByPhoneNumber(Node *node, int phoneNumber) {
  if (node == NULL) {
    printf("\nNão foi possível remover esse número.\n");
    return node;
  }

  int shouldDeleteLeft = phoneNumber < node->phoneNumber;
  int shouldDeleteRight = phoneNumber > node->phoneNumber;
  if (shouldDeleteLeft)
    node->left = deleteNodeByPhoneNumber(node->left, phoneNumber);
  else if (shouldDeleteRight)
    node->right = deleteNodeByPhoneNumber(node->right, phoneNumber);
  else {
    int isLeftNodeNull = node->left == NULL;
    int isRightNodeNull = node->right == NULL;
    if (isLeftNodeNull || isRightNodeNull) {
      Node *temporary = node->left ? node->left : node->right;

      if (temporary == NULL) {
        temporary = node;
        node = NULL;
      } else
        *node = *temporary;

      free(temporary);
    } else {
      Node *temporary = minValueNode(node->right);

      node->phoneNumber = temporary->phoneNumber;

      node->right =
          deleteNodeByPhoneNumber(node->right, temporary->phoneNumber);
    }
  }

  if (node == NULL)
    return node;

  node->height = height(node);

  node = rebalance(node, phoneNumber);

  return node;
}

void findByPhoneNumber(Node *node, int phoneNumber) {
  if (node == NULL) {
    printf("\nNão foi possível localizar esse número.\n");
    return;
  }

  int shouldFindLeft = phoneNumber < node->phoneNumber;
  int shouldFindRight = phoneNumber > node->phoneNumber;
  if (shouldFindLeft)
    findByPhoneNumber(node->left, phoneNumber);
  else if (shouldFindRight)
    findByPhoneNumber(node->right, phoneNumber);
  else {
    printf("\n%d - %s\n", node->phoneNumber, node->name);
    return;
  }
}