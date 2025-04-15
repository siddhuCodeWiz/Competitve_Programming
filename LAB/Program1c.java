package LAB;

/*Malika taught a new fun time program practice for Engineering Students. As a part of this she 
has given set of numbers, and asked the students to find the gross value of numbers between 
indices S1 and S2 (S1<=S2), inclusive. Now it’s your task to create a method sumRange(S1,S2) 
which return the sum of numbers between the indices S1 and S2, both are inclusive. 

Input Format: 
Line-1: An integer n, size of the array(set of numbers). 
Line-2: n space separated integers. 
Line-3: Two integers s1 and s2, index positions where s1<=s2. 
Output Format: 
An integer, sum of integers between indices(s1, s2). 
Sample Input-1: 
8 
1 2 13 4 25 16 17 8 
2 6 
Sample Output-1: 
75 
Constraints: 
1 <= nums.length <= 3 * 104 -100 <= nums[i] <= 100 
0 <= index < nums.length -100 <= val <= 100 
0 <= left <= right < nums.length 
At most 3 * 104 calls will be made to update and sumRange. */

import java.util.*;

public class Program1c {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        FenwickTree ft = new FenwickTree(n);
        for(int i=0; i<n; i++){
            arr[i]=sc.nextInt();
            ft.update(arr[i], i);
        }

        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(ft.rangeQuery(b)-ft.rangeQuery(a-1));

        sc.close();
    }    
}

class FenwickTree{
    int[] arr;
    int n;
    int[] BIT;

    FenwickTree(int n){
        this.n = n;
        BIT = new int[n+1];
    }

    public void update(int val, int ind){
        ind++;
        while(ind<=n){
            BIT[ind]+=val;
            ind += (ind&-ind);
        }
    }

    public int rangeQuery(int ind){
        ind++;
        int s = 0;
        while(ind>0){
            s+=BIT[ind];
            ind-=(ind&-ind);
        }
        return s;
    }
}
