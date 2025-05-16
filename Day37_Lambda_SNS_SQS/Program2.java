/*You are given a crystal with an energy level n. Your goal is to discover all the 
different ways this crystal could have been created by combining smaller shards.

Each combination must:
- Use only shards with energy values between 2 and n - 1.
- Be represented as a list of shard values whose product equals n.
- Use any number of shards (minimum 2), and the order doesn't matter.

Your task is to return all unique shard combinations that can multiply together 
to recreate the original crystal.

Example 1:
---------
Input:
28

Output:
[[2, 14], [2, 2, 7], [4, 7]]

Example 2:
----------
Input:
23

Output:
[]



Constraints:
- 1 <= n <= 10^4
- Only shards with energy between 2 and n - 1 can be used. */
import java.util.*;

public class Program2 {
    Set<List<Integer>> sols;
    int n;

    Program2(int n){
        sols = new HashSet<>();
        this.n = n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        Program2 p2 = new Program2(n);
        p2.find(1, 2, new ArrayList<>());
        System.out.println(p2.sols);
    }

    void find(int currPrd, int start, List<Integer> sol){
        if (currPrd == n && sol.size() >= 2) {
            sols.add(new ArrayList<>(sol));
            return;
        }
        if (currPrd > n) return;

        for (int i = start; i < n; i++) {
            if (n % i == 0 && currPrd * i <= n) {
                sol.add(i);
                find(currPrd * i, i, sol); 
                sol.remove(sol.size() - 1);
            }
        }
    }
}
