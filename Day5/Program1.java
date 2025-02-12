package Day5;

/*Given the in-order and post-order traversals of a binary tree, construct 
the original binary tree. For the given Q number of queries, 
each query consists of a lower level and an upper level. 
The output should list the nodes in the order they appear in a level-wise.

Input Format:
-------------
An integer N representing the number nodes.
A space-separated list of N integers representing the similar to in-order traversal.
A space-separated list of N integers representing the similar to post-order traversal.
An integer Q representing the number of queries.
Q pairs of integers, each representing a query in the form:
Lower level (L)
Upper level (U)

Output Format:
For each query, print the nodes in order within the given depth range

Example
Input:
7
4 2 5 1 6 3 7
4 5 2 6 7 3 1
2
1 2
2 3
Output:
[1, 2, 3]
[2, 3, 4, 5, 6, 7]

Explanation:
        1
       / \
      2   3
     / \  / \
    4   5 6  7
Query 1 (Levels 1 to 2): 1 2 3
Query 2 (Levels 2 to 3): 2 3 4 5 6 7 */

import java.util.*;


public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main m = new Main(n);
        for(int i=0; i<n; i++){
            m.inorder.add(sc.nextInt());
        }
        for(int i=0; i<n; i++){
            m.postorder.add(sc.nextInt());
        }
        m.buildTree();

        int queries = sc.nextInt();

        int[][] query = new int[queries][2];

        for(int i=0; i<queries; i++){
            query[i][0] = sc.nextInt();
            query[i][1] = sc.nextInt();
        }

        ArrayList<Integer> ans;
        for(int[] curr:query){
            ans = new ArrayList<>();
            for(int j=curr[0]; j<=curr[1]; j++){
                if(m.levelOrderMap.containsKey(j)){
                    ans.addAll(m.levelOrderMap.get(j));
                }
            }
            System.out.println(ans);
        }
        sc.close();

    }
}


class Main{
    int n;
    ArrayList<Integer> inorder;
    ArrayList<Integer> postorder;
    int currInd;
    HashMap<Integer, Integer> hm;
    HashMap<Integer, ArrayList<Integer>> levelOrderMap;

    Main(int n){
        this.n = n;
        inorder = new ArrayList<>();
        postorder = new ArrayList<>();
        hm = new HashMap<>();
        levelOrderMap = new HashMap<>();
        currInd = n-1;
    }

    protected void buildTree(){
        for(int i=0; i<n; i++){
            hm.put(inorder.get(i), i);
        }

        Node root = buildTreeHelper(0, n-1);
        levelOrderTraversal(root);
    }

    private Node buildTreeHelper(int si,  int ei){
        if(si>ei || currInd<0){
            return null;
        }
        int val = postorder.get(currInd);
        currInd--;
        int ind = hm.get(val);
        // System.out.println(val);
        Node curr = new Node(val);

        curr.right = buildTreeHelper(ind+1, ei);
        curr.left = buildTreeHelper(si, ind-1);
        return curr;
    }

    private void levelOrderTraversal(Node root){
        if(root==null) return;
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        int lvl=1;
        while(!que.isEmpty()){
            int si = que.size();
            levelOrderMap.put(lvl, new ArrayList<Integer>());
            for(int i=0; i<si; i++){
                Node curr = que.poll();
                levelOrderMap.get(lvl).add(curr.data);

                if(curr.left!=null) que.add(curr.left);
                if(curr.right!=null) que.add(curr.right);
            }
            lvl++;
            // System.out.println();
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