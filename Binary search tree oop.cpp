#include <iostream>

using namespace std;

class Node {
private:
    int data;
    Node* leftChild;
    Node* rightChild;

public:
    Node(int n) {
        data = n;
        leftChild = nullptr;
        rightChild = nullptr;
    }

    void insert(int n) {
       
        if (data > n) {
            if (leftChild == nullptr) {
                leftChild = new Node(n);
            }
            else {
                leftChild->insert(n);
            }
        }
        if (data < n) {
            if (rightChild == nullptr) {
                rightChild = new Node(n);
            }
            else {
                rightChild->insert(n);
            }
        }

    }

    void traverseInOrder() {
        if (leftChild != nullptr) {
            leftChild->traverseInOrder();
        }
        cout << data << " ";
        if (rightChild != nullptr) {
            rightChild->traverseInOrder();
        }
    }


};

class BinarySearchTree {

private:

    Node* root;

public:

    BinarySearchTree() {
        root = nullptr;
    }

    void insert(int n) {
        if (root == nullptr) {
            root = new Node(n);
            return;
        }
        else {
            root->insert(n);
        }
    }

    void traverseInOrder() {
        if (root != nullptr) {
            root->traverseInOrder();
        }
    }
};



int main()
{
    BinarySearchTree tree1;

    tree1.insert(25);
    tree1.insert(20);
    tree1.insert(15);
    tree1.insert(27);
    tree1.insert(30);
    tree1.insert(29);
    tree1.insert(26);
    tree1.insert(22);
    tree1.insert(32);

    tree1.traverseInOrder();

    return 0;
}

