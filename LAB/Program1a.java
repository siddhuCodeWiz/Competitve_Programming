package LAB;

/*a) Write a JAVA Program to find Subarrays with K Different integers 
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A 
good if the number of different integers in that subarray is exactly K. (For example, [1,2,3,1,2] has 
3 different integers: 1, 2, and 3.) 
Return the number of good subarrays of A. 
Example 1: 
Input: A = [1,2,1,2,3], K = 2 
Output: 7 
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], 
[2,1,2], [1,2,1,2] 
Example 2: 
Input: A = [1,2,1,3,4], K = 3 
Output: 3 
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], 
[1,3,4]. Note: 
1 <= A.length <= 20000 
1 <= A[i] <= A.length 
1 <= K <= A.length  */
import java.util.*;

public class Program1a {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println(atMostK(arr, k, n) - atMostK(arr, k-1, n));
        sc.close();
    }

    static int atMostK(int[] arr, int k, int n){
        HashMap<Integer, Integer> hm = new HashMap<>();
        int right=0, left=0, count=0;

        for(right=0; right<n; right++){
            hm.put(arr[right], hm.getOrDefault(arr[right], 0)+1);

            while(hm.size()>k){
                hm.put(arr[left], hm.get(arr[left])-1);
                if(hm.get(arr[left])==0){
                    hm.remove(arr[left]);
                }
                left++;
            }
            count += right-left+1;
        }
        return count;
    }
}
