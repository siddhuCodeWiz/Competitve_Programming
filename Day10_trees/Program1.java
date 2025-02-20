package Day10_trees;

/*You are a gardener designing a beautiful floral pathway in a vast botanical 
garden. The garden is currently overgrown with plants, trees, and bushes 
arranged in a complex branching structure, much like a binary tree. Your task 
is to carefully prune and rearrange the plants to form a single-file walking 
path that visitors can follow effortlessly.

To accomplish this, you must flatten the garden’s layout into a linear sequence 
while following these rules:
    1. The garden path should maintain the same PlantNode structure, 
       where the right branch connects to the next plant in the sequence, 
       and the left branch is always trimmed (set to null).
    2. The plants in the final garden path should follow the same arrangement 
       as a pre-order traversal of the original garden layout. 

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print the list.


Sample Input:
-------------
1 2 5 3 4 -1 6

Sample Output:
--------------
1 2 3 4 5 6


Explanation:
------------
input structure:
       1
      / \
     2   5
    / \    \
   3   4    6
   
output structure:
	1
	 \
	  2
	   \
		3
		 \
		  4
		   \
			5
			 \
			  6
 */

import java.util.*;


public class Program1 {
    public static void flatten(PlantNode root) {
        if (root == null)
        return;
        
        Stack<PlantNode> stack = new Stack<>();
        stack.push(root);
        PlantNode prev = null;
        
        while (!stack.isEmpty()) {
            PlantNode curr = stack.pop();
            
            if (prev != null) {
                prev.right = curr;
                prev.left = null;
            }
            
            if (curr.right != null)
            stack.push(curr.right);
            if (curr.left != null)
            stack.push(curr.left);
            
            prev = curr;
        }
    }
    
    public static void printFlattenedTree(PlantNode root) {
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.right;
        }
    }
    
    public static PlantNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == -1)
        return null;
        
        Queue<PlantNode> queue = new LinkedList<>();
        PlantNode root = new PlantNode(arr[0]);
        queue.offer(root);
        int i = 1;
        
        while (!queue.isEmpty() && i < arr.length) {
            PlantNode curr = queue.poll();
            if (arr[i] != -1) {
                curr.left = new PlantNode(arr[i]);
                queue.offer(curr.left);
            }
            i++;
            
            if (i < arr.length && arr[i] != -1) {
                curr.right = new PlantNode(arr[i]);
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        Integer[] arr = Arrays.stream(input).map(Integer::parseInt).toArray(Integer[]::new);
        
        PlantNode root = buildTree(arr);
        flatten(root);
        printFlattenedTree(root);
        sc.close();
    }
}

class PlantNode {
    int val;
    PlantNode left, right;

    PlantNode(int x) {
        val = x;
    }
}