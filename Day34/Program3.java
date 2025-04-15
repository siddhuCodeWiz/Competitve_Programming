package Day34;

/*Given a classic mobile phone, and the key pad of the phone looks like below.
	1		2		3
		   abc	   def
		 
	4		5		6
   ghi     jkl     mno
  
	7		8		9
  pqrs     tuv    wxyz
	
	*		0		#


You are given a string S contains digits between [2-9] only, 
For example: S = "2", then the possible words are "a", "b", "c".

Now your task is to find all possible words that the string S could represent.
and print them in a lexicographical order. 

Input Format:
-------------
A string S, consist of digits [2-9]

Output Format:
--------------
Print the list of words in lexicographical order.


Sample Input-1:
---------------
2

Sample Output-1:
----------------
[a, b, c]


Sample Input-2:
---------------
24

Sample Output-2:
----------------
[ag, ah, ai, bg, bh, bi, cg, ch, ci]
 */
import java.util.*;

public class Program3 {
    HashMap<Integer, String> hm;
    ArrayList<String> sols;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();
        Program3 p3  =new Program3();
        p3.find(s);
        System.out.println(p3.sols);
    }

    void find(String s){
        hm = new HashMap<>();
        sols = new ArrayList<>();
        hm.put(1, "");
        hm.put(2, "abc");
        hm.put(3, "def");
        hm.put(4, "ghi");
        hm.put(5, "jkl");
        hm.put(6, "mno");
        hm.put(7, "pqrs");
        hm.put(8, "tuv");
        hm.put(9, "wxyz");

        helper(s, 0, "");
    }

    void helper(String s, int pos, String curr){
        if(pos == s.length()){
            sols.add(curr);
            return;
        }

        String r = hm.get(Integer.parseInt(s.substring(pos, pos+1)));
        for(int i=0; i<r.length(); i++){
            helper(s, pos+1, curr+r.charAt(i));
        }
    }
}
