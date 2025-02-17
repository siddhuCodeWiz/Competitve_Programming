package Day6;
import java.util.*;

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        
        String[] ss1 = s1.split(" ");
        String[] ss2 = s2.split(" ");
        
        Main m = new Main();
        
        for(int i=0; i<ss1.length; i++){
            m.preorder.add(Integer.parseInt(ss1[i]));
        }
        
        for(int i=0; i<ss2.length; i++){
            m.postorder.add(Integer.parseInt(ss2[i]));
        }
        
        m.buildTree();
        sc.close();
    }
}

class Main{
    int n;
    ArrayList<Integer> preorder;
    ArrayList<Integer> postorder;
    boolean[] visited;
    Queue<Integer> que;
    LinkedHashMap<Integer, ArrayList<Integer>> levelOrder;
    HashMap<Integer, Integer> pre;
    HashMap<Integer, Integer> post;

    Main(){
        preorder = new ArrayList<>();
        postorder = new ArrayList<>();
        visited = new boolean[100];
        levelOrder = new LinkedHashMap<>();
        pre = new HashMap<>();
        post = new HashMap<>();
        que = new LinkedList<>();
    }
    
    protected void buildTree(){
        n = preorder.size();
        for(int i=0; i<n; i++){
            pre.put(preorder.get(i), i);
        }
        for(int i=0; i<n; i++){
            post.put(postorder.get(i), i);
        }
        
        if(preorder.get(0)==postorder.get(n-1)){
            que.add(preorder.get(0));
            visited[preorder.get(0)] = true;
            buildTreeHelper(1);
        }

    }

    private void buildTreeHelper(int level){
        int si = que.size();
        for(int i=0; i<si; i++){
            int currEle = que.poll();
            System.out.println(currEle);
            if(levelOrder.containsKey(level)){
                levelOrder.get(level).add(currEle);
            }else{
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(currEle);
                levelOrder.put(level, arr);
            }
    
            int leftInd = pre.get(currEle)+1;
            int rightInd = post.get(currEle)-1;
            System.out.println("Left Ind: "+leftInd+" Right Ind: "+rightInd);
    
            if(leftInd<0 || leftInd>=n) return;
            if(rightInd<0 || rightInd>=n) return;
            
            if(preorder.get(leftInd)==postorder.get(rightInd) && visited[preorder.get(leftInd)]==false){
                que.add(preorder.get(leftInd));
            }
            
            else if(visited[preorder.get(leftInd)]==false && visited[postorder.get(rightInd)]){
                que.add(preorder.get(leftInd));
                que.add(postorder.get(rightInd));
                visited[preorder.get(leftInd)] = true;
                visited[postorder.get(rightInd)] = true;
            }
            
        }
        buildTreeHelper(level++);
    }
    
    public void printLevel(){
        for(Map.Entry<Integer, ArrayList<Integer>> curr : levelOrder.entrySet()){
            System.out.print(curr.getValue());
        }
    }
}



/*import java.util.*;

class Solution {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    static int preIndex = 0;
    static Map<Integer, Integer> postMap;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read preorder
        String[] preStr = sc.nextLine().trim().split(" ");
        int[] preorder = new int[preStr.length];
        for(int i = 0; i < preStr.length; i++) {
            preorder[i] = Integer.parseInt(preStr[i]);
        }
        
        // Read postorder
        String[] postStr = sc.nextLine().trim().split(" ");
        int[] postorder = new int[postStr.length];
        for(int i = 0; i < postStr.length; i++) {
            postorder[i] = Integer.parseInt(postStr[i]);
        }
        
        // Construct tree and print level order
        TreeNode root = constructTree(preorder, postorder);
        List<List<Integer>> result = levelOrder(root);
        System.out.println(result);
    }
    
    public static TreeNode constructTree(int[] preorder, int[] postorder) {
        preIndex = 0;
        postMap = new HashMap<>();
        
        // Store postorder indices in map for O(1) lookup
        for(int i = 0; i < postorder.length; i++) {
            postMap.put(postorder[i], i);
        }
        
        return buildTree(preorder, 0, postorder.length - 1);
    }
    
    private static TreeNode buildTree(int[] preorder, int postStart, int postEnd) {
        // Base cases
        if (postStart > postEnd || preIndex >= preorder.length) {
            return null;
        }
        
        // Create root node from current preorder element
        TreeNode root = new TreeNode(preorder[preIndex++]);
        
        // If this is a leaf node
        if (postStart == postEnd) {
            return root;
        }
        
        // Find the index of next preorder element in postorder
        // This will be the end of left subtree in postorder
        int index = postMap.get(preorder[preIndex]);
        
        // If we found a valid index and it's within our current post range
        if (index <= postEnd) {
            // Recursively build left and right subtrees
            root.left = buildTree(preorder, postStart, index);
            root.right = buildTree(preorder, index + 1, postEnd - 1);
        }
        
        return root;
    }
    
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);
                
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
} */