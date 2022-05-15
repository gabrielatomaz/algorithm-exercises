#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "josephus-problem-lib.h"

int main()
{
    Node *list = NULL;

    Soldier *soldier1 = createSoldier(1, "nome1");
    Soldier *soldier2 = createSoldier(2, "nome2");
    Soldier *soldier3 = createSoldier(3, "nome3");
    Soldier *soldier4 = createSoldier(4, "nome4");
    Soldier *soldier5 = createSoldier(5, "nome5");
    Soldier *soldier6 = createSoldier(6, "nome6");

    list = create(*soldier1); // 5, 6, 2, 1, 3, 4
    list = insertAtStart(list, *soldier2);
    list = insertAtEnd(list, *soldier3);
    list = insertAtStart(list, *soldier6);
    list = insertAtEnd(list, *soldier4);
    list = insertAtStart(list, *soldier5);

    // list = removeEvenValues(list);
    // list = removeAt(list, 2);
    // list = removeBy(list, *soldier2);
    // list = removeStart(list);

    // list = close(list);

    // Soldier soldier = findBiggestSoldier(list);
    // printf("Soldado com Id mais alto: %d - %s\n ", soldier.id, soldier.name);

    // printf("Quantidade de soldados: %d\n", size(list));

    // printf("Há soldados na lista? %s\n", isEmpty(list) ? "Não" : "Sim");

    print(list);

    return 0;
}

Node *create(Soldier soldier)
{
    Node *newNode = malloc(sizeof(Node));
    newNode->content = soldier;
    newNode->next = newNode;

    return newNode;
}

void print(Node *list)
{
    if (list == NULL) {
        printf("Não há nenhum soldado na lista.\n");
        return;
    }
    Node *current = list->next;
    do
    {
        Soldier content = current->content;
        printf("[Soldado %d: %s] -> ",
               content.id,
               content.name);
        current = current->next;
    } while (current != list->next);
}

int size(Node *list)
{
    Node *current = list;
    int size = 1;

    if (list == NULL)
        return 0;

    current = current->next;
    while (current != list)
    {
        size++;
        current = current->next;
    }

    return size;
}

Node *insertAtStart(Node *list, Soldier soldier)
{
    Node *newNode = malloc(sizeof(Node));

    newNode->content = soldier;
    newNode->next = list->next;
    list->next = newNode;

    return list;
}

Node *insertAtEnd(Node *list, Soldier soldier)
{
    Node *newNode = malloc(sizeof(Node));

    newNode->content = soldier;
    newNode->next = NULL;

    newNode->next = list->next;
    list->next = newNode;
    list = list->next;

    return list;
}

Node *insertAt(Node *list, Soldier soldier, int position)
{
    int listSize = size(list);

    if (!isPositionValid(position, listSize))
        return list;

    if (list == NULL)
        return insertAtStart(list, soldier);

    Node *newNode = create(soldier),
         *current = list;

    for (int i = 1; i != position; i++)
        current = current->next;

    newNode->next = current->next;
    current->next = newNode;

    if (position == (listSize + 1))
        list = newNode;

    return list;
}

int isEmpty(Node *list)
{
    return size(list) == 0;
}

Node *removeAt(Node *list, int position)
{
    Node *current,
        *previous = list;
    int listSize = size(list);

    if (!isPositionValid(position, listSize))
        return list;

    if (listSize == 1)
    {
        list = NULL;
        free(current);
    }

    current = list->next;
    for (int i = 1; i < position; i++)
    {
        previous = current;
        current = current->next;
    }

    previous->next = current->next;

    return list;
}

Node *removeStart(Node *list)
{
    Node *auxiliaryNode;
    if (list == NULL)
        return list;

    auxiliaryNode = list->next;
    list->next = auxiliaryNode->next;

    free(auxiliaryNode);

    return list;
}

Node *removeEvenValues(Node *list)
{
    Node *current = list->next,
         *previous;
    do
    {
        if (current->content.id % 2 == 0)
        {
            list = removeBy(list, current->content);
            current = list->next;
        }
     
        current = current->next;

    } while (current != list->next);

    return list;
}

Node *removeBy(Node *list, Soldier soldier)
{
    Node *current = list, *previous;

    if (list == NULL)
        return list;

    if (list == list->next)
    {
        if (list->content.id == soldier.id)
        {
            list = NULL;
            free(current);
        }

        return list;
    }

    do
    {
        previous = current;
        current = current->next;
        if (current->content.id == soldier.id)
        {
            previous->next = current->next;

            if (current == list)
                list = previous;

            free(current);
            current = previous->next;
        }
    } while (current != list);

    return list;
}

Node *close(Node *list)
{
    while (!isEmpty(list))
    {
        list = removeStart(list);
    }

    return list;
}

int isPositionValid(int position, int size)
{
    int isValid = position >= 1 && position <= size + 1;
    if (isValid)
        return 1;

    printf("Posição inválida!\n");
    return 0;
}

Soldier findBiggestSoldier(Node *list)
{
    Soldier result = list->content;
    Node *current = list;
    int listSize = size(list);

    for (int i = 1; i < listSize; i++)
    {
        if (current->content.id > result.id)
            result = current->content;

        current = current->next;
    }

    return result;
}

Soldier *createSoldier(int id, char name[30])
{
    Soldier *soldier = (Soldier *)malloc(sizeof(Soldier));

    if (soldier == NULL)
    {
        printf("Memória insuficiente!\n");
        exit(1);
    }

    soldier->id = id;
    strcpy(soldier->name, name);

    return soldier;
}
