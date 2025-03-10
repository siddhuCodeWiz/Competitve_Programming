package Day20_TopologicalSort;

/*Imagine you're the chief curator at a renowned museum that houses a rare collection 
of ancient artifacts. These artifacts are arranged in a special display that 
follows a strict rule: any artifact positioned to the left of another has a lower 
value, and any artifact on the right has a higher value. 

Your task is to transform this display into what is known as a "Greater Artifact 
Display." In this new arrangement, each artifact’s new value will be its original 
value plus the sum of the values of all artifacts that are more valuable than it.

Example 1:
----------
input=
4 2 6 1 3 5 7
output=
22 27 13 28 25 18 7

Explanation:
Input structure 
       4
      / \
     2   6
    / \ / \
   1  3 5  7

Output structure:
        22
      /   \
     27   13
    / \   / \
   28 25 18  7

Reverse updates:
- Artifact 7: 7
- Artifact 6: 6 + 7 = 13
- Artifact 5: 5 + 13 = 18
- Artifact 4: 4 + 18 = 22
- Artifact 3: 3 + 22 = 25
- Artifact 2: 2 + 25 = 27
- Artifact 1: 1 + 27 = 28
 */

import java.util.*;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ss = sc.nextLine().split(" ");

        BST bst = new BST();
        for(int i=0; i<ss.length; i++){
            bst.addEdge(Integer.parseInt(ss[i]));
        }

        bst.representArtifact(bst.root);

        bst.levelOrderTraversal();

        sc.close();
    }

    
}


class BST{
    Node root;
    int sum;

    BST(){
        sum = 0;
    }

    void addEdge(int val){
        if(root==null ){
            root = new Node(val);
            return;
        }

        Node temp = root;
        Node last =null;
        while(temp!=null){
            last = temp;
            if(temp.val != -1 && val > temp.val){
                temp = temp.right;
            }else if(temp.val != -1 && val < temp.val){
                temp=temp.left;
            }
        }

        if(val > last.val){
            last.right = new Node(val);
        }else{
            last.left = new Node(val);
        }
    }

    void representArtifact(Node root){
        if(root==null || root.val == -1) return;

        representArtifact(root.right);
        sum+=root.val;
        root.val=sum;
        representArtifact(root.left);
        // System.out.print(root.val+" ");
    }

    void levelOrderTraversal(){
        Node temp = root;
        Queue<Node> que = new LinkedList<>();

        que.add(temp);
        while(!que.isEmpty()){
            Node popped = que.poll();
            System.out.print(popped.val+" ");
            if(popped.left!=null && popped.left.val!=-1){
                que.add(popped.left);
            }
            if(popped.right!=null && popped.right.val!=-1){
                que.add(popped.right);
            }
        }
    }
}

class Node{
    int val;
    Node left;
    Node right;

    Node(int val){
        this.val = val;
        this.left=null;
        this.right=null;
    }
}