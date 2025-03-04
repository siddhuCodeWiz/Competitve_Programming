package Day16_DisjointSet;

/*You are a database integrity engineer working for a global cloud company. 
Your team maintains a distributed database network, where each server either:
    - Stores equivalent data to another server (serverX == serverY).
    - Stores different data from another server (serverX != serverY).

The transitive consistency rule must be followed:
    - If A == B and B == C, then A == C must be true.
    - If A == B and B != C, then A != C must be true.

Your task is to analyze the given constraints and determine whether they 
follow transitive consistency. If all relations are consistent, return true; 
otherwise, return false

Input Format:
-------------
Space separated strnigs, list of relations

Output Format:
--------------
Print a boolean value, whether transitive law is obeyed or not.


Sample Input-1:
---------------
a==b c==d c!=e e==f

Sample Output-1:
----------------
true


Sample Input-2:
---------------
a==b b!=c c==a

Sample Output-2:
----------------
false

Explanation:
------------
{a, b} form one equivalence group.
{c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
However, b != c contradicts b == a and c == a.

Sample Input-3:
---------------
a==b b==c c!=d d!=e f==g g!=d

Sample Output-3:
----------------
true */

import java.util.*;

class Program2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        DisjointSet ds = new DisjointSet();
        
        String[] s = sc.nextLine().split(" ");
        HashMap<Character, Character> equals = new HashMap<>();
        HashMap<Character, Character> notEquals = new HashMap<>();
        
        for(int i=0; i<s.length; i++){
            if(s[i].charAt(1) == '='){
                equals.put(s[i].charAt(0), s[i].charAt(3));
            } else{
                notEquals.put(s[i].charAt(0), s[i].charAt(3));
            }
        }
        
        ds.arr = new int[26];
        Arrays.fill(ds.arr, -1);
        
        for(Character cc : equals.keySet()){
            ds.union( ((int)cc)-97 , ((int)equals.get(cc))-97);
        }
        
        boolean ff = true;
        
        for(Character cc : notEquals.keySet()){
            
            if((ds.find((int)cc-97)) == ds.find((int)notEquals.get(cc)-97)){
                ff = false;
                break;
                
            }
        }
        
        System.out.print(ff);
        
        // ds.printArray();
        sc.close();
    }
}

class DisjointSet{
    int[] arr;
    
    void union(int start, int end){
        int start_p = find(start);
        int end_p = find(end);
        
        if(start_p < end_p){
            arr[end_p] = start_p;
        } else if(start_p > end_p){
            arr[start_p] = end_p;
        }
        
    }
    
    int find(int v){
        if(arr[v] == -1){
            return v;
        }
        
        return find(arr[v]);
    }
    
    void printArray(){
        for(int i=0; i<26; i++){
            System.out.print(arr[i]+" ");
        }
    }
    
}
