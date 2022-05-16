#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

#include "josephus-problem-lib.h"

int main()
{
	Node *soldiers = NULL;
	int totalSoldiers = 5, soldierId;
	char soldierName[30];

	printf("\n------------------------Problema de Josephus------------------------\n");

	printf("\nQuantos soldados há no circulo?\n");
	scanf("%d", &totalSoldiers);

	for (int i = 0; i < totalSoldiers; i++)
	{
		int soldierId = i + 1;
		char soldierName[30];

		printf("[SOLDADO %d]\n", soldierId);

		printf("Qual o nome do soldado?\n");
		scanf("%s", soldierName);
		printf("\n");

		Soldier *soldier = createSoldier(soldierId, soldierName);

		soldiers = insertAtEnd(soldiers, *soldier);
	}

	// int stabNumber = randomNumber(totalSoldiers);
	int stabNumber = 2, counter = 0;
	printf("\nSituação atual da lista: \n");
	printList(soldiers);
	while (size(soldiers) != 1)
	{
		if (stabNumber == 0)
			soldiers = removeStart(soldiers);

		soldiers = removeAt(soldiers, stabNumber + counter);

		counter++;

		printf("\nSituação atual da lista: \n");
		printList(soldiers);
	}

	printf("\nNúmero aleatório gerado: %d\n", stabNumber);

	printList(soldiers);

	printf("\n------------------------Problema de Josephus------------------------\n");

	// Node *list = NULL;

	// Soldier *soldier1 = createSoldier(1, "nome1");
	// Soldier *soldier2 = createSoldier(2, "nome2");
	// Soldier *soldier3 = createSoldier(3, "nome3");
	// Soldier *soldier4 = createSoldier(4, "nome4");
	// Soldier *soldier5 = createSoldier(5, "nome5");
	// Soldier *soldier6 = createSoldier(6, "nome6");
	// Soldier *soldier7 = createSoldier(7, "nome7");

	// list = insertAtEnd(list, *soldier2);
	// list = insertAtEnd(list, *soldier3);
	// list = insertAtStart(list, *soldier6);
	// list = insertAt(list, *soldier4, 2);
	// list = insertAtStart(list, *soldier7);
	// list = insertAtEnd(list, *soldier5);

	// orderBySelectionSort(list);

	// list = removeEvenValues(list);
	// list = removeAt(list, 2);
	// list = removeAt(list, 8);
	// list = removeBy(list, *soldier2);
	// list = removeStart(list);

	// list = destroyList(list);

	// Soldier soldier = findBiggestSoldierId(list);
	// printf("Soldado com Id mais alto: %d - %s\n ", soldier.id, soldier.name);

	// printf("Quantidade de soldados: %d\n", size(list));

	// printf("Há soldados na lista? %s\n", isEmpty(list) ? "Não" : "Sim");

	// orderByQuickSort(list);

	// printList(list);

	return 0;
}

Node *createList(Soldier soldier)
{
	Node *newNode = malloc(sizeof(Node));
	newNode->content = soldier;
	newNode->next = newNode;

	return newNode;
}

void printList(Node *list)
{
	printf("\n");

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

	printf("\n");
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
	if (isEmpty(list))
		return createList(soldier);

	Node *newNode = malloc(sizeof(Node));

	newNode->content = soldier;
	newNode->next = list->next;
	list->next = newNode;

	return list;
}

Node *insertAtEnd(Node *list, Soldier soldier)
{
	if (isEmpty(list))
		return createList(soldier);

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
	if (isEmpty(list))
		return createList(soldier);

	int listSize = size(list);

	if (!isPositionValid(position, listSize))
		return list;

	if (list == NULL)
		return insertAtStart(list, soldier);

	Node *newNode = createList(soldier),
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
	Node *current, *previous = list;
	int sizeList = size(list), i;

	if (sizeList == 1)
	{
		list = NULL;

		return list;
	}

	current = list->next;
	for (i = 1; i < position; i++)
	{
		previous = current;
		current = current->next;
	}

	printf("\nSoldado %d - %s foi removido.\n",
		   current->content.id, current->content.name);

	previous->next = current->next;

	if (current == list)
		list = previous;

	free(current);

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
	if (list == NULL)
		return list;

	if (list->next == list)
	{
		free(list);
		list = NULL;

		return list;
	}

	Node *temporaryNode = list->next;

	list->next = temporaryNode->next;
	free(temporaryNode);
	temporaryNode = NULL;

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

Node *destroyList(Node *list)
{
	int listSize = size(list);
	do
	{
		list = removeAt(list, listSize);

		listSize--;

	} while (listSize >= 1);

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

void orderByQuickSort(Node *list)
{
	clock_t begin = clock();

	Node *lastNode = findLast(list);
	Node *firstNode = lastNode->next;

	orderByQuickSortRecursive(firstNode, lastNode);

	clock_t end = clock();

	timeSpent(begin, end);
}

void orderByQuickSortRecursive(Node *first, Node *last)
{
	if (first == last)
	{
		return;
	}

	Node *pivot = partition(first, last);
	if (pivot != NULL && pivot->next != NULL)
	{
		orderByQuickSortRecursive(pivot->next, last);
	}
	if (pivot != NULL && first != pivot)
	{
		orderByQuickSortRecursive(first, pivot);
	}
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

int randomNumber(int totalSoldiers)
{
	srand(time(NULL));

	return rand() % ((totalSoldiers * 2) + 1);
}

void timeSpent(double begin, double end)
{
	double timeSpent = (double)(end - begin);

	printf("Tempo gasto: %f\n", timeSpent);
}