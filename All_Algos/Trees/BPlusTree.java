import java.util.*;

class BPlusTree {
    class BPlusTreeNode {
        boolean isLeaf;
        List<Integer> keys;
        List<BPlusTreeNode> children;
        BPlusTreeNode next; // For linked list of leaves

        BPlusTreeNode(boolean isLeaf) {
            this.isLeaf = isLeaf;
            keys = new ArrayList<>();
            children = new ArrayList<>();
        }
    }

    int order;
    BPlusTreeNode root;

    BPlusTree(int order) {
        this.order = order;
        root = new BPlusTreeNode(true);
    }

    public void insert(int key) {
        BPlusTreeNode r = root;
        if (r.keys.size() == order - 1) {
            BPlusTreeNode newRoot = new BPlusTreeNode(false);
            newRoot.children.add(r);
            splitChild(newRoot, 0, r);
            root = newRoot;
        }
        insertNonFull(root, key);
    }

    private void insertNonFull(BPlusTreeNode node, int key) {
        if (node.isLeaf) {
            int pos = Collections.binarySearch(node.keys, key);
            if (pos < 0)
                pos = -pos - 1;
            node.keys.add(pos, key);
        } else {
            int i = 0;
            while (i < node.keys.size() && key > node.keys.get(i))
                i++;
            BPlusTreeNode child = node.children.get(i);
            if (child.keys.size() == order - 1) {
                splitChild(node, i, child);
                if (key > node.keys.get(i))
                    i++;
            }
            insertNonFull(node.children.get(i), key);
        }
    }

    private void splitChild(BPlusTreeNode parent, int index, BPlusTreeNode node) {
        int mid = order / 2;
        BPlusTreeNode sibling = new BPlusTreeNode(node.isLeaf);
        sibling.keys.addAll(node.keys.subList(mid, node.keys.size()));
        node.keys = new ArrayList<>(node.keys.subList(0, mid));

        if (!node.isLeaf) {
            sibling.children.addAll(node.children.subList(mid + 1, node.children.size()));
            node.children = new ArrayList<>(node.children.subList(0, mid + 1));
            parent.keys.add(index, sibling.keys.get(0));
        } else {
            sibling.next = node.next;
            node.next = sibling;
            parent.keys.add(index, sibling.keys.get(0));
        }
        parent.children.add(index + 1, sibling);
    }

    public void printLeaves() {
        BPlusTreeNode curr = root;
        while (!curr.isLeaf)
            curr = curr.children.get(0);
        while (curr != null) {
            System.out.print(curr.keys + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }


    public static void main(String[] args) {
        System.out.println("\nB+ Tree:");
        BPlusTree bptree = new BPlusTree(4); // Order 4
        int[] values = {10, 20, 5, 6, 12, 30, 7, 17};
        for (int val : values) bptree.insert(val);
        bptree.printLeaves();
    }
}
