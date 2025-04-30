package Day41_DP;

/*You are working in a genetics laboratory where you are tasked with correcting 
DNA sequences. Each DNA strand is represented as a sequence of characters 
'A', 'C', 'G', and 'T'.
Sometimes, due to mutations or errors during sequencing, the DNA strand (originalDNA) 
must be modified to match a targetDNA sequence exactly.

You can perform the following mutation operations:
- Insert a nucleotide (A, C, G, or T) into the DNA strand.
- Delete a nucleotide from the DNA strand.
- Replace a nucleotide with another one.

Each operation counts as one step.

Your task is to find the minimum number of mutation operations needed to 
transform the originalDNA into the targetDNA.

Input format:
-------------
2 space seperated strings, originalDNA and targetDNA

Output format:
--------------
An integer, the minimum number of mutation operations


Example 1:
-----------
Input:
ACGT AGT

Output:
1

Explanation:
Delete 'C': "ACGT" → "AGT"
Only 1 mutation is needed.

Example 2:
----------
Input:
GATTAC GCATGCU

Output:
4

Explanation:
- Replace 'A' with 'C': "GATTAC" → "GCTTAC"
- Replace 'T' with 'A': "GCTTAC" → "GCATAC"
- Replace 'A' with 'G': "GCATAC" → "GCATGC"
- Insert 'U' at the end: "GCATGC" → "GCATGCU"

Thus, 4 mutations are needed. */
import java.util.*;

public class Program2 {
    static String ori;
    static String tar;
    static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ori = sc.next();
        tar = sc.next();sc.close();
        dp = new int[ori.length()][tar.length()];
        for(int i=0; i<ori.length(); i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(helper(0, 0));
    }

    static int helper(int i, int j){
        if(i>=ori.length()) return tar.length()-j;
        if(j>=tar.length()) return ori.length()-i;
        
        if(dp[i][j]!=-1) return dp[i][j];
        
        if(ori.charAt(i) == tar.charAt(j)){
            dp[i][j] = helper(i+1, j+1);
        }else{
            int a = helper(i+1, j+1);
            int b = helper(i, j+1);
            int c = helper(i+1, j);

            dp[i][j] = 1+Math.min(a, Math.min(b, c));
        }
        return dp[i][j];
    }
}
