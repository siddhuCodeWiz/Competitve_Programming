package Day19;

/*Mr. Athreya is a beginner in computer programming.
He is given an integer D, a decimal value. 
Your task is to count the 1's in the BCD form of each integer N and store 
the count in an array of size D+1, where N is in the range of  0<= N <= D.
 
Example of BCD :
Given an integer I=10 , The BCD of I is '1010', and 1's count is 2.
 
NOTE: Can you solve it in O(D) time?
 
Input Format:
-------------
An integer D, the decimal value.
 
Output Format:
--------------
Print the array of D+1 integers.
 
 
Sample Input-1:
---------------
3
 
Sample Output-1:
----------------
0 1 1 2


Sample Input-2:
---------------
6
 
Sample Output-2:
----------------
0 1 1 2 1 2 2 */

import java.util.*;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n+1];

        arr[0] = 0;
        arr[1] = 1;

        // Catch th relation between the currect number and half of it based n whether it is an even or odd number.
        for(int i=2; i<=n; i++){
            int setBits = arr[i/2]+(i%2);
            arr[i] = setBits;
        }

        for(int i=0; i<=n; i++){
            System.out.print(arr[i]+" ");
        }

        sc.close();
    }
}
