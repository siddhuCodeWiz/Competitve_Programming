package Day6;
import java.util.*;

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        
        String[] ss1 = s1.split(" ");
        String[] ss2 = s2.split(" ");
        
        Main m = new Main();
        
        for(int i=0; i<ss1.length; i++){
            m.preorder.add(Integer.parseInt(ss1[i]));
        }
        
        for(int i=0; i<ss2.length; i++){
            m.postorder.add(Integer.parseInt(ss2[i]));
        }
        
        m.buildTree();
    }
}

class Main{
    int n;
    ArrayList<Integer> preorder;
    ArrayList<Integer> postorder;
    boolean[] visited;
    Queue<Integer> que;
    LinkedHashMap<Integer, ArrayList<Integer>> levelOrder;
    HashMap<Integer, Integer> pre;
    HashMap<Integer, Integer> post;

    Main(){
        preorder = new ArrayList<>();
        postorder = new ArrayList<>();
        visited = new boolean[100];
        levelOrder = new LinkedHashMap<>();
        pre = new HashMap<>();
        post = new HashMap<>();
        que = new LinkedList<>();
    }
    
    protected void buildTree(){
        n = preorder.size();
        for(int i=0; i<n; i++){
            pre.put(preorder.get(i), i);
        }
        for(int i=0; i<n; i++){
            post.put(postorder.get(i), i);
        }
        
        if(preorder.get(0)==postorder.get(n-1)){
            que.add(preorder.get(0));
            visited[preorder.get(0)] = true;
            buildTreeHelper(1);
        }

    }

    private void buildTreeHelper(int level){
        int si = que.size();
        for(int i=0; i<si; i++){
            int currEle = que.poll();
            System.out.println(currEle);
            if(levelOrder.containsKey(level)){
                levelOrder.get(level).add(currEle);
            }else{
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(currEle);
                levelOrder.put(level, arr);
            }
    
            int leftInd = pre.get(currEle)+1;
            int rightInd = post.get(currEle)-1;
            System.out.println("Left Ind: "+leftInd+" Right Ind: "+rightInd);
    
            if(leftInd<0 || leftInd>=n) return;
            if(rightInd<0 || rightInd>=n) return;
            
            if(preorder.get(leftInd)==postorder.get(rightInd) && visited[preorder.get(leftInd)]==false){
                que.add(preorder.get(leftInd));
            }
            
            else if(visited[preorder.get(leftInd)]==false && visited[postorder.get(rightInd)]){
                que.add(preorder.get(leftInd));
                que.add(postorder.get(rightInd));
                visited[preorder.get(leftInd)] = true;
                visited[postorder.get(rightInd)] = true;
            }
            
        }
        buildTreeHelper(level++);
    }
    
    public void printLevel(){
        for(Map.Entry<Integer, ArrayList<Integer>> curr : levelOrder.entrySet()){
            System.out.print(curr.getValue());
        }
    }
}
