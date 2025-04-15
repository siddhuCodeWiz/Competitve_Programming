package Day35_BackTracking;

/*Given two strings S1 and S2, find if S2 can match S1 or not.

A match that is both one-to-one (an injection) and onto (a surjection), 
i.e. a function which relates each letter in string S1 to a separate and 
distinct non-empty substring in S2, where each non-empty substring in S2
also has a corresponding letter in S1.

Return true,if S2 can match S1.
Otherwise false.

Input Format:
-------------
Line-1 -> Two strings S1 and S2

Output Format:
--------------
Print a boolean value as result.


Sample Input-1:
---------------
abab kmitngitkmitngit

Sample Output-1:
----------------
true


Sample Input-2:
---------------
aaaa kmjckmjckmjckmjc

Sample Output-2:
----------------
true

Sample Input-3:
---------------
mmnn pqrxyzpqrxyz

Sample Output-3:
----------------
false

 */

import java.util.*;

public class Program3 {
    HashMap<Character, String> map = new HashMap<>();
    HashSet<String> used = new HashSet<>();
    boolean ans = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();

        Program3 p3 = new Program3();
        p3.helper(s1, s2, 0, 0);

        System.out.println(p3.ans);

        sc.close();
    }

    void helper(String s1, String s2, int i, int j) {
        if (i == s1.length() && j == s2.length()) {
            ans = true;
            return;
        }

        if (i == s1.length() || j == s2.length()) return;

        char ch = s1.charAt(i);

        if (map.containsKey(ch)) {
            String mappedStr = map.get(ch);
            if (j + mappedStr.length() <= s2.length() &&
                s2.substring(j, j + mappedStr.length()).equals(mappedStr)) {
                helper(s1, s2, i + 1, j + mappedStr.length());
            }
        } else {
            for (int k = j + 1; k <= s2.length(); k++) {
                String candidate = s2.substring(j, k);
                if (used.contains(candidate)) continue;

                map.put(ch, candidate);
                used.add(candidate);

                helper(s1, s2, i + 1, k);

                map.remove(ch);
                used.remove(candidate);
            }
        }
    }
}
