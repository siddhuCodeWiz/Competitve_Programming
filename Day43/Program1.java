package Day43;

/*You are managing a fleet of exploratory spacecraft, each carrying containers 
composed of two types of critical resources: 
fuel units (represented by '0') and oxygen tanks (represented by '1'). 
You're given a list 'containers', where each container is represented by a 
binary string indicating its resource composition, 
along with two integers: 'fuelLimit' (maximum allowed fuel units) and 
'oxygenLimit' (maximum allowed oxygen tanks).

Your goal is to select the largest possible subset of containers such that the 
total number of fuel units does not exceed 'fuelLimit' and the total number of 
oxygen tanks does not exceed 'oxygenLimit'.

Input format:
-------------
Line 1: Space seperated strings, represents the container strings
Line 2: Two space separated integers, represents fuelLimit & oxygenLimit

Output format:
--------------
An integer, largest possible subset of containers.


Example 1:
----------
Input=
10 0001 111001 1 0
5 3

Output=
4

Explanation: The largest subset meeting the constraints is {"10", "0001", "1", "0"}.
- Total fuel units = 5 (within limit)
- Total oxygen tanks = 3 (within limit)
Container "111001" cannot be included as it exceeds the oxygen tank limit.


Example 2:
----------
Input=
10 0 1
1 1

Output=
2

Explanation: The largest subset meeting the constraints is {"0", "1"}.
- Total fuel units = 1
- Total oxygen tanks = 1


Constraints:
- 1 <= containers.length <= 600
- 1 <= containers[i].length <= 100
- 'containers[i]' consists only of digits '0' and '1'.
- 1 <= fuelLimit, oxygenLimit <= 100
 */

// import java.util.*;

// public class Program1 {
//     String[] arr;
//     int n0l;
//     int n1l;
//     int n;
//     int cc[][];
//     int maxx = Integer.MIN_VALUE;
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         Program1 p1 = new Program1();
//         p1.arr = sc.nextLine().split(" ");
//         p1.n = p1.arr.length;
//         p1.n0l = sc.nextInt();
//         p1.n1l = sc.nextInt();

//         p1.countHelper();
//         p1.helper(0, 0, 0, 0);
//         System.out.println(p1.maxx);
//     }

//     void countHelper(){
//         cc = new int[n][2];
//         int c0, c1;
//         for(int i=0; i<n; i++){
//             c0 = 0;c1=0;
//             for(int j=0; j<arr[i].length(); j++){
//                 if(arr[i].charAt(j)=='1') c1++;
//                 else c0++;
//             }
//             cc[i][0] = c0;
//             cc[i][1] = c1;
//         }
//     }

//     void helper(int currInd, int curr0, int curr1, int curr_count){
//         if(curr0>n0l || curr1>n1l) return;
//         maxx = Math.max(maxx, curr_count);
//         if(currInd>=n) return;

//         helper(currInd+1, curr0+cc[currInd][0], curr1+cc[currInd][1], curr_count+1);
//         helper(currInd+1, curr0, curr1, curr_count);
//     }
// }






import java.util.*;

public class Program1 {
    String[] arr;
    int fuelLimit;
    int oxygenLimit;
    int n;
    int[][] cc;
    int[][][] memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Program1 p1 = new Program1();
        p1.arr = sc.nextLine().split(" ");
        p1.n = p1.arr.length;
        p1.fuelLimit = sc.nextInt();
        p1.oxygenLimit = sc.nextInt();

        sc.close();

        p1.countHelper();
        // Initialize memo with -1
        p1.memo = new int[p1.n + 1][p1.fuelLimit + 1][p1.oxygenLimit + 1];
        for (int[][] layer : p1.memo)
            for (int[] row : layer)
                Arrays.fill(row, -1);

        int result = p1.helper(0, 0, 0);
        System.out.println(result);
    }

    void countHelper() {
        cc = new int[n][2];
        for (int i = 0; i < n; i++) {
            int c0 = 0, c1 = 0;
            for (char ch : arr[i].toCharArray()) {
                if (ch == '0') c0++;
                else c1++;
            }
            cc[i][0] = c0;
            cc[i][1] = c1;
        }
    }

    int helper(int currInd, int curr0, int curr1) {
        if (curr0 > fuelLimit || curr1 > oxygenLimit) return Integer.MIN_VALUE;
        if (currInd >= n) return 0;

        if (memo[currInd][curr0][curr1] != -1)
            return memo[currInd][curr0][curr1];

        // Include current container
        int take = helper(currInd + 1, curr0 + cc[currInd][0], curr1 + cc[currInd][1]);
        if (take != Integer.MIN_VALUE) take += 1;

        // Skip current container
        int skip = helper(currInd + 1, curr0, curr1);

        // Memoize and return max of include/skip
        return memo[currInd][curr0][curr1] = Math.max(take, skip);
    }
}
