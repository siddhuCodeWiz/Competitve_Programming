package Day7_treesAndSubnetting;

/*Bubloo is working with computer networks, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) 
is uniquely identified by an integer value.

Bubloo has been assigned an important task: find the shortest communication 
path (in terms of network hops) between two specific servers in the network.

Network Structure:
------------------
The network of servers follows a binary tree topology.
Each server (node) has a unique identifier (integer).
If a server does not exist at a certain position, it is represented as '-1' (NULL).

Given the root of the network tree, and two specific server IDs (E1 & E2), 
determine the minimum number of network hops (edges) required to 
communicate between these two servers.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
4 8

Sample Output-1:
----------------
4

Explanation:
------------
The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


Sample Input-2:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
6 6

Sample Output-2:
----------------
0

Explanation:
------------
No edegs between 6 and 6.
 */

import java.util.*;

class Program1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        
        Node root = null;
        for(int i=0; i<s.length; i++){
            root = buildTree(root, Integer.parseInt(s[i]));
        }

        Main m = new Main(sc.nextInt(), sc.nextInt());

        Node lca = m.LCA(root);
        if(lca==null) System.out.println(-1);
        else System.out.println(m.findDistance(lca, 0));

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

    public static void LCA(Node root, int val1, int val2){

    }
}

class Main{
    int v1;
    int v2;

    Main(int v1, int v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    Node LCA(Node root){
        if(root==null){
            return null;
        }

        if(root.value==v1 || root.value==v2){
            return root;
        }

        Node left = LCA(root.left);
        Node right = LCA(root.right);

        if(left!=null && right!=null) return root;
        return (left != null) ? left : right;
    }

    int findDistance(Node root, int level){
        if(root==null) return 0;
        if(root.value==v1 || root.value==v2) return level;

        return findDistance(root.left, level+1)+findDistance(root.right, level+1);

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