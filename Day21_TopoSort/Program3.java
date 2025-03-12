package Day21_TopoSort;

/*Imagine you're the curator of an ancient manuscript archive. Each manuscript is
assigned a unique significance score, and the archive is arranged so that every 
manuscript on the left has a lower score and every manuscript on the right has a
higher score, forming a special ordered display. Now, for an upcoming exhibition,
scholars have decided that only manuscripts with significance scores between low 
and high (inclusive) are relevant. Your task is to update the archive by removing
any manuscripts whose scores fall outside the range [low, high]. Importantly, 
while you remove some manuscripts, you must preserve the relative order of the 
remaining ones—if a manuscript was originally displayed as a descendant of another, 
that relationship should remain intact. It can be proven that there is a unique 
way to update the archive.

Display the manuscript of the updated archive. Note that the main manuscript 
(the root) may change depending on the given score boundaries.

Input format:
Line 1: space separated scores to build the manuscript archive
Line 2: two space seperated integers, low and high.

Output format:
space separated scores of the updated archive

Example 1:
input=
1 0 2
1 2
output=
1 2

Explanation:
Initial archieve:
      1
     / \
    0   2


Updated archieve:
    1
     \
      2
After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
and its right manuscript 2 remain.

Example 2:
input=
3 0 4 2 1
1 3
output=
3 2 1

Explanation:
Initial archieve:
          3
         / \
        0   4
         \
          2
         /
        1

Updated archieve:
      3
     /
    2
   /
  1
 */


import java.util.*;

class Program3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ss = sc.nextLine().split(" ");

        BST bst = new BST();
        for (String s : ss) {
            bst.addEdge(Integer.parseInt(s));  // Build BST correctly
        }

        int minn = sc.nextInt();
        int maxx = sc.nextInt();
        sc.close();

        bst.root = bst.pruneTree(bst.root, minn, maxx);  // Update root after pruning

        bst.levelOrderTraversal();  // Print result
    }
}

class BST {
    Node root;

    BST() {
        root = null;
    }

    void addEdge(int val) {
        root = insert(root, val);
    }

    private Node insert(Node root, int val) {
        if (root == null) return new Node(val);

        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);

        return root;
    }

    Node pruneTree(Node root, int minn, int maxx) {
        if (root == null) return null;

        root.left = pruneTree(root.left, minn, maxx);
        root.right = pruneTree(root.right, minn, maxx);

        if (root.val < minn) return root.right; 
        if (root.val > maxx) return root.left;  

        return root;
    }

    void levelOrderTraversal() {
        if (root == null) return; 

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.val + " ");

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }
}

class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
