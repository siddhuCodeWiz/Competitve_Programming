package Day8_trees;
/*In an Intelligence Agency, each senior officer supervises either two junior officers 
or none. The senior officer is assigned a clearance level equal to the higher clearance 
level of the two junior officers they supervise.

The clearance levels are represented as integer values in the range [1, 50], and multiple 
officers may have the same clearance level.

At the end, all officers (senior and junior) are collectively referred to as agents in the system.

You are provided with a hierarchical clearance level tree where each node represents 
an officer's clearance level. The tree structure follows these rules:
	- If a node has two children, its clearance level is the maximum of the two children's
	  clearance levels.
	- If a node has no children, it's clearance level is same as exists.
	- The value -1 indicates an empty (null) position.
Your task is to find the second highest clearance level among all agents in the agency. 
If no such level exists, return -2.

Input Format:
-------------
A single line of space separated integers, clearance levels of each individual.

Output Format:
--------------
Print an integer, second top agent based on rank.


Sample Input-1:
---------------
5 5 4 -1 -1 2 4

Sample Output-1:
----------------
4


Sample Input-2:
---------------
3 3 3 3 3

Sample Output-2:
----------------
-2
 */

import java.util.*;

public class Program2 {
    static int secondHighest;
    static int firstHighest;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        
        Node root = null;
        for(int i=0; i<s.length; i++){
            root = buildTree(root, Integer.parseInt(s[i]));
        }

        System.out.println(heapSearch(root));

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

    public static int heapSearch(Node root){
        if(root==null) return -2;

        firstHighest = root.value;
        secondHighest= Integer.MAX_VALUE;
        helper(root);
        int ans = secondHighest==Integer.MAX_VALUE ? -2 : secondHighest;
        return ans;
    }

    public static void helper(Node root){
        if(root==null || root.value==-1) return;
        // System.out.println("Data: "+root.value+" High: "+secondHighest);
        if(secondHighest== Integer.MAX_VALUE && root.value<firstHighest){
            secondHighest = root.value;
        } else if(root.value!=firstHighest && root.value>secondHighest ){
            secondHighest = root.value;
        }

        if(secondHighest==Integer.MAX_VALUE){
            helper(root.left);
            helper(root.right);
        }else{
            if(root.left!=null && root.left.value>=secondHighest){
                helper(root.left);
            }
            if(root.right!=null && root.right.value>=secondHighest){
                helper(root.right);
            }
        }
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