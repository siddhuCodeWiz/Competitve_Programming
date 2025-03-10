package Day19;

/*AlphaCipher is a string formed from another string by rearranging its letters

You are given a string S.
Your task is to check, can any one of the AlphaCipher is a palindrome or not.

Input Format:
-------------
A string S

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
carrace

Sample Output-1:
----------------
true


Sample Input-2:
---------------
code

Sample Output-2:
----------------
false */


import java.util.*;

class Program1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        HashMap<Character, Integer> hm = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            if(hm.containsKey(s.charAt(i))){
                hm.put(s.charAt(i), hm.get(s.charAt(i))+1);
            }else{
                hm.put(s.charAt(i), 1);
            }
        }

        System.out.println(checkAlphaCipher(s, hm));

        sc.close();
    }

    public static boolean checkAlphaCipher(String s, HashMap<Character, Integer> hm){
        if(s.length()%2==0){
            for(int a : hm.values()){
                if(a%2!=0){
                    return false;
                }
            }
        } else{
            int oddCount = 0;
            for(int a : hm.values()){
                if(a%2!=0){
                    oddCount++;
                    if(oddCount>1){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}