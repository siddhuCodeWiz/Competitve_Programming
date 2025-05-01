package Day43;

/*You are controlling a battlefield represented by an m x n grid. 
Each cell on this grid can be one of the following:

- A reinforced barrier ('B'), blocking your laser.
- An enemy drone ('D'), your target.
- An open cell ('0'), where you can position your robot to fire.

When your robot fires its powerful laser from an open cell, 
the beam destroys all enemy drones in the same row and column 
until the beam hits a barrier ('B'). The barrier completely stops 
the laser, protecting anything behind it.

Your goal is to identify the best position (open cell) to place 
your robot so that firing the laser destroys the maximum number 
of enemy drones in a single shot. Return this maximum number.

Input format:
-------------
Line 1: Two space separated integers, represents m & n
Next M lines: each row of battlefield


Output format:
--------------
An integer, maximum number of enemy drones destroyed in a single shot.

Example 1:
----------
input=
3 4
0 D 0 0
D 0 B D
0 D 0 0

Output=
3

Explanation: placing robot at battlefield[1][1] destroys 3 enemy drones 
in a single shot.

Example 2:
----------
3 3
B B B
0 0 0
D D D

Output=
1


Constraints:
- 1 <= m, n <= 500
- battlefield[i][j] is either 'B', 'D', or '0'.
 */

 import java.util.*;

class Program2{
    char[][] arr;
    int m;
    int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Program2 p2 = new Program2();
        p2.m = sc.nextInt();
        p2.n = sc.nextInt();
        p2.arr = new char[p2.m][p2.n];
        for(int i=0; i<p2.m; i++){
            for(int j=0; j<p2.n; j++){
                p2.arr[i][j] = sc.next().charAt(0);
            }
        }

        System.out.println(p2.dfs());
        sc.close();
    }

    int dfs(){
        int maxx = Integer.MIN_VALUE;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(arr[i][j]=='0'){
                    maxx = Math.max(maxx, col_count(i, j)+row_count(i, j));
                }
            }
        }
        return maxx;
    }

    int col_count(int i, int j){
        int cc= 0;
        for(int k=i+1; k<m; k++){
            if(arr[k][j] == 'B'){
                break;
            }else if(arr[k][j] == 'D'){
                cc++;
            }
        }

        for(int k=i-1; k>=0; k--){
            if(arr[k][j] == 'B'){
                break;
            }else if(arr[k][j] == 'D'){
                cc++;
            }
        }

        return cc;
    }

    int row_count(int i, int j){
        int cc= 0;
        for(int k=j+1; k<n; k++){
            if(arr[i][k] == 'B'){
                break;
            }else if(arr[i][k] == 'D'){
                cc++;
            }
        }

        for(int k=j-1; k>=0; k--){
            if(arr[i][k] == 'B'){
                break;
            }else if(arr[i][k] == 'D'){
                cc++;
            }
        }

        return cc;
    }
}