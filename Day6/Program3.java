package Day6;

/*The Indian Army has established multiple military camps at strategic locations 
along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
a hierarchical structure, with a main base camp acting as the root of the network.

To fortify national security, the Government of India has planned to deploy 
a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
boundary of the network.

Structure of Military Camps:
    - Each military camp is uniquely identified by an integer ID.
    - A camp can have at most two direct connections:
        - Left connection → Represents a subordinate camp on the left.
        - Right connection → Represents a subordinate camp on the right.
    - If a military camp does not exist at a specific position, it is 
      represented by -1
	
Mission: Deploying the S.H.I.E.L.D.
Your task is to determine the list of military camp IDs forming the outer 
boundary of the military network. This boundary must be traversed in 
anti-clockwise order, starting from the main base camp (root).

The boundary consists of:
1. The main base camp (root).
2. The left boundary:
    - Starts from the root’s left child and follows the leftmost path downwards.
    - If a camp has a left connection, follow it.
    - If no left connection exists but a right connection does, follow the right connection.
    - The leftmost leaf camp is NOT included in this boundary.
3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
4. The right boundary (in reverse order):
    - Starts from the root’s right child and follows the rightmost path downwards.
    - If a camp has a right connection, follow it.
    - If no right connection exists but a left connection does, follow the left connection.
    - The rightmost leaf camp is NOT included in this boundary.


Input Format:
-------------
space separated integers, military-camp IDs.

Output Format:
--------------
Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.


Sample Input-1:
---------------
5 2 4 7 9 8 1

Sample Output-1:
----------------
[5, 2, 7, 9, 8, 1, 4]


Sample Input-2:
---------------
11 2 13 4 25 6 -1 -1 -1 7 18 9 10

Sample Output-2:
----------------
[11, 2, 4, 7, 18, 9, 10, 6, 13]


Sample Input-3:
---------------
1 2 3 -1 -1 -1 5 -1 6 7

Sample Output-3:
----------------
[1, 2, 7, 6, 5, 3]
 */

import java.util.*;

class Program3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] ss = sc.nextLine().split(" ");
        int[] arr = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            arr[i] = Integer.parseInt(ss[i]);
        }

        Node root = null;
        for (int i = 0; i < ss.length; i++) {
            root = buildTree(arr[i], root);
        }

        BoundaryTraversal bt = new BoundaryTraversal();
        bt.printBoundary(root);
        sc.close();
    }

    public static Node buildTree(int val, Node root){
        if (root == null) {
            return new Node(val);
        }

        Queue<Node> que = new LinkedList<>();
        que.add(root);

        while (!que.isEmpty()) {
            Node curr = que.poll();
            if (curr.left == null) {
                curr.left = new Node(val);
                break;
            } else if (curr.right == null) {

                curr.right = new Node(val);
                break;
            } else {
                if (curr.left.value!= -1)
                    que.add(curr.left);
                if (curr.right.value != -1)
                    que.add(curr.right);
            }
        }
        return root;
    }
}

class BoundaryTraversal{
    private List<Integer> boundary;

    public BoundaryTraversal(){
        boundary = new ArrayList<>();
    }

    public void printBoundary(Node root){
        if (root == null)
            return;

        if (root.value != -1) {
            boundary.add(root.value);
        }

        processLeftBoundary(root.left);
        processLeaves(root.left);
        processLeaves(root.right);

        processRightBoundary(root.right);

        System.out.println(boundary);
    }

    private void processLeftBoundary(Node node){
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }

        if (node.value != -1){
            boundary.add(node.value);
        }

        if (node.left != null){
            processLeftBoundary(node.left);
        } else if(node.right != null) {
            processLeftBoundary(node.right);
        }
    }

    private void processRightBoundary(Node node){
        if (node == null || (node.left == null && node.right == null)){
            return;
        }

        if (node.right != null){
            processRightBoundary(node.right);
        } else if (node.left != null){
            processRightBoundary(node.left);
        }

        if (node.value != -1) {
            boundary.add(node.value);
        }
    }

    private void processLeaves(Node node){
        if (node == null) {
            return;
        }

        if (node.left ==null && node.right == null && node.value!= -1){
            boundary.add(node.value);
            return;
        }
        processLeaves(node.left);
        processLeaves(node.right);
    }
}

class Node {
    int value;
    Node left;
    Node right;

    Node(int val) {
        this.value = val;
        this.left = null;
        this.right = null;
    }
}