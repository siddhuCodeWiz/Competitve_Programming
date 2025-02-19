package Day8_trees;

import java.util.*;

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        
        Node root = null;
        for(int i=0; i<s.length; i++){
            root = buildTree(root, Integer.parseInt(s[i]));
        }
        
        Node flipped = flipTree(root);
        levelOrderTraversal(flipped);
        
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
    
    
    public static Node flipTree(Node root){
        if((root.left==null && root.right==null) || root==null) return root;
        
        Node newRoot = flipTree(root.left);
        
        
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right=null;
        
        return newRoot;
    }
    
    static void levelOrderTraversal(Node root){
        if(root==null) return;
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        // int lvl = 1;
        Node curr;
        while(!que.isEmpty()){
            int si = que.size();

            for(int i=0; i<si; i++){
                curr = que.poll();
                System.out.print(curr.value+" ");
                if(curr.left != null) que.add(curr.left);
                if(curr.right != null) que.add(curr.right);
            }
            // System.out.println();
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