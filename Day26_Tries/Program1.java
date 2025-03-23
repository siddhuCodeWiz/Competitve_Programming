package Day26_Tries;

/*Imagine you're a digital security analyst reviewing a suspicious email. The email’s 
content is a continuous string of characters, and you have a list of keywords 
commonly used in phishing scams. Your mission is to scan the email text and flag 
every segment that exactly matches one of these keywords. In other words, identify 
all index pairs [i, j] such that the substring from position i to j in the email 
text is one of the suspicious keywords. Return these pairs sorted by their starting 
index, and if two pairs share the same starting index, sort them by their ending index.

Input Format
------------
Line-1: string STR(without any space)
Line-2: space separated strings, suspicious keywords[]

Output Format
-------------
Print the pairs[i, j] in sorted order.


Example 1:
----------
Input:  
cybersecuritybreachalert
breach alert cyber

Output: 
0 4
13 18
19 23

Example 2:
----------
Input:  
phishphishingphish
phish phishing

Output:
0 4
5 9
5 12
13 17


Explanation: Notice that keywords can overlap—for instance, the word "phish" appears 
as part of the substring [5,9] in addition to the complete "phishing" substring [5,12].

Constraints:

- 1 <= emailText.length <= 100  
- 1 <= suspiciousWords.length <= 20  
- 1 <= suspiciousWords[i].length <= 50  
- emailText and each suspicious word consist of lowercase English letters.  
- All suspicious words are unique. */

import java.util.*;

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        String[] keywords = sc.nextLine().split(" ");

        Trie tt = new Trie();
        for(String ss : keywords){
            tt.insert(ss);
        }

        List<int[]> sols = new ArrayList<>();

        for(int i=0; i<s.length(); i++){
            tt.searchSubstrings(s, i, sols);
        }

        for(int i=0; i<sols.size(); i++){
            System.out.println(sols.get(i)[0]+" "+sols.get(i)[1]);
        }

        sc.close();
    }
}

class Trie{
    Node root;

    Trie(){
        root = new Node();
    }

    void insert(String word){
        Node node = root;

        for(int i=0; i<word.length(); i++){
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i));
            }
            node = node.get(word.charAt(i));
        }
        node.setEnd();
    }


    void searchSubstrings(String s,int start, List<int[]> result){
        Node node = root;
        for(int i=start; i<s.length(); i++){
            char ch = s.charAt(i);
            if(!node.containsKey(ch)){
                return;
            }
            node = node.get(ch);
            if(node.getEnd()){
                result.add(new int[]{start, i});
            }
        }
    }
}


class Node{
    Node[] links;
    boolean isEndOfWord;

    Node(){
        links = new Node[26];
        isEndOfWord = false;
    }

    boolean containsKey(char ch){
        return links[ch-'a']!=null;
    }

    Node get(char ch){
        return links[ch-'a'];
    }

    void put(char ch){
        links[ch-'a'] = new Node();
    }

    void setEnd(){
        isEndOfWord = true;
    }

    boolean getEnd(){
        return isEndOfWord;
    }
}
