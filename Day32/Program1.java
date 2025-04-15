package Day32;

import java.util.Arrays;

/*A detective is investigating a case involving a secret message hidden within a 
longer note. The detective knows that the culprit rearranged the letters of a 
short code-word into multiple secret locations within a larger note.

Given two strings, note (the longer text) and codeWord (the short secret code), 
your task is to help the detective find all starting positions within the note 
where any rearrangement or shuffled of codeWord is located.

Input Format:
-------------
Single line space separated strings, two words.

Output Format:
--------------
Print the list of integers, indices.


Sample Input-1:
---------------
bacdgabcda abcd
 
Sample Output-1:
----------------
[0, 5, 6]

Explanation:
- At index 0: "bacd" is an anagram of "abcd"
- At index 5: "abcd" matches exactly
- At index 6: "bcda" is an anagram of "abcd"
Thus, the positions [0, 5, 6] are returned.

Sample Input-2:
---------------
bacacbacdcab cab

Sample Output-2:
----------------
[0, 3, 4, 5, 9] */

import java.util.*;

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String secret = sc.next();
        String word = sc.next();
        sc.close();
        int[] secret_count = new int[26];
        int[] word_count = new int[26];

        for(int i=0; i<word.length(); i++){
            secret_count[secret.charAt(i)-'a']++;
            word_count[word.charAt(i)-'a']++;
        }

        int i=0, j=word.length()-1;

        while(j<secret.length()){
            if(Arrays.equals(secret_count, word_count)){
                System.out.print(i+" ");
            }

            secret_count[secret.charAt(i)-'a']--;
            i++;
            j++;
            if(j<secret.length()){
                secret_count[secret.charAt(j)-'a']++;
            }
        }

    }
}
