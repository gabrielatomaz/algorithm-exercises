struct node {
  int phoneNumber;
  char name[30];
  struct node *left;
  struct node *right;
  int height;
};

typedef struct node Node;

void findByPhoneNumber(Node *node, int phoneNumber);
Node *deleteNodeByPhoneNumber(Node *node, int phoneNumber);
int totalElementsCount(Node *node);
void printOrdered(Node *node);
void printAsTree(Node *node);
void printAsTreeRecursive(Node *node, int level);
Node *rebalanceLeft(Node *node, int phoneNumber);
Node *rebalanceRight(Node *node, int phoneNumber);
Node* insert(Node* node, int key, char name[30]);
int getBalance(Node *node);
Node *leftRotation(Node *node);
Node *rightRotation(Node *node);
Node *createNode(int phoneNumber, char name[30]);
int getMaxValue(int v1, int v2);
Node *minValueNode(Node* node);
int height(Node *node);
Node *rebalance(Node *node, int phoneNumber);

