/*Imagine you're a top-secret agent receiving an encrypted directive from headquarters. The message comes as a string of digits, and each digit (from 2 to 9) is a 
cipher for a set of potential code letters. To uncover the true instruction, you must translate the string into every possible combination of letters by 
substituting each digit with its corresponding set of letters. The final decoded messages listed in lexicographycal order.

Below is the mapping of digits to letters (as found on a traditional telephone keypad):

| Digit | Letters       |
|-------|---------------|
| 2     | a, b, c       |
| 3     | d, e, f       |
| 4     | g, h, i       |
| 5     | j, k, l       |
| 6     | m, n, o       |
| 7     | p, q, r, s    |
| 8     | t, u, v       |
| 9     | w, x, y, z    |

Note: The digit 1 does not correspond to any letters.

Example 1:
Input: 23  
Output: [ad, ae, af, bd, be, bf, cd, ce, cf]

Example 2:
Input: 2 
Output: [a, b, c]


Constraints:

- 0 <= digits.length <= 4  
- Each digit in the input is between '2' and '9'.
 */


 import java.util.*;

class Program2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        Main m = new Main(sc.nextLine());
        m.allCombi(0, "");
        System.out.println(m.sols);

        sc.close();
    }
}

class Main{
    HashMap<Character, char[]> hm;
    String num;
    ArrayList<String> sols;
    
    Main(String s){
        hm = new HashMap<>();
        hm.put('2', new char[]{'a', 'b', 'c'});
        hm.put('3', new char[]{'d', 'e', 'f'});
        hm.put('4', new char[]{'g', 'h', 'i'});
        hm.put('5', new char[]{'j', 'k', 'l'});
        hm.put('6', new char[]{'m', 'n', 'o'});
        hm.put('7', new char[]{'p', 'q', 'r', 's'});
        hm.put('8', new char[]{'t', 'u', 'v'});
        hm.put('9', new char[]{'w', 'x', 'y', 'z'});
        this.num = s;
        sols = new ArrayList<>();
    }
    
    void allCombi(int i, String curr){
        if(i>num.length()-1){
            sols.add(curr);
            return;
        }
        // System.out.println(i+" "+curr);
        for(char cc : hm.get(num.charAt(i))){
            allCombi(i+1, curr+cc);
        }
            
    }
}