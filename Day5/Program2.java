package Day5;
/*Construct the binary tree from the given In-order and Pre-order. 
Find Nodes Between Two Levels in Spiral Order.
The spiral order is as follows:
-> Odd levels → Left to Right.
-> Even levels → Right to Left.

Input Format:
--------------
An integer N representing the number of nodes.
A space-separated list of N integers representing the in-order traversal.
A space-separated list of N integers representing the pre-order traversal.
Two integers:
Lower Level (L)
Upper Level (U)

Output Format:
Print all nodes within the specified levels, but in spiral order.

Example:
Input:
7
4 2 5 1 6 3 7
1 2 4 5 3 6 7
2 3

Output:
3 2 4 5 6 7

Explanation:
Binary tree structure:
        1
       / \
      2   3
     / \  / \
    4   5 6  7

Levels 2 to 3 in Regular Order:
Level 2 → 2 3
Level 3 → 4 5 6 7

Spiral Order:
Level 2 (Even) → 3 2 (Right to Left)
Level 3 (Odd) → 4 5 6 7 (Left to Right)
 */


import java.util.*;

class Program2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main m = new Main(n);

        for(int i=0; i<n; i++){
            m.inorder.add(sc.nextInt());
        }

        for(int i=0; i<n; i++){
            m.preorder.add(sc.nextInt());
        }

        m.buildTree();

        int ll = sc.nextInt();
        int ul = sc.nextInt();

        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=ll; i<=ul; i++){
            // System.out.println(m.levelOrdeMap.get(i));
            if(i%2!=0){
                if(m.levelOrdeMap.containsKey(i))
                    ans.addAll(m.levelOrdeMap.get(i));
            } else{
                if(m.levelOrdeMap.containsKey(i)){
                    ArrayList<Integer> rev = m.levelOrdeMap.get(i);
                    Collections.reverse(rev);
                    ans.addAll(rev);
                }
            }
        }
        
        for(int ele : ans){
            System.out.print(ele+" ");
        }
        sc.close();
    }
}


class Main{
    int n;
    ArrayList<Integer> inorder;
    ArrayList<Integer> preorder;
    HashMap<Integer, Integer> hm;
    HashMap<Integer, ArrayList<Integer>> levelOrdeMap;
    int currInd;

    Main(int n){
        this.n = n;
        this.inorder = new ArrayList<>();
        this.preorder = new ArrayList<>();
        hm = new HashMap<>();
        levelOrdeMap  =new HashMap<>();
        currInd=0;
    }

    protected void buildTree(){
        for(int i=0; i<n; i++){
            hm.put(inorder.get(i), i);
        }

        Node root = buildTreeHelper(0, n-1);
        // System.out.println(root.data);
        levelOrderTraversal(root);
    }

    private Node buildTreeHelper(int is, int ie){
        if(is>ie) return null;
        int val = preorder.get(currInd);
        currInd++;
        int ind = hm.get(val);

        Node root = new Node(val);
        root.left = buildTreeHelper(is, ind-1);
        root.right = buildTreeHelper(ind+1, ie);
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
                // System.out.print(curr.data);
                levelOrdeMap.get(lvl).add(curr.data);
                if(curr.left != null) que.add(curr.left);
                if(curr.right != null) que.add(curr.right);
            }
            // System.out.println();
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