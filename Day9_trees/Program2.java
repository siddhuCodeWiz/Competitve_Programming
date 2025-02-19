package Day9_trees;

/*Balbir Singh is working with Binary Trees.
The elements of the tree is given in the level order format.
Balbir has a task to split the tree into two parts by removing only one edge
in the tree, such that the product of sums of both the splitted-trees should be maximum.

You will be given the root of the binary tree.
Your task is to help the Balbir Singh to split the binary tree as specified.
print the product value, as the product may be large, print the (product % 1000000007)
	
NOTE: 
Please do consider the node with data as '-1' as null in the given trees.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6

Sample Output-1:
----------------
110

Explanation:
------------
if you split the tree by removing edge between 1 and 4, 
then the sums of two trees are 11 and 10. So, the max product is 110.


Sample Input-2:
---------------
3 2 4 3 2 -1 6

Sample Output-2:
----------------
100
 */
import java.util.*;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        
        Node root = null;
        for(int i=0; i<s.length; i++){
            root = buildTree(root, Integer.parseInt(s[i]));
        }

        System.out.println(splitTree(root));

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

    private static long splitTree(Node root){
        if(root==null || root.value==-1) return 0;

        ArrayList<Long> arr = new ArrayList<>();
        long ts=computeTotalSum(root, arr);
        // System.out.println(arr);
        long sol = 0;
        for(long num : arr){
            sol=Math.max(sol, num*(ts-num));
        }

        return sol;
    }

    private static long computeTotalSum(Node root, ArrayList<Long> arr) {
        if (root == null || root.value==-1) return 0;
        long s = root.value + computeTotalSum(root.left, arr) + computeTotalSum(root.right, arr);
        arr.add(s);
        return s;
    }
}

class Node {
    int value;
    Node left, right;
    Node(int x) { value = x; }
}




// import java.util.*;



// // public class MaxProductSplitTree {
    
//     static final int MOD = 1000000007;
//     static long maxProduct = 0;
    
//     // Step 1: Calculate total sum of the tree
    

//     // Step 2: DFS to calculate subtree sums and find the max product
//     private static long findMaxProduct(TreeNode root, long totalSum) {
//         if (root == null) return 0;
        
//         // Compute sum of the current subtree
//         long subtreeSum = root.val + findMaxProduct(root.left, totalSum) + findMaxProduct(root.right, totalSum);
        
//         // Potential split: (totalSum - subtreeSum) * subtreeSum
//         long product = subtreeSum * (totalSum - subtreeSum);
//         maxProduct = Math.max(maxProduct, product);
        
//         return subtreeSum;
//     }

//     // Build tree from level order input
//     private static TreeNode buildTree(Integer[] arr) {
//         if (arr == null || arr.length == 0 || arr[0] == null) return null;
        
//         TreeNode root = new TreeNode(arr[0]);
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(root);
        
//         int i = 1;
//         while (i < arr.length) {
//             TreeNode curr = queue.poll();
            
//             if (arr[i] != null) {
//                 curr.left = new TreeNode(arr[i]);
//                 queue.offer(curr.left);
//             }
//             i++;
            
//             if (i < arr.length && arr[i] != null) {
//                 curr.right = new TreeNode(arr[i]);
//                 queue.offer(curr.right);
//             }
//             i++;
//         }
//         return root;
//     }

//     public static int maxProduct(TreeNode root) {
//         if (root == null) return 0;
        
//         // Step 1: Compute the total sum of the tree
//         long totalSum = computeTotalSum(root);
        
//         // Step 2: Find max product using DFS
//         findMaxProduct(root, totalSum);
        
//         return (int)(maxProduct % MOD);
//     }

//     public static void main(String[] args) {
//         // Sample Input 1
//         Integer[] input1 = {1, 2, 4, 3, 5, 6};
//         TreeNode root1 = buildTree(input1);
//         System.out.println(maxProduct(root1)); // Output: 110

//         // Sample Input 2
//         Integer[] input2 = {3, 2, 4, 3, 2, null, 6};
//         TreeNode root2 = buildTree(input2);
//         System.out.println(maxProduct(root2)); // Output: 100
//     }
// }
