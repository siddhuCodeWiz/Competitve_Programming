package Day17_DisjointSet;

/*A grid of light bulbs is given, represented as a matrix of size rows x cols, 
where each cell contains either 0 (off) or 1 (on).

Your task is to turn all the light bulbs off (0) by following the toggle rule:
    - In each step, you can choose either an entire row or an entire column 
    and toggle all its elements (change 0 to 1 and 1 to 0).
    
At the end, if all light bulbs are turned off, print true, otherwise print false.


Input Format
-------------
Line-1: Read two integers rows and cols(space separated).
Line-2: Read the matrix of dimension rows X cols.

Output Format
--------------
Print a boolean result.



Sample input-1:
---------------
5 5
0 0 1 0 0
0 0 1 0 0
1 1 0 1 1
0 0 1 0 0
0 0 1 0 0

Sample output-1:
----------------
true

Explanation:
------------
0 0 1 0 0          0 0 1 0 0           0 0 0 0 0
0 0 1 0 0   row-3  0 0 1 0 0   cols-3  0 0 0 0 0
1 1 0 1 1   --->   0 0 1 0 0   --->    0 0 0 0 0
0 0 1 0 0          0 0 1 0 0           0 0 0 0 0
0 0 1 0 0          0 0 1 0 0           0 0 0 0 0 


Sample input-2
--------------
2 2
1 1
0 1

Sample output-2
---------------
false
 */
import java.util.*;

class Program3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] arr = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        boolean isTrue = true;
        for(int i=1; i<n; i++){
            if(!(isEqual(arr[0], arr[i]) || isNOTEqual(arr[0], arr[i]))){
                isTrue = false;
                break;
            }
        }

        System.out.println(isTrue);
        sc.close();

    }

    public static boolean isEqual(int[] arr1, int[] arr2){
        for(int i=0; i<arr1.length; i++){
            if(arr1[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean isNOTEqual(int[] arr1, int[] arr2){
        for(int i=0; i<arr1.length; i++){
            if((arr1[i]==0 && arr2[i]==0) || (arr1[i]==1 && arr2[i]==1)){
                return false;
            }
        }
        return true;
    }
}