package Day1_SlidingWindow;
/*You are given an integer array nums and two integers l and r. Your task is to 
find the minimum positive energy produced by a sequence of operations. 
Each operation corresponds to selecting a contiguous subarray of nums 
whose length is between l and r (inclusive).

The energy of a sequence is defined as the product of all the numbers in 
the subarray. You need to find the sequence with the smallest positive energy.

If no such sequence exists, return -1.

Input Format:
---------------
Line-1: Three space separated integers, N, L and R.
Line-2: N space separated integers, array[].

Output Format:
-----------------
An integer result, smallest positive energy.

Sample Input-1:
-----------------
4 2 3
2 -1 3 4

Sample Output-1:
-------------------
12

Explanation:
--------------
The possible sequences of operations with lengths between l = 2 and r = 3 are:

[2, -1] (not valid, energy = -2)
[3, 4] (energy = 12)
[2, -1, 3] (not valid, energy = -6)
The sequence [3, 4] produces the smallest positive energy of 12. Hence, 
the output is 12.

Sample Input-2:
-----------------
3 2 3
-1 -3 2

Sample Output-1:
-------------------
-1

Explanation:
No valid sequence produces a positive energy. Thus, the output is -1.

Constraints:
============
1 ≤ nums.length ≤ 100
1 ≤ l ≤ r ≤ nums.length
−1000 ≤ nums[i] ≤ 1000 */



import java.util.*;

class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        int[] nums = new int[n];
        
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
        System.out.println(findMinPositiveEnergy(nums, l, r));
        sc.close();
    }
    
    public static int findMinPositiveEnergy(int[] nums, int l, int r) {
        int n = nums.length;
        int minPositive = Integer.MAX_VALUE;
        
        for (int start = 0; start < n; start++) {
            int product = 1;
            for (int end = start; end < n && (end - start + 1) <= r; end++) {
                product *= nums[end];
                if ((end - start + 1) >= l && product > 0) {
                    minPositive = Math.min(minPositive, product);
                }
            }
        }
        
        return (minPositive == Integer.MAX_VALUE) ? -1 : minPositive;
    }
}





/*import java.util.*;

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // System.out.println(-2/-1);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        long minPrd=Integer.MAX_VALUE;
        
        for(int i=l; i<=r; i++){
            long currPrd = findFirstPrd(arr, i);
            int leftPtr = 0;
            int rightPtr = i-1;
            
            while(leftPtr<n && rightPtr<n){
                // System.out.println(currPrd);
                // System.out.println(leftPtr+"    "+rightPtr);
                if(currPrd>0){
                    minPrd = (long)Math.min(minPrd, currPrd);
                }
                // leftPtr;
                rightPtr++;
                if(arr[leftPtr]!=0)
                    currPrd/=arr[leftPtr];
                else
                    currPrd=findPrd(arr, leftPtr+1, rightPtr);
                if(rightPtr<n)
                    currPrd*=arr[rightPtr];
                leftPtr++;
            }
        }
        if(minPrd!=Integer.MAX_VALUE)
            System.out.println(minPrd);
        else
            System.out.println(-1);
    }
    
    public static int findFirstPrd(int[] arr, int s){
        int currPrd = 1;
        for(int i=0; i<s; i++){
            currPrd*=arr[i];
        }
        return currPrd;
    }
    
    public static int findPrd(int[] arr, int i, int j){
        int currPrd = 1;
        for(int k=i; k<j; k++){
            currPrd*=arr[k];
        }
        return currPrd;
    }
} */