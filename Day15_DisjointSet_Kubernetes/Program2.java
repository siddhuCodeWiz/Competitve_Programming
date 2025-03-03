package Day15_DisjointSet_Kubernetes;

/*In the world of secret codes and cryptography, you are entrusted with deciphering a hidden message. 
You have two encoded keys, key1 and key2, both of equal length. Each character 
in key1 is paired with the corresponding character in key2. 

This relationship follows the standard rules of an equivalence cipher:
• Self-Mapping: Every character inherently maps to itself.  
• Mutual Mapping: If a character from key1 maps to one in key2, then that 
  character in key2 maps back to the one in key1.  
• Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
  are all interchangeable in this cipher.

Using this mapping, you must decode a cipherText, by replacing every character 
with the smallest equivalent character from its equivalence group. 
The result should be the relatively smallest possible decoded message.


Input Format:
-------------
Three space separated strings, key1 , key2 and cipherText

Output Format:
--------------
Print a string, decoded message which is relatively smallest string of cipherText.

Example 1: 
input=
attitude progress apriori
output=
aaogoog


Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
[d, e, s]. By substituting each character in cipherText with the smallest from 
its group, you decode the message to "aaogoog".


Constraints:  
• 1 <= key1.length, key2.length, cipherText.length <= 1000  
• key1.length == key2.length  
• key1, key2, and cipherText consist solely of lowercase English letters.
 */


import java.util.*;

class Program2{
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // System.out.println((int)'a'-97);
    DisjointSet ds = new DisjointSet();
    ds.key1 = sc.next();
    ds.key2 = sc.next();
    ds.cipherText = sc.next();
    System.out.println(ds.decodeMessage());
    sc.close();
  }
}

class DisjointSet{
  String key1;
  String key2;
  String cipherText;
  StringBuilder decoded;
  int[] arr;

  DisjointSet(){
    arr = new int[26];
    Arrays.fill(arr, -1);
    decoded = new StringBuilder("");
  }

  private int find(int v){   // Find method return the parent of the passed node
    if(arr[v] == -1){
      return v;       // If it is -1 then it is the parent, return the value
    }
    return find(arr[v]);      // Else call the recursion function to find the parent
  }

  private void union(int char1, int char2){
    int char1_parent = find(char1);
    int char2_parent = find(char2);

    if(char1_parent < char2_parent){
      arr[char2_parent] = char1_parent;
    }else if(char1_parent> char2_parent){
      arr[char1_parent] = char2_parent;
    }
  }

  protected String decodeMessage(){
    if(key1.length() != key2.length()){
      return "";
    }
    for(int i=0; i<key1.length(); i++){
      union((int)key1.charAt(i) - 97, (int)key2.charAt(i) - 97);
    }
    for(int i=0; i<cipherText.length(); i++){
      decoded.append((char)(find((int)cipherText.charAt(i) - 97)+97));
    }
    return decoded.toString();
  }
}
