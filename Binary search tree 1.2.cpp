#include <iostream>
using namespace std;

struct node {
    int key;
    struct node* left, * right;

public:
    //CONSTRUCTOR TO INITIALIZE THE NODE
    node(int key) {
		this->key = key;
		left = right = nullptr;
	}
};

// Inorder traversal
void traverseInOrder(struct node* root) {
    //NO NEED TO TRAVESE IF ROOT IS NULL
    if (root == nullptr) return;
    // TRAVERSE THE LEFT SUBTREE
    if (root->left != nullptr) {
        traverseInOrder(root->left);
    }
    //PRINTING THE ROOT
    cout << root->key << " ";
    //TRAVERSE THE RIGHT SUBTREE
    if (root->right != nullptr) {
        traverseInOrder(root->right);
    }
}

// Insert a node
struct node* insertNode(struct node* root, int key) {
    //THIS IS THE BASE CASE OF INSERT NODE FUNCTION
    //IF THE CORRECT NODE IS NULL THEN INSERT THE NEW NODE TO THAT PLACE
    if ( root == NULL) {
        struct node* newNode = new node(key);
        return newNode;
    }

    //IF THE INSERTING VALUE IS GREATER THAN THE KEY VALUE, GOING TO THE LEFT SIDE OF THE NODE
    if (root->key > key) {
        root->left = insertNode(root->left, key);
    }
    //IF THE INSERTING VALUE IS LESS THAN THE KEY VALUE, GOING TO THE RIGHT SIDE OF THE NODE
    else if (root->key < key) {
        root->right = insertNode(root->right, key);
    }
    return root;
}

//FUNTION TO GET THE MINIMUM VAL IN A 


// Deleting a node
struct node* deleteNode(struct node* root, int key) {
    //IF THE ROOT IS NULL THERE'S NOTHING TO DELETE
    if (root == NULL) return root;

    //THESE WILL RECURSIVELY HAPPEN UNTILL WE GET THE CORRECE NODE TO DELETE
    if (root->key < key) {
        root->right = deleteNode(root->right, key);
    }
    else if (root->key > key) {
        root->left = deleteNode(root->left, key);
    }

    //THERE ARE THREE CASES TO CONSIDER WHEN DOING DELETION
    // CASE 01: IF BOTH CHILDREN ARE NULL
    else if (root->left == nullptr and root->right == nullptr) {
        delete root;
        return NULL;
    }

    //CASE 02: ONLY ONE OF THE CHILDREN IS NULL
    else if (root->right == nullptr and root->left != nullptr) {
        struct node* tempNode = root->left;
        delete root;
        return tempNode;
    }
    else if (root->left == nullptr and root->right != nullptr) {
        struct node* tempNode = root->right;
        delete root;
        return tempNode;
    }

    //CASE 03 : NEITHER OF THE CHILDREN ARE NULL
    else {
        /*IF WE GET THE MINIMAUM VALUE OF THE RIGHT SIDE OF THE ROOT, IT WILL BE STILL GREATER THAN THE 
        VALUES IN THE LEFT SIDE OF THE ROOT. SO WE CAN REPLACE THE ROOT WITH THAT WITH LOWEST VALUE AND DELETE
        ITS DUPLICATE IN THE RIGTH SIDE WITHOUT DAMAGING THE ORDER OF THE BINARY TREE*/
        struct node* tempNode = root->right;

        //GETTING THE MINIMUM VALUE OF THE RIGHT SIDE
        while (tempNode->left != nullptr) {
            tempNode = tempNode->left;
        }

        //DUPLICATING THE MIN VALUE
        root->key = tempNode->key;
        //DELETING THE RIGHT SIDE DUPLICATE
        root->right = deleteNode(root->right, tempNode->key);
    }
    return root;
}

// Driver code
int main() {
    struct node* root = NULL;

    int operation;
    int operand;
    cin >> operation;

    while (operation != -1) {
        switch (operation) {
        case 1: // insert
            cin >> operand;
            root = insertNode(root, operand);
            cin >> operation;
            break;
        case 2: // delete
            cin >> operand;
            root = deleteNode(root, operand);
            cin >> operation;
            break;
        default:
            cout << "Invalid Operator!\n";
            return 0;
        }
    }

    traverseInOrder(root);
}
