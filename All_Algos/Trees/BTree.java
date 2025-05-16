class BTree {
    class BTreeNode {
        int[] keys;
        int t;
        BTreeNode[] children;
        int n;
        boolean leaf;

        public BTreeNode(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.keys = new int[2 * t - 1];
            this.children = new BTreeNode[2 * t];
            this.n = 0;
        }

        public void traverse() {
            for (int i = 0; i < n; i++) {
                if (!leaf)
                    children[i].traverse();
                System.out.print(keys[i] + " ");
            }
            if (!leaf)
                children[n].traverse();
        }

        public BTreeNode search(int key) {
            int i = 0;
            while (i < n && key > keys[i])
                i++;
            if (i < n && keys[i] == key)
                return this;
            if (leaf)
                return null;
            return children[i].search(key);
        }

        public void insertNonFull(int key) {
            int i = n - 1;
            if (leaf) {
                while (i >= 0 && keys[i] > key) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = key;
                n++;
            } else {
                while (i >= 0 && keys[i] > key)
                    i--;
                if (children[i + 1].n == 2 * t - 1) {
                    splitChild(i + 1, children[i + 1]);
                    if (keys[i + 1] < key)
                        i++;
                }
                children[i + 1].insertNonFull(key);
            }
        }

        public void splitChild(int i, BTreeNode y) {
            BTreeNode z = new BTreeNode(y.t, y.leaf);
            z.n = t - 1;

            for (int j = 0; j < t - 1; j++)
                z.keys[j] = y.keys[j + t];
            if (!y.leaf)
                for (int j = 0; j < t; j++)
                    z.children[j] = y.children[j + t];

            y.n = t - 1;
            for (int j = n; j >= i + 1; j--)
                children[j + 1] = children[j];
            children[i + 1] = z;

            for (int j = n - 1; j >= i; j--)
                keys[j + 1] = keys[j];
            keys[i] = y.keys[t - 1];
            n++;
        }
    }

    BTreeNode root;
    int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public void traverse() {
        if (root != null)
            root.traverse();
    }

    public void insert(int key) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root);
                int i = 0;
                if (s.keys[0] < key)
                    i++;
                s.children[i].insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    public boolean search(int key) {
        return root != null && root.search(key) != null;
    }

    public static void main(String[] args) {
        System.out.println("B-Tree:");
        BTree btree = new BTree(3); // Min degree 3
        btree.insert(10);
        btree.insert(20);
        btree.insert(5);
        btree.insert(6);
        btree.insert(12);
        btree.insert(30);
        btree.insert(7);
        btree.insert(17);
        btree.traverse();
    }
}