package Day19;

/*There are floods in the eastern India.There are infinite number ofboats available
with National Disaster Response force.Where each boat can carry a maximum weight 
limit.

Each boat carries at most two people at same time provided the sum of those people 
is at most limit. 

Return the minimum number of boats to carry every given person to rescue them
 
Input Format
------------
Line1: Two space separated integers, representing no of people and limit of boat
Line2: space separated integers represents weight of each person 

Output Format
-------------
An integer represents minimum no of boats required


Example 1:
-----------
Input1: 2 3
        1 2
Output: 1
Explanation: 1 boat (1, 2)


Example 2:
----------
Input2: 4 3
        3 2 2 1
Output2: 3
Explanation: 3 boats (1, 2), (2) and (3)


Example 3:
----------
Input3: 4 5
        3 5 3 4
Output3: 4
Explanation: 4 boats (3), (3), (4), (5)
 */

import java.util.*;

public class Program5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main m = new Main(sc.nextInt(), sc.nextInt());

        for(int i=0; i<m.tp; i++){
            m.pq.add(sc.nextInt());
        }

        System.out.println(m.calMinBoats());

        sc.close();
    }
}

class Main{
    int tp;
    int maxCap;
    PriorityQueue<Integer> pq;


    Main(int t, int m){
        this.tp = t;
        this.maxCap = m;
        this.pq = new PriorityQueue<>();
    }

    int calMinBoats(){
        int minBoats = 0;
        int currWeight = 0;
        int curr;
        while(!pq.isEmpty()){
            curr = pq.poll();
            currWeight+=curr;
            if(currWeight==maxCap){
                minBoats++;
                currWeight=0;
            } else if(currWeight>maxCap){
                minBoats++;
                currWeight-=curr;
            }
        }
        if(currWeight>0) minBoats++;
        return minBoats;
    }
}
