/*Sure Siddharth! Here are **concise and clear notes on AVL Trees**, great for revision and interviews:

---

## 🌳 AVL Trees – Notes

### 🔹 What is an AVL Tree?

* AVL Tree is a **self-balancing Binary Search Tree (BST)**.
* Named after **Adelson-Velsky and Landis** (inventors).
* For every node, the **difference in height** of the left and right subtrees (called **Balance Factor**) is **at most 1**.

---

### 🔹 Balance Factor

$\text{Balance Factor} = \text{Height of Left Subtree} - \text{Height of Right Subtree}$

* Valid balance factor values: `-1, 0, +1`
* If balance factor is outside this range, the tree needs **rebalancing**.

---

### 🔹 Rotations to Rebalance

There are **4 types of rotations** to fix imbalance:

#### 1. **LL (Left-Left) Rotation**

* Imbalance occurs in the **left subtree of left child**.
* Fix: **Right Rotation**.

#### 2. **RR (Right-Right) Rotation**

* Imbalance in the **right subtree of right child**.
* Fix: **Left Rotation**.

#### 3. **LR (Left-Right) Rotation**

* Imbalance in **right subtree of left child**.
* Fix: **Left Rotation** on child → then **Right Rotation** on parent.

#### 4. **RL (Right-Left) Rotation**

* Imbalance in **left subtree of right child**.
* Fix: **Right Rotation** on child → then **Left Rotation** on parent.

---

### 🔹 AVL Tree Operations (with balancing)

#### 1. **Insertion**

* Same as BST insertion.
* After insertion, backtrack and check balance factor.
* Apply necessary rotation(s) to balance the tree.

#### 2. **Deletion**

* Same as BST deletion.
* After deletion, check balance factor for each ancestor node.
* Apply rotation(s) to restore AVL property.

---

### 🔹 Time Complexities

| Operation | Time Complexity |
| --------- | --------------- |
| Search    | O(log n)        |
| Insert    | O(log n)        |
| Delete    | O(log n)        |

Due to self-balancing, AVL trees always maintain **logarithmic height**.

---

### 🔹 AVL vs BST

| Property           | BST            | AVL Tree                    |
| ------------------ | -------------- | --------------------------- |
| Balance            | Not guaranteed | Strictly balanced           |
| Worst-case Height  | O(n)           | O(log n)                    |
| Insertion/Deletion | Faster         | Slower (due to rebalancing) |
| Search             | Can be slow    | Always fast (O(log n))      |

---

### 🔹 Use Cases

* When **frequent search** is needed and **data is dynamic** (insert/delete often).
* Suitable for **memory-constrained** systems where keeping height low is critical.

---

Let me know if you'd like a **code implementation** in Java, Python, or C++!
 */

class AVLtrees{

    // Node structure
    class Node{
        int key;
        int height;
        Node left, right;

        Node(int d){
            this.key = d;
            this.height = 1;
            left=null;
            right=null;
        }
    }

    Node root;

    int height(Node n){
        return n==null ? 0 : n.height;
    }

    int getBalance(Node n){
        return n==null ? 0 : height(n.left)-height(n.right);
    }

    // Utility: Right rotate subtree rooted with y
    Node rightRotate(Node y){
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right))+1;
        x.height = Math.max(height(x.left), height(x.right))+1;

        return x;
    }

    // Utility: Left rotate subtree rooted with x
    Node leftRotate(Node x){
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right))+1;
        y.height = Math.max(height(y.left), height(y.right))+1;

        return y;
    }

    // Insert a key into AVL tree
    Node insert(Node node, int key){
        // Normal BST insertion

        if(node==null){
            return new Node(key);
        }

        if(key < node.key){
            node.left = insert(node.left, key);
        } else if(key > node.key){
            node.right = insert(node.right, key);
        } else{ // Duplicate keys not allowed
            return node;
        }

        // Update height
        node.height = 1+Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Perform rotation if needed
        // LL
        if (balance > 1 && key < node.left.key) //Note: We have to check key < node.left.key this in order to differentiate between LL and LR rotation
            return rightRotate(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return unchanged node
        return node;
    }

    // Find node with minimum value
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete a key from AVL tree
    Node delete(Node root, int key){
        // Normal BST deletion
        if(root==null){
            return root;
        }

        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            // One or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;

                if (temp == null) {
                    // No child
                    root = null;
                } else {
                    // One child
                    root = temp;
                }
            } else {
                // Two children: get inorder successor
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }

        // If the tree had only one node
        if (root == null)
            return root;

        // Update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // Get balance factor
        int balance = getBalance(root);

        // Perform rotations
        // LL
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // LR
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RR
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // RL
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // In-order traversal (sorted order)
    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    // Public wrappers
    public void insert(int key) {
        root = insert(root, key);
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public void displayInOrder() {
        inOrder(root);
        System.out.println();
    }
    public static void main(String[] args) {
        AVLtrees tree = new AVLtrees();

        // Inserting nodes
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("Inorder traversal of AVL tree:");
        tree.displayInOrder();

        // Deleting node
        tree.delete(20);
        System.out.println("After deleting 20:");
        tree.displayInOrder();
    }
}