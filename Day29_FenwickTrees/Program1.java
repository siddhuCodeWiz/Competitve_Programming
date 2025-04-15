package Day29_FenwickTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*Imagine you're the chief engineer aboard a futuristic spaceship. The ship is 
powered by N series of fuel cells arranged in a row, where each fuel cell holds
a specific amount of energy measured in megajoules. Your job is to manage these 
fuel cells by performing two main operations:

Option 1: Calculate the total energy available in a consecutive block of fuel 
          cells between indices start and end (inclusive).
Option 2: Update the energy level with given 'newEnergy' of a specific 'index' 
          fuel cell when it's refilled.

Input Format:
-------------
Line-1: Two integers N and Q, where N is the number of fuel cells and Q is the number of operations.
Line-2: N space separated integers.
next Q lines: Three integers option, start/index and end/newEnergy.

Output Format:
--------------
An integer result, for every totalEnergy.


Example 1:
-----------
Input:
8 5
1 2 13 4 25 16 17 8
1 2 6
1 0 7
2 2 18
2 4 17
1 2 7

Output:
75
86
80


Example 2:
----------
Input:
16 1
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
1 0 15

Output:
136


Constraints

- 1 <= N <= 3*10^4  
- -100 <= fuelCells[i] <= 100  
- 0 <= index < fuelCells.length  
- -100 <= newEnergy <= 100  
- 0 <= start <= end < fuelCells.length  
- At most 3*10^4 calls will be made to recharge and totalEnergy.

*/

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int Q = sc.nextInt();

        int[] fuelCells = new int[N];
        FenwickTree fenwickTree = new FenwickTree(N);

        for (int i = 0; i < N; i++) {
            fuelCells[i] = sc.nextInt();
            fenwickTree.update(i, fuelCells[i]);
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            int option = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (option == 1) {
                results.add(fenwickTree.rangeSum(a, b));
            } else if (option == 2) {
                int delta = b - fuelCells[a];
                fuelCells[a] = b;
                fenwickTree.update(a, delta); 
            }
        }

        for (int res : results) {
            System.out.println(res);
        }

        sc.close();
    }
}

class FenwickTree {
    int[] BIT;
    int N;

    FenwickTree(int n) {
        this.N = n;
        this.BIT = new int[N + 1];
    }

    public int rangeSum(int start, int end) {
        return query(end) - (start > 0 ? query(start - 1) : 0);
    }

    void update(int index, int val) {
        index++;
        while (index <= N) {
            BIT[index] += val;
            index += (index & -index);
        }
    }

    int query(int index) {
        index++;
        int ans = 0;
        while (index > 0) {
            ans += BIT[index];
            index -= (index & -index);
        }
        return ans;
    }
}