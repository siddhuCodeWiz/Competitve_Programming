package Day9_trees;


/*Imagine you are a librarian organizing books on vertical shelves in a grand 
library. The books are currently scattered across a tree-like structure, where 
each book (node) has a position determined by its shelf number (column) and row 
number (level).

Your task is to arrange the books on shelves so that:
1. Books are placed column by column from left to right.
2. Within the same column, books are arranged from top to bottom (i.e., by row).
3. If multiple books belong to the same shelf and row, they should be arranged 
from left to right, just as they appear in the original scattered arrangement.

Sample Input:
-------------
3 9 20 -1 -1 15 7

Sample Output:
--------------
[[9],[3,15],[20],[7]]

Explanation:
------------
         3
       /   \
      9     20
          /    \
         15     7

Shelf 1: [9]
Shelf 2: [3, 15]
Shelf 3: [20]
Shelf 4: [7]


Sample Input-2:
---------------
3 9 8 4 0 1 7

Sample Output-2:
----------------
[[4],[9],[3,0,1],[8],[7]]

Explanation:
------------

          3
       /     \
      9       8
    /   \   /   \
   4     0 1     7

Shelf 1: [4]
Shelf 2: [9]
Shelf 3: [3, 0, 1]
Shelf 4: [8]
Shelf 5: [7]
 */



import java.util.*;

public class Program3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        
        Node root = null;
        for(int i=0; i<s.length; i++){
            root = buildTree(root, Integer.parseInt(s[i]));
        }

        System.out.println(arrangeBooks(root));

        sc.close();
    }


    public static Node buildTree(Node root, int val){
        if(root==null){
            return new Node(val);
        }

        Queue<Node> que = new LinkedList<>();
        que.add(root);
        Node curr;

        while(!que.isEmpty()){
            curr = que.poll();

            if(curr.left==null){
                curr.left = new Node(val);
                break;
            }else if(curr.right==null){
                curr.right = new Node(val);
                break;
            }else{
                if(curr.left.value!=-1) que.add(curr.left); 
                if(curr.right.value!=-1) que.add(curr.right); 
            }
        }

        return root;
    }

    public static ArrayList<ArrayList<Integer>> arrangeBooks(Node root){
        TreeMap<Integer, ArrayList<Integer>> tm = new TreeMap<>();
        helper(root, 0, tm);

        ArrayList<ArrayList<Integer>> sol = new ArrayList<>();
        for(Integer key : tm.keySet()){
            sol.add(tm.get(key));
        }
        return sol;
    }

    public static void helper(Node root,int column, TreeMap<Integer, ArrayList<Integer>> tm){
        if(root==null || root.value==-1) return;

        if(tm.containsKey(column)){
            tm.get(column).add(root.value);
        }else{
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(root.value);
            tm.put(column, arr);
        }

        helper(root.left, column-1, tm);
        helper(root.right, column+1, tm);
    }
}

class Node{
    int value;
    Node left;
    Node right;
    
    Node(int v){
        this.value = v;
        this.left = null;
        this.right = null;
    }
}

















/*
 * ChatGPT Solution
 * 
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

class Pair {
    TreeNode node;
    int column, row;
    Pair(TreeNode node, int column, int row) {
        this.node = node;
        this.column = column;
        this.row = row;
    }
}

public class VerticalOrderTraversal {
    
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        // TreeMap to store column-wise nodes (sorted by TreeMap keys)
        TreeMap<Integer, List<int[]>> columnMap = new TreeMap<>();
        
        // Queue for BFS: Stores (node, column, row)
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            TreeNode node = p.node;
            int column = p.column;
            int row = p.row;
            
            // Add the node value to the respective column list
            columnMap.putIfAbsent(column, new ArrayList<>());
            columnMap.get(column).add(new int[]{row, node.val});
            
            // Process left and right children with updated row and column values
            if (node.left != null) queue.offer(new Pair(node.left, column - 1, row + 1));
            if (node.right != null) queue.offer(new Pair(node.right, column + 1, row + 1));
        }
        
        // Sort and format the output
        List<List<Integer>> result = new ArrayList<>();
        for (List<int[]> nodes : columnMap.values()) {
            // Sort by row first, then by value if rows are the same
            nodes.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            List<Integer> columnList = new ArrayList<>();
            for (int[] n : nodes) columnList.add(n[1]);
            result.add(columnList);
        }
        
        return result;
    }
    
    // Helper function to build a binary tree from level order input
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;
        
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int i = 1;
        while (i < arr.length) {
            TreeNode curr = queue.poll();
            
            if (arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                queue.offer(curr.left);
            }
            i++;
            
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] input1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = buildTree(input1);
        System.out.println(verticalTraversal(root1)); // Output: [[9], [3, 15], [20], [7]]

        Integer[] input2 = {3, 9, 8, 4, 0, 1, 7};
        TreeNode root2 = buildTree(input2);
        System.out.println(verticalTraversal(root2)); // Output: [[4], [9], [3, 0, 1], [8], [7]]
    }
}

 */