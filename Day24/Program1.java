/*Imagine you are an explorer who stumbles upon two ancient treasure chests. 
However, these aren’t ordinary chests—the coins inside are arranged in a mysterious, 
branching order. In each chest, the coins are hidden in secret compartments where 
lower valued coins are tucked away on one side and higher valued coins are placed 
on the opposite side. To unlock the legendary vault of riches, you must select one 
coin from the first chest and one from the second chest such that their magical 
values add up to a secret key number. 
Your challange is to return true if you can unlock the legendary vault of riches, 
otherwise false.

Example 1
----------
Input=
2 1 4
1 0 3
5

Output=
true

Chest A:
    2
   / \
  1   4

Chest B:
    1
   / \
  0   3

Explanation:Choosing the coin with value 2 from Chest A and the coin with value 3 
from Chest B adds up to 5, unlocking the vault

Example 2:
----------
Input=
0 -10 10
5 1 7 0 2
18

Output=
false

Chest A:
    0
   / \
-10   10

Chest B:
      5
     / \
    1   7
   / \
  0   2

Explanation: No combination of one coin from Chest A and one coin from Chest B 
sums to 18, so the vault remains sealed.


/*
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
} */

class Program1 {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
    //write your code here
    
    Main m = new Main();
    m.twoSumBST(root1, root2, target);
    // System.out.println(quest);
    return m.quest;
        
        
    }
}

class Main{
    boolean quest;
    Main(){
        quest=false;
    }
    
    public void twoSumBST(TreeNode root1, TreeNode root2, int target){
        if(quest==true) return;
        if(root1==null || root2==null){
            return;
        }
        if(root1.val+root2.val==target){
            quest = true;
            return;
        } else if(root1.val+root2.val < target){
            twoSumBST(root1, root2.right, target);
            twoSumBST(root1.right, root2, target);
        } else{
            twoSumBST(root1, root2.left, target);
            twoSumBST(root1.left, root2, target);
        }
        
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}