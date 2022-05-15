struct soldier {
    int id;
    char name[30];
};

typedef struct soldier Soldier;

struct node {
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
Node *close(Node *list);
void print(Node *list);
Soldier findBiggestSoldier(Node *list);
Node *removeEvenValues(Node *list);
Node *orderBySelectionSort(Node *list);
Node *deleteByValue(Node *list, Soldier soldier);
void orderByQuickSort();
int isPositionValid(int position, int size);
int isEmpty(Node *list);
int size(Node *list);