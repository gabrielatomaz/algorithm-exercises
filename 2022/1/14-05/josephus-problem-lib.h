struct soldier
{
    int id;
    char name[30];
};

typedef struct soldier Soldier;

struct node
{
    struct soldier content;
    struct node *next;
};

typedef struct node Node;

Soldier *createSoldier(int id, char name[30]);
Soldier findBiggestSoldierId(Node *list);

Node *createList(Soldier soldier);
Node *destroyList(Node *list);
void printList(Node *list);

Node *insertAtStart(Node *list, Soldier soldier);
Node *insertAt(Node *list, Soldier soldier, int position);
Node *insertAtEnd(Node *list, Soldier soldier);

Node *removeStart(Node *list);
Node *removeAt(Node *list, int position);
Node *removeBy(Node *list, Soldier soldier);
Node *removeEvenValues(Node *list);

void orderBySelectionSort(Node *list);

void orderByQuickSort(Node *list);
void orderByQuickSortRecursive(Node *first, Node *last);
Node *partition(Node *first, Node *last);
Node *findLast(Node *list);

int isPositionValid(int position, int size);
int isEmpty(Node *list);
int size(Node *list);
void timeSpent(double begin, double end);
int randomNumber(int totalSoldiers);