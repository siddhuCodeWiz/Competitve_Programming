package Day35_BackTracking;
/*Ramesh and Suresh are best friends. 
They decided to play a word game.

The game rules are as follows:
	- The game board shows a word W consist of two characters only A and B.
	- You are allowed to replace a pair of neighbour letters AA with BB in W.
	- Finally, The one who failed to replace the letters will lose the game.

You can assume that Ramesh will start playing the game always.

Your task is to determine if Ramesh can guarantee a win.
Print 'true', if Ramesh guarantee a win, otherwise, print 'false'.

Input Format:
-------------
A string W, word.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
AAABAAAA

Sample Output-1:
----------------
true

Explanation:
------------
Given word is AAABAAAA 
Ramesh -> AAABBBAA 
Now whatever the pair Suresh replaced with BB, 
one more replace is possible for Ramesh
So, there is a guarantee for Ramesh to win.

Sample Input-2:
---------------
AAAAAA

Sample Output-2:
----------------
true


Sample Input-3:
---------------
AAABAAA

Sample Output-3:
----------------
false */






import java.util.*;

class Program1{
    boolean winn;
    Program1(){
        winn = true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuilder sb = new StringBuilder(s);
        Program1 ss = new Program1();
        ss.helper(sb, true);
        System.out.println(ss.winn);
        sc.close();
    }
    
    void helper(StringBuilder s, boolean turn){
        
        // System.out.println(s.toString());
        boolean entered = false;
        for(int i=0; i<s.length()-1; i++){
            if(s.charAt(i)=='A' && s.charAt(i+1)=='A'){
                StringBuilder ns = s.delete(i, i+2);
                ns = ns.insert(i, "BB");
                helper(ns, !turn);
                entered = true;
            }
        }   
        if(entered==false){
            if(turn==true) winn = false;
            else winn = true;
            return;
        }
    }
}

// Given word is AAABAAAA 
// Ramesh -> AAABBBAA