package Day39_DP_Trie;

/*Mr Suresh is working with the plain text P, a list of words w[], 
He is converting P into C [the cipher text], C is valid cipher of P, 
if the following rules are followed:
	- The cipher-text C is a string ends with '$' character.
	- Every word, w[i] in w[], should be a substring of C, and 
	the substring should have $ at the end of it.

Your task is to help Mr Suresh to find the shortest Cipher C,  
and return its length.

Input Format:
-------------
Single line of space separated words, w[].

Output Format:
--------------
Print an integer result, the length of the shortest cipher.


Sample Input-1:
---------------
kmit it ngit

Sample Output-1:
----------------
10

Explanation:
------------
A valid cipher C is "kmit$ngit$".
w[0] = "kmit", the substring of C, and the '$' is the end character after "kmit"
w[1] = "it", the substring of C, and the '$' is the end character after "it"
w[2] = "ngit", the substring of C, and the '$' is the end character after "ngit"


Sample Input-2:
---------------
ace

Sample Output-2:
----------------
4

Explanation:
------------
A valid cipher C is "ace$".
w[0] = "ace", the substring of C, and the '$' is the end character after "ace"

 */

import java.util.*;

public class Program3 {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] ss = sc.nextLine().split(" ");
		Arrays.sort(ss, Comparator.reverseOrder());
		sc.close();
		Trie t = new Trie();
		StringBuffer sb;
		for(int i=0; i<ss.length; i++){
			sb = new StringBuffer(ss[i]);
			t.insert(sb.reverse().toString());
		}

		System.out.println(t.diff_words);
	}
}

class Trie{
	TrieNode root;
	int diff_words;
	Trie(){
		root = new TrieNode();
		diff_words = 0;
	}

	void insert(String s){
		TrieNode node = root;
		boolean entered = false;

		for(int i=0; i<s.length(); i++){
			char ch = s.charAt(i);
			if(!node.containsKey(ch)){
				node.put(ch);
				entered = true;
			}
			node = node.get(ch);
		}
		if(entered){
			diff_words+=s.length()+1;
		}
		node.setEnd();
	}
}

class TrieNode{
	TrieNode[] links;
	boolean isEndOfWord;

	TrieNode(){
		links = new TrieNode[26];
		isEndOfWord = false;
	}

	boolean containsKey(char ch){
		return links[ch-'a']!=null;
	}

	TrieNode get(char ch){
		return links[ch-'a'];
	}

	void put(char ch){
		links[ch-'a'] = new TrieNode();
	}

	void setEnd(){
		isEndOfWord=true;
	}

	boolean getEnd(){
		return isEndOfWord;
	}
}
