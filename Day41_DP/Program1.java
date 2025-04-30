package Day41_DP;

/*You are participating in a futuristic space exploration mission where you must 
navigate through a series of planets aligned in a straight line.
The planets are numbered from 0 to n-1, and you start your journey on planet 0.

You are given a 0-indexed array planets, where each element planets[i] represents 
the maximum number of planets you can move forward from planet i. In simpler terms, 
standing on planet i, you can move to any planet from i+1 to i+planets[i], 
as long as you don't exceed the last planet.

Your goal is to reach the final planet (planet n-1) in the fewest number of 
moves possible.

It is guaranteed that a path to the final planet always exists.

Return the minimum number of moves needed to reach planet n-1.

Example 1
----------
Input:
2 3 1 0 4
Output:
2

Explanation:
- Move from planet 0 to planet 1.
- Move from planet 1 to planet 4 (last planet). */

import java.util.*;

class Program1{
    int minn;
    int[] dp;
    Program1(){
        minn = Integer.MAX_VALUE;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] num = sc.nextLine().split(" ");
        int[] nums = new int[num.length];
        
        int n = num.length;
        for(int i=0; i<n;i++){
            nums[i] = Integer.parseInt(num[i]);
        }
        
        Program1 ss = new Program1();
        ss.dp = new int[n];
        Arrays.fill(ss.dp, -1);
        System.out.println(ss.helper(nums, 0, n));

        sc.close();
    }
    
    int helper(int[] nums, int curr_ind, int n){
        if(curr_ind >= n-1) return 0;
        
        if(dp[curr_ind]!=-1) return dp[curr_ind];
        int minn = (int)1e9;
        for(int i=curr_ind+1; i<=curr_ind+nums[curr_ind]; i++){
            minn = Math.min(minn, helper(nums, i, n));
        }
        return dp[curr_ind]=minn+1;
    }
}