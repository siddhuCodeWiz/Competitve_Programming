package Day11_Backtrack;

/*A shipping company is managing two cargo ships: a large cargo ship and 
a smaller cargo ship. The company has divided the large cargo ship into 
X compartments and the smaller cargo ship into Y compartments, where X > Y.

Each compartment in both ships is loaded with a specific amount of cargo. 
The company needs to relocate cargo from the large cargo ship to the smaller 
cargo ship by selecting Y compartments from the large ship and transferring 
their cargo to Y compartments in the smaller ship, maintaining the respective order.

However, due to weight balance regulations, the amount in compartment n+1 
should always be greater than or equal to that in the compartment n of the smaller 
cargo ship, after the transferred from the large cargo ship.

Your task is to help the company determine the number of ways they can transfer 
the cargo while satisfying these regulations.

Input Format:
-------------
Number of compartments in the large cargo ship (X).
Number of compartments in the smaller cargo ship (Y).
Cargo weights in compartments of the large cargo ship.
Cargo weights in compartments of the smaller cargo ship.

Output Format:
----------------
Return the number of valid ways to transfer the cargo.


Sample Input:
--------------
5
3
1 5 2 4 7
7 8 6

Sample Output:
----------------
4

Explanation:
-----------
The compartments from the large cargo ship can be selected as:
- (1, 2, 7)
- (1, 4, 7)
- (5, 4, 7)
- (2, 4, 7)  
Thus, there are 4 valid ways to transfer the cargo.

Sample Input:
--------------
4
2
7 7 7 7
3 4

Sample Output:
----------------
6

Explanation:
-----------
The compartments from the large cargo ship can be selected as:
- (1st, 2nd) (7,7)
- (1st, 3rd) (7,7)
- (1st, 4th) (7,7)
- (2nd, 3rd) (7,7)
- (2nd, 4th) (7,7)
- (3rd, 4th) (7,7)  

Thus, there are 6 valid ways to transfer the cargo. */

import java.util.*;

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main m = new Main();
        m.X = sc.nextInt();
        m.Y = sc.nextInt();
        m.largeShip = new int[m.X];
        m.smallShip = new int[m.Y];
        for(int i=0; i<m.X; i++){
            m.largeShip[i] = sc.nextInt();
        }
        for(int i=0; i<m.Y; i++){
            m.smallShip[i] = sc.nextInt();
        }

        System.out.println(m.findWays());
        sc.close();
    }

}

class Main{
    int X;
    int Y;
    int[] largeShip;
    int[] smallShip;
    ArrayList<Integer> curr;
    int count;

    Main(){
        curr = new ArrayList<>();
        count = 0;
    }


    protected int findWays(){
        helper(0);
        return count;
    }

    private void helper(int currInd){
        if(curr.size()==Y){
            System.out.println(curr);
            count++;
            return;
        }
        if(currInd>=X){
            return;
        }


        for(int i=currInd; i<X; i++){
            if(curr.size()==0){
                curr.add(largeShip[i]);
                helper(i+1);
                curr.remove(curr.size()-1);
            } else if(curr.get(curr.size()-1) <= largeShip[i]){
                curr.add(largeShip[i]);
                helper(i+1);
                curr.remove(curr.size()-1);
            }
        }
    }
}