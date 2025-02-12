package Day5;

/*Construct Tree from the given Level-order and In-order.
Compute the Depth and Sum of the Deepest nodes in the Binary tree

Input Format:
-------------
An integer N representing the number of nodes.
A space-separated list of N integers representing the in-order traversal.
A space-separated list of N integers representing the level-order traversal.

Output Format:
--------------
Print two values:
->The maximum number of levels.
->The sum of all node values at the deepest level.

Example:
-------------
Input:
11
7 8 4 2 5 9 11 10 1 6 3
1 2 3 4 5 6 7 9 8 10 11

Output:
6 11

Explanation:
The binary tree structure:
           1
         /   \
       2       3
      / \     /
     4   5   6
    /     \   
   7       9
    \       \
     8      10
            /
          11
Maximum Depth: 6
nodes at the Deepest Level (6): 11
Sum of nodes at Level 6: 11
 */
import java.util.*;

public class Program3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main m = new Main(n);
        for(int i=0; i<n; i++){
            m.inorder.add(sc.nextInt());
        }
        for(int i=0; i<n; i++){
            m.levelorder.add(sc.nextInt());
        }

        m.buildTree();

        int ss= 0;
        int hl = 0;
        Map.Entry<Integer, ArrayList<Integer>> lastEntry = null;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : m.levelOrdeMap.entrySet()) {
            lastEntry = entry;
        }

        if(lastEntry!=null){
            hl = lastEntry.getKey();
            for(int ele : lastEntry.getValue()){
                ss+=ele;
            }
        }

        System.out.println(hl+" "+ss);
        sc.close();
    }
}

class Main{
    int n;
    ArrayList<Integer> inorder;
    ArrayList<Integer> levelorder;
    int currInd;
    HashMap<Integer, Integer> hm;
    LinkedHashMap<Integer, ArrayList<Integer>> levelOrdeMap;

    Main(int n){
        this.n = n;
        this.inorder = new ArrayList<>();
        this.levelorder = new ArrayList<>();
        this.hm = new HashMap<>();
        this.levelOrdeMap = new LinkedHashMap<>();
        currInd =0;
    }

    public void buildTree(){
        for(int i=0; i<n; i++){
            hm.put(inorder.get(i), i);
        }

        Node root = buildTreeHelper(0, n-1, levelorder);
        levelOrderTraversal(root);
    }

    private Node buildTreeHelper(int is, int ie, List<Integer> levelNodes) {
        if (is > ie || levelNodes.isEmpty()) return null;

        // Find the first node in level order that is in the given inorder range
        int rootVal = -1;
        for (int val : levelNodes) {
            if (hm.get(val) >= is && hm.get(val) <= ie) {
                rootVal = val;
                break;
            }
        }
        if (rootVal == -1) return null;  // Safety check

        Node root = new Node(rootVal);
        int rootIdx = hm.get(rootVal);

        // Split level order into left and right children
        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();

        for (int val : levelNodes) {
            if (hm.get(val) < rootIdx) leftSubtree.add(val);
            else if (hm.get(val) > rootIdx) rightSubtree.add(val);
        }

        // Build left and right subtrees
        root.left = buildTreeHelper(is, rootIdx - 1, leftSubtree);
        root.right = buildTreeHelper(rootIdx + 1, ie, rightSubtree);

        return root;
    }

    private void levelOrderTraversal(Node root){
        if(root==null) return;
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        int lvl = 1;
        Node curr;
        while(!que.isEmpty()){
            int si = que.size();
            levelOrdeMap.put(lvl, new ArrayList<>());

            for(int i=0; i<si; i++){
                curr = que.poll();
                System.out.print(curr.data+" ");
                levelOrdeMap.get(lvl).add(curr.data);
                if(curr.left != null) que.add(curr.left);
                if(curr.right != null) que.add(curr.right);
            }
            System.out.println();
            lvl++;
        }
    }
}

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}