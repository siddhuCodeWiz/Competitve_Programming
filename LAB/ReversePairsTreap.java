package LAB;

/*Given an integer array nums, return the number of reverse pairs in the array. A reverse pair is a 
pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j]. 
Example 1: 
Input: nums = [1,3,2,3,1] 
Output: 2 
Example 2: 
Input: nums = [2,4,3,5,1] 
Output: 3 
Constraints: 
1 <= nums.length <= 5 * 104 -2^31 <= nums[i] <= 2^31 – 1 */

import java.util.*;

public class ReversePairsTreap {

    static class TreapNode {
        long key;
        int priority;
        int count; // Number of duplicates
        int size;
        TreapNode left, right;

        TreapNode(long key) {
            this.key = key;
            this.priority = new Random().nextInt();
            this.count = 1;
            this.size = 1;
        }
    }

    static class Treap {
        TreapNode root;

        int getSize(TreapNode node) {
            return node == null ? 0 : node.size;
        }

        void updateSize(TreapNode node) {
            if (node != null) {
                node.size = node.count + getSize(node.left) + getSize(node.right);
            }
        }

        TreapNode rotateRight(TreapNode p) {
            TreapNode q = p.left;
            p.left = q.right;
            q.right = p;
            updateSize(p);
            updateSize(q);
            return q;
        }

        TreapNode rotateLeft(TreapNode p) {
            TreapNode q = p.right;
            p.right = q.left;
            q.left = p;
            updateSize(p);
            updateSize(q);
            return q;
        }

        TreapNode insert(TreapNode node, long key) {
            if (node == null) return new TreapNode(key);

            if (key == node.key) {
                node.count++;
            } else if (key < node.key) {
                node.left = insert(node.left, key);
                if (node.left.priority > node.priority)
                    node = rotateRight(node);
            } else {
                node.right = insert(node.right, key);
                if (node.right.priority > node.priority)
                    node = rotateLeft(node);
            }

            updateSize(node);
            return node;
        }

        int countLessThan(TreapNode node, long key) {
            if (node == null) return 0;

            if (key <= node.key) {
                return countLessThan(node.left, key);
            } else {
                return getSize(node.left) + node.count + countLessThan(node.right, key);
            }
        }

        void insert(long key) {
            root = insert(root, key);
        }

        int countLessThan(long key) {
            return countLessThan(root, key);
        }
    }

    public int reversePairs(int[] nums) {
        Treap treap = new Treap();
        int result = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            result += treap.countLessThan((long) nums[i]);
            treap.insert((long) nums[i] * 2);
        }

        return result;
    }

    public static void main(String[] args) {
        ReversePairsTreap obj = new ReversePairsTreap();

        int[] nums1 = {1, 3, 2, 3, 1};
        int[] nums2 = {2, 4, 3, 5, 1};

        System.out.println("Output 1: " + obj.reversePairs(nums1)); // Output: 2
        System.out.println("Output 2: " + obj.reversePairs(nums2)); // Output: 3
    }
}
