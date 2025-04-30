package Day42_ERdiagram_DP;

/*A digital advertising company is setting up electronic billboards across the city. 
Each billboard screen has dimensions of rows x cols, indicating how many lines (rows) 
and how many characters per line (cols) the screen can display. The company has 
prepared an advertising slogan consisting of several words, provided as a list of strings. 
The slogan must repeatedly appear on the billboard, word by word, maintaining the 
exact original order. Each word must fit entirely on a single line without breaking. 
Consecutive words on the same line must be separated by exactly one blank space.

Determine how many complete times the given advertising slogan can be displayed 
fully on the billboard screen.

Input format:
-------------
Line 1: Space seperated words, slogon
Line 2: Two space separated integers, rows & cols


Output format:
--------------
An integer, number of times the given advertising slogan can be displayed fully on the billboard screen.


Example 1:
----------
Input=
fast cars
2 8

Output=
1

Explanation:  
fast----  
cars----  
(The character '-' represents empty spaces on the screen.)


Example 2:
----------
Input=
win big now
3 7

Output=
2

Explanation:  
win-big  
now-win  
big-now  
(The character '-' represents empty spaces on the screen.)


Example 3:
----------
Input=
eat fresh daily
4 6

Output=1
 
Explanation:  
eat---  
fresh-  
daily-  
eat---  
(The character '-' represents empty spaces on the screen.)


Constraints:

- 1 <= slogan.length <= 1000
- Each word in slogan consists only of lowercase English letters.
- 1 <= rows, cols <= 2 *10^4 */



import java.util.*;

public class Program2 {

    public static int billboardSlogan(String[] words, int rows, int cols) {
        int n = words.length;
        int[] dp = new int[n];       // dp[i]: how many words can we fit starting at word i in one row
        int[] next = new int[n];     // next[i]: the word index to start next row from

        for (int i = 0; i < n; i++) {
            int currLen = 0;
            int wordCount = 0;
            int j = i;

            while (currLen + words[j % n].length() <= cols) {
                currLen += words[j % n].length();
                j++;
                wordCount++;
                if (currLen < cols) currLen++; // add a space
            }

            dp[i] = wordCount;
            next[i] = j % n;
        }

        int totalWords = 0;
        int start = 0;

        for (int i = 0; i < rows; i++) {
            totalWords += dp[start];
            start = next[start];
        }

        return totalWords / n; // how many full times slogan repeated
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read words
        String line = sc.nextLine();
        String[] words = line.trim().split("\\s+");

        // Read rows and cols
        String[] dims = sc.nextLine().split("\\s+");
        int rows = Integer.parseInt(dims[0]);
        int cols = Integer.parseInt(dims[1]);

        int result = billboardSlogan(words, rows, cols);
        System.out.println(result);

        sc.close();
    }
}
