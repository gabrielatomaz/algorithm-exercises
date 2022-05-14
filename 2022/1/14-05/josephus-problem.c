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

    list = insertAtStart(list, *soldier1); // 4 5 3 1 6
    list = insertAtStart(list, *soldier3);
    list = insertAtStart(list, *soldier4);
    list = insertAtEnd(list, *soldier6);
    list = insertAt(list, *soldier5, 2);
    list = removeAt(list, 1);
    

    int id = findBiggest(list).id;

    printf("%d - SOLDADO\n", id);
    print(list);
    int s = size(list);
    int empty = isEmpty(list);
    printf("size: %d\n", s);
    printf("isEmpty: %s", empty ? "true" : "false");

    return 0;
}

Node *create(Soldier soldier)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->content = soldier;

    return newNode;
}

void print(Node *list)
{
    Node *current = list;
    int listSize = size(list);

    if (list != NULL)
    {
        do
        {
            current = current->next;
            Soldier content = current->content;
            printf("Soldado %d: %s\n",
                   content.id,
                   content.name);
        } while (current != list);
    }
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
    Node *newNode = create(soldier);

    if (list == NULL)
    {
        list = newNode;
        newNode->next = newNode;

        return list;
    }

    newNode->next = list->next;
    list->next = newNode;

    return list;
}

Node *insertAtEnd(Node *list, Soldier soldier)
{
    Node *newNode = insertAtStart(list, soldier);

    return newNode->next;
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
    return list == NULL;
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
        free(current);

        close(list);
    }

    current = list->next;
    for (int i = 1; i < position; i++)
    {
        previous = current;
        current = current->next;
    }

    previous->next = current->next;

    if (current == list)
        list = previous;

    free(current);

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
    Node *current,
        *previous = list;
    int listSize = size(list);

    current = list->next;


    previous->next = current->next;

    free(current);

    return list;
}

Node *close(Node *list)
{
    list = NULL;
    
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

Soldier findBiggest(Node *list)
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
