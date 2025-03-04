package Day16_DisjointSet;

/*There are N cities, and M routes[], each route is a path between two cities.
routes[i] = [city1, city2], there is a travel route between city1 and city2.
Each city is numbered from 0 to N-1.
 
There are one or more Regions formed among N cities. 
A Region is formed in such way that you can travel between any two cities 
in the region that are connected directly and indirectly.
 
Your task is to findout the number of regions formed between N cities. 
 
Input Format:
-------------
Line-1: Two space separated integers N and M, number of cities and routes
Next M lines: Two space separated integers city1, city2.
 
Output Format:
--------------
Print an integer, number of regions formed.
 
 
Sample Input-1:
---------------
5 4
0 1
0 2
1 2
3 4
 
Sample Output-1:
----------------
2
 
 
Sample Input-2:
---------------
5 6
0 1
0 2
2 3
1 2
1 4
2 4
 
Sample Output-2:
----------------
1
 
Note: Look HINT for explanation. */



import java.util.*;

class Program1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        DisjointSet ds = new DisjointSet();
        
        ds.n = sc.nextInt();
        ds.m = sc.nextInt();
        
        ds.arr = new int[ds.n];
        Arrays.fill(ds.arr, -1);
        
        for(int i=0; i<ds.m; i++){
            ds.union(sc.nextInt(), sc.nextInt());
        }
        
        System.out.println(ds.findRegions());
        sc.close();
    }
}

class DisjointSet{
    int[] arr;
    int n;
    int m;
    int unique;
    
    void union(int start, int end){
        int start_p = find(start);
        int end_p = find(end);
        
        if(start_p < end_p){
            arr[end_p] = start_p;
        } else if(start_p > end_p){
            arr[start_p] = end_p;
        }
        
    }
    
    int find(int v){
        if(arr[v] == -1){
            return v;
        }
        
        return find(arr[v]);
    }
    
    int findRegions(){
        HashSet<Integer> hs = new HashSet<>();
        for(int i=0; i<n; i++){
            if(arr[i]!=-1)
                hs.add(find(arr[i]));
        }
        return hs.size();
    }
    
}