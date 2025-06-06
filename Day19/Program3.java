package Day19;

/*Birbal is creating a new binary code system BBC (Birbal Binary Code) as follows:

I	f(I)
-------
0	""
1	"0"
2	"1"
3	"00"
4	"01"
5	"10"
6	"11"
7	"000"

You are given an integer value I, where I is positive number.
Your task is to find BBC representation of  the given number I.

Input Format:
-------------
An integer I

Output Format:
--------------
Print the BBC representation of I.


Sample Input-1:
---------------
23

Sample Output-1:
----------------
1000


Sample Input-2:
---------------
45

Sample Output-2:
----------------
01110 */

import java.util.*;
public class Program3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<String> arr = new ArrayList<>(100);
        arr.add("");
        arr.add("0");
        arr.add("1");
        arr.add("00");
        arr.add("01");
        arr.add("10");
        arr.add("11");
        // arr.add("000");
        
        int i=7;

        while(i<=n){
            int curr = i;
            int j = curr/2;
            while(i<=n && j<curr){
                arr.add("0"+arr.get(j));
                // System.out.println(i+" "+arr.get(i));
                i++; j++;
            }

            j=curr/2;
            while(i<=n && j<curr){
                arr.add("1"+arr.get(j));
                // System.out.println(i+" "+arr.get(i));
                i++; j++;
            }
        }

        System.out.println(arr.get(n));

        sc.close();
    }
}
