/*In the ancient land of Palindoria, wizards write magical spells as strings of 
lowercase letters. However, for a spell to be effective, each segment of the 
spell must read the same forward and backward.

Given a magical spell 'spell', your task is to partition it into sequences of 
valid magical spell segments. 
Your goal is to help the wizard discover all valid combinations of magical spell 
segmentations.

Example 1:
----------
Input:  
aab
  
Output:  
[[a, a, b], [aa, b]]

Example 2:

Input:  
a  
Output:  
[[a]]

Spell Constraints:

- The length of the spell is between 1 and 16 characters.  
- The spell contains only lowercase English letters.   */

import java.util.*;

public class Program1 {

    public List<List<String>> partition(String spell) {
        int n = spell.length();
        boolean[][] dp = new boolean[n][n];

        // Step 1: Fill DP table to know which substrings are palindromes
        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                if (spell.charAt(start) == spell.charAt(end) &&
                    (end - start <= 2 || dp[start + 1][end - 1])) {
                    dp[start][end] = true;
                }
            }
        }

        // Step 2: Use backtracking with the help of DP table
        List<List<String>> result = new ArrayList<>();
        backtrack(spell, 0, new ArrayList<>(), result, dp);
        return result;
    }

    private void backtrack(String spell, int start, List<String> current,
                           List<List<String>> result, boolean[][] dp) {
        if (start == spell.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int end = start; end < spell.length(); end++) {
            if (dp[start][end]) {
                current.add(spell.substring(start, end + 1));
                backtrack(spell, end + 1, current, result, dp);
                current.remove(current.size() - 1); // backtrack
            }
        }
    }

    // Test it
    public static void main(String[] args) {
        Program1 wizard = new Program1();

        System.out.println("Example 1:");
        System.out.println(wizard.partition("aab"));  // Output: [[a, a, b], [aa, b]]

        System.out.println("Example 2:");
        System.out.println(wizard.partition("a"));    // Output: [[a]]
    }
}

