import java.util.*;

// Sample Input-1:
// ---------------
// 6 4
// 1,5,10,8,6,4

// Sample Output-1:
// ----------------
// 31

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int coup = sc.nextInt();
        sc.nextLine();
        String[] num = sc.nextLine().split(",");
        int[] nums = Arrays.stream(num).mapToInt(a -> Integer.parseInt(a)).toArray();

        // int toUse_min = Integer.MIN_VALUE;
        int toUse_max = Integer.MIN_VALUE;
        
        int total = 0;
        for(int i=0; i<n; i++){
            if(nums[i]<=coup && nums[i] >= toUse_max){
                toUse_max = nums[i];
            }
            total+=nums[i];
        }

        if(toUse_max!=Integer.MIN_VALUE)
            System.out.println(total-toUse_max+1);
        else 
            System.out.println(total+1);
    }
}