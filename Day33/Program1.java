package Day33;

import java.math.BigInteger;
/*Neeraj has a task to complete. Given a number N, he has to make reductions 
to make it to 1 with the following rules:
    1. If N is odd then add 1 to it.
    2. If N is even then divide it by 2.
    
Neeraj is given the N in binary format as a string S. Neeraj always 
successful in making N to 1. 
Your task is to help Neeraj to find the number of steps required to make N  to 1.

Input Format:
-------------
A string S, represents the binary equivalent of N.

Output Format:
--------------333
Print an integer as number of steps.


Sample Input-1:
---------------
110

Sample Output-1:
----------------
4

Explanation:
-------------
step-1: N=6, even, so 6/2=3
step-2: N=3, odd,  so 3+1=4
step-3: N=4, even, so 4/2=2
step-4: N=2, even, so 2/2=1
Total steps=4


Sample Input-2:
---------------
101

Sample Output-2:
----------------
5

Explanation:
------------
step-1: N=5, odd,  so 5+1=6
step-2: N=6, even, so 6/2=3
step-3: N=3, odd,  so 3+1=4
step-4: N=4, even, so 4/2=2
step-5: N=2, even, so 2/2=1
Total steps=5 */
import java.util.*;

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(steps(sc.nextLine()));
        sc.close();
    }

    public static int steps(String s){
        BigInteger binary = new BigInteger(s, 2);
        int steps = 0;
        while(!binary.equals(BigInteger.ONE)){
            if(binary.testBit(0)){
                binary = binary.add(BigInteger.ONE);
            }else{
                binary = binary.shiftRight(1);
            }
            steps++;
        }
        return steps;
    }
}
