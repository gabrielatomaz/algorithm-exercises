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

Node *create(Soldier soldier);
Node *insertAtStart(Node *list, Soldier soldier);
Node *insertAt(Node *list, Soldier soldier, int position);
Node *insertAtEnd(Node *list, Soldier soldier);
Node *removeStart(Node *list);
Node *removeAt(Node *list, int position);
Node *removeBy(Node *list, Soldier soldier);
Node *removeEvenValues(Node *list);
void *finishList(Node *list);
void print(Node *list);
Soldier findBiggestSoldierId(Node *list);
Node *orderBySelectionSort(Node *list);
void orderByQuickSort(Node *first, Node *last);
Node *partition(Node *first, Node *last);
Node *findLast(Node *list);
int isPositionValid(int position, int size);
int isEmpty(Node *list);
int size(Node *list);