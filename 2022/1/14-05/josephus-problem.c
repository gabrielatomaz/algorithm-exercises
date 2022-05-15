#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
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

    list = create(*soldier1);
    list = insertAtEnd(list, *soldier2);
    list = insertAtEnd(list, *soldier3);
    list = insertAtStart(list, *soldier6);
    list = insertAt(list, *soldier4, 5);
    list = insertAtStart(list, *soldier5);

    // orderBySelectionSort(list);

    // list = removeEvenValues(list);
    // list = removeAt(list, 2);
    // list = removeBy(list, *soldier2);
    // list = removeStart(list);

    finishList(list);

    // Soldier soldier = findBiggestSoldierId(list);
    // printf("Soldado com Id mais alto: %d - %s\n ", soldier.id, soldier.name);

    printf("Quantidade de soldados: %d\n", size(list));

    printf("Há soldados na lista? %s\n", isEmpty(list) ? "Não" : "Sim");

    // Node *lastNode = findLast(list);
    // Node *firstNode = lastNode->next;
    // orderByQuickSort(firstNode, lastNode);

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
    if (list == NULL)
    {
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
    int size = 0;

    if (list == NULL)
        return 0;

    Node *current = list->next;
    do
    {
        size++;
        current = current->next;
    } while (current != list->next);

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

Node *findLast(Node *list)
{
    Node *current,
        *previous = list;
    int listSize = size(list);

    if (listSize == 1)
        return list;

    current = list->next;
    for (int i = 1; i < listSize; i++)
    {
        current = current->next;
    }

    list = current;

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
    Node *current = list->next;

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

void finishList(Node *list)
{
    Node *current = list->next;
    do
    {
        list = removeBy(list, current->content);
        current = list->next;
        current = current->next;
    } while (current != list->next);
}

int isPositionValid(int position, int size)
{
    int isValid = position >= 1 && position <= size + 1;
    if (isValid)
        return 1;

    printf("Posição inválida!\n");
    return 0;
}

Soldier findBiggestSoldierId(Node *list)
{
    Soldier result = list->content;
    Node *current = list;
    int listSize = size(list);

    for (int i = 1; i < listSize; i++)
    {
        if (current->content.id > result.id)
        {
            result = current->content;
        }

        current = current->next;
    }

    list = removeBy(list, result);

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

Node *partition(Node *first, Node *last)
{
    Node *pivot = first;
    Node *front = first;
    Soldier temp;
    while (front != NULL && front != last)
    {
        if (front->content.id < last->content.id)
        {
            pivot = first;
            // Swap node value
            temp = first->content;
            first->content = front->content;
            front->content = temp;
            // Visit to next node
            first = first->next;
        }
        // Visit to next node
        front = front->next;
    }
    // Change last node value to current node
    temp = first->content;
    first->content = last->content;
    last->content = temp;

    return pivot;
}

void orderByQuickSort(Node *first, Node *last)
{
    clock_t begin = clock();

    if (first == last)
    {
        return;
    }

    Node *pivot = partition(first, last);
    if (pivot != NULL && pivot->next != NULL)
    {
        orderByQuickSort(pivot->next, last);
    }
    if (pivot != NULL && first != pivot)
    {
        orderByQuickSort(first, pivot);
    }

    clock_t end = clock();

    timeSpent(begin, end);
}

void orderBySelectionSort(Node *list)
{
    Node *i,
        *j;
    i = list;
    Soldier soldier;

    clock_t begin = clock();

    do
    {
        j = i->next;

        do
        {
            if (i->content.id >= j->content.id)
            {
                soldier = i->content;
                i->content = j->content;
                j->content = soldier;
            }

            j = j->next;
        } while (j != list->next);

        i = i->next;
    } while (i->next != list);

    clock_t end = clock();
    
    timeSpent(begin, end);
}

void timeSpent(double begin, double end)
{
    double timeSpent = (double)(end - begin);

    printf("Tempo gasto: %f", timeSpent);
}