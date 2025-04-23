/*You are a stealthy archaeologist exploring a circular ring of ancient tombs 
located deep within a jungle. Each tomb holds a certain number of precious 
artifacts. 
However, these tombs are protected by an ancient magical curse: 
if you disturb two adjacent tombs during the same night, the entire ring 
activates a trap that seals you in forever.

The tombs are arranged in a perfect circle, meaning the first tomb is adjacent 
to the last. You must plan your artifact retrieval carefully to maximize the 
number of artifacts collected in a single night without triggering the curse.

Given an integer array  artifacts  representing the number of artifacts in each 
tomb, return the   maximum   number of artifacts you can collect without 
disturbing any two adjacent tombs on the same night.

Example 1:  
Input:
2 4 3
Output:  
4   

Explanation: You cannot loot tomb 1 (artifacts = 2) and tomb 3 (artifacts = 3), 
as they are adjacent in a circular setup.


Example 2:  
Input:
1 2 3 1
Output:  
4

Explanation: You can loot tomb 1 (1 artifact) and tomb 3 (3 artifacts) without 
breaking the ancient rule.  
Total = 1 + 3 = 4 artifacts.


Example 3:  
Input:
1 2 3
Output:  
3 

Constraints:  
-  1 <= artifacts.length <= 100 
-  0 <= artifacts[i] <= 1000  */


import java.util.*;

class Program1{
    int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] n = sc.nextLine().split(" ");
        sc.close();
        int[] nums = Arrays.stream(n).mapToInt(a -> Integer.parseInt(a)).toArray();
        Program1 p1 = new Program1();
        p1.dp = new int[nums.length-1];
        Arrays.fill(p1.dp, -1);
        int[] case1 = Arrays.copyOfRange(nums, 1, nums.length); 
        int max1 = p1.helper(0, case1);

        Arrays.fill(p1.dp, -1);
        int[] case2 = Arrays.copyOfRange(nums, 0, nums.length-1);
        int max2 = p1.helper(0, case2);

        System.out.println(Math.max(max1, max2));
    }

    int helper(int currInd, int[] nums){
        if(currInd>=nums.length) return 0;
        if(dp[currInd]!=-1) return dp[currInd];

        int loot = Math.max(helper(currInd+1, nums), nums[currInd]+helper(currInd+2, nums));
        return dp[currInd]=loot;

    }
}