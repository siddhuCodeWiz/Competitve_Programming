package Day20_TopologicalSort;

/*Imagine you're the master chef in a renowned kitchen, tasked with preparing a 
spectacular dinner consisting of numDishes unique dishes, labeled from 
0 to numDishes - 1. However, the recipes have dependencies—certain dishes can 
only be prepared after completing others. You’re given a list called dependecies, 
where each element dependencies[i] = [Xi, Yi] means that you must finish 
preparing dish Yi before starting dish Xi.

For instance, the pair [2, 1] implies that to create dish 2, 
dish 1 must be prepared first.

Return the ordering of dishes that a chef should take to finish all dishes.
	- the result set should follow the given order of conditions.
	- If it is impossible to complete all dishes, return an empty set.


Input Format:
-------------
Line-1: An integer numDishes, number of Dishes.
Line-2: An integer m, number of dependencies.
Next m lines: Two space separated integers, Xi and Yi.

Output Format:
--------------
Return a list of integers, the ordering of dishes that a chef should finish.

Example 1:
------------
Input=
4
3
1 2
3 0
0 1
Output=
[2, 1, 0, 3]


Explanation: There are 4 dishes. Since dish 1 requires dish 2, dish 3 requires 
dish 0 and dish 0 requires dish 1, you can prepare dishes in the order 2 1 0 3.


Example 2:
----------
Input=
2
2
1 0
0 1
Output=
[]

Explanation: There are 2 dishes, but dish 1 depends on dish 0 and dish 0 depends 
on dish 1. This circular dependency makes it impossible to prepare all dishes.

Constraints:

- 1 <= numDishes <= 2000  
- 0 <= m <= 5000  
- dependencies[i].length == 2  
- 0 <= Xi, Yi < numDishes  
- All the dependency pairs are unique. */

import java.util.*;

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph g = new Graph(n);
        for(int i=0; i<m; i++){
            g.addDishDependency(sc.nextInt(), sc.nextInt());
        }
        g.findDishOrder();
        System.out.println(g.sol);

        sc.close();
    }
}

class Graph{
    HashMap<Integer, ArrayList<Integer>> adjList;
    int[] inorderDegree;
    int nodes;
    ArrayList<Integer> sol;

    Graph(int nodes){
        this.nodes = nodes;
        this.inorderDegree = new int[nodes];
        Arrays.fill(this.inorderDegree, 0);
        adjList = new HashMap<>();

        for(int i=0; i<nodes; i++){
            adjList.put(i, new ArrayList<>());
        }
        sol = new ArrayList<>();
    }

    void addDishDependency(int start, int end){
        adjList.get(end).add(start);
        inorderDegree[start]+=1;
    }

    void findDishOrder(){
        Queue<Integer> que = new LinkedList<>();

        for(int i=0; i<nodes; i++){
            if(inorderDegree[i]==0){
                que.add(i);
            }
        }

        while(!que.isEmpty()){
            int popped = que.poll();
            sol.add(popped);
            for(int i : adjList.get(popped)){
                inorderDegree[i]-=1;
                if(inorderDegree[i]==0){
                    que.add(i);
                }
            }
        }
    }

}
