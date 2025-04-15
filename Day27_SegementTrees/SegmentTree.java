package Day27_SegementTrees;
/*Given an array we need to tell the maximum number in the given range 
Do it using segment trees for faster access
Sample Input:
10
8 2 5 1 4 5 3 9 6 10
3
0 9
4 8
1 4


Operation	Complexity
Building the Segment Tree	O(n)
Query (Range Maximum Query)	 O(log(n))
 */
import java.util.*;

public class SegmentTree {
    int[] arr;  // Original input array
    int[] seg;  // Segment tree array

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree();
        Scanner sc = new Scanner(System.in);

        // Read the input array size
        int n = sc.nextInt();
        st.arr = new int[n];
        st.seg = new int[4 * n]; // Initialize segment tree array
        /*The worst-case space requirement is at most 4 * n, derived from:
        A complete binary tree of height log₂(n).
        In a full binary tree, a perfectly balanced tree has at most 2n - 1 nodes.
        To simplify indexing, we allocate an array large enough for any n, which is 4 * n. */

        // Read the array elements
        for (int i = 0; i < n; i++) {
            st.arr[i] = sc.nextInt();
        }

        // Build the segment tree
        st.build(0, 0, n - 1);

        // Read the number of queries
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(st.query(0, 0, n - 1, l, r));
        }
        sc.close();
    }

    // Build function to construct the segment tree
    public void build(int ind, int low, int high) {
        if (low == high) {
            seg[ind] = arr[low];  // Store the value from the input array
            return;
        }

        int mid = (low + high) / 2;
        build(2 * ind + 1, low, mid);      // Build left child
        build(2 * ind + 2, mid + 1, high); // Build right child

        // Store the maximum value in the segment
        seg[ind] = Math.max(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    // Query function to find the maximum in a given range [l, h]
    public int query(int ind, int low, int high, int l, int h) {
        // Case 1: If the current range is completely inside the query range
        if (low >= l && high <= h) return seg[ind];

        // Case 2: If the current range is completely outside the query range
        if (high < l || low > h) return Integer.MIN_VALUE;

        // Case 3: If the current range partially overlaps with the query range
        int mid = (low + high) / 2;
        int leftMax = query(2 * ind + 1, low, mid, l, h);
        int rightMax = query(2 * ind + 2, mid + 1, high, l, h);

        return Math.max(leftMax, rightMax);
    }
}
