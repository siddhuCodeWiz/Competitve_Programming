package LAB;

/*b) Write a JAVA Program to find shortest sub array with sum at least K 
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K. If 
there is no non-empty subarray with sum at least K, return -1. 
Example 1: 
Input: A = [1], K = 1 
Output: 1 
Example 2: 
Input: A = [1,2], K = 4 
Output: -1 
Example 3: 
Input: A = [2,-1,2], K = 3 
Output: 3 
Note: 
1 <= A.length <= 50000 -10 ^ 5 <= A[i] <= 10 ^ 5 
1 <= K <= 10 ^ 9 */

import java.util.*;
public class Program1b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        
        System.out.println(findLen(arr, n, k));
        sc.close();
    }

    public static int findLen(int[] arr, int n, int k){
        int[] preSum = new int[n+1];
        for(int i=0; i<n; i++){
            preSum[i+1] = preSum[i]+arr[i];
        }
        
        int minLen=n+1;
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i=0; i<=n; i++){
            while(!deque.isEmpty() && preSum[i]-preSum[deque.peekFirst()]>=k){
                minLen = Math.min(minLen, i-deque.pollFirst());
            }

            while(!deque.isEmpty() && preSum[i]<preSum[deque.peekLast()]){
                deque.pollLast();
            }

            deque.offerLast(preSum[i]);
        }

        return minLen<= n+1 ? minLen : -1;
    }
}
