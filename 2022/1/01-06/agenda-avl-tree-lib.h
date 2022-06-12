struct node {
  int phoneNumber;
  char name[30];
  struct node *left;
  struct node *right;
  int height;
};

typedef struct node Node;

int getMaxValue(int v1, int v2);
int height(Node *node);
Node *createNode(int phoneNumber, char name[30]);
Node *rightRotation(Node *node);
Node *leftRotation(Node *node);

int getBalance(Node *node);
Node* insert(Node* node, int key, char name[30]);
Node *rebalance(Node *node, int phoneNumber);
Node *rebalanceLeft(Node *node, int phoneNumber);
Node *rebalanceRight(Node *node, int phoneNumber);
void printAsTree(Node *node);

void printAsTreeRecursive(Node *node, int level);
int totalElementsCount(Node *node);
void printOrdered(Node *node);
Node *minValueNode(Node* node);
Node *deleteNodeByPhoneNumber(Node *node, int phoneNumber);
void findByPhoneNumber(Node *node, int phoneNumber);