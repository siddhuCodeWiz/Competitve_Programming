package LAB;

/*d) Write a JAVA Program to implement a segment tree with its operations In Hyderabad 
after a long pandemic gap, the Telangana Youth festival Is Organized at HITEX. 
In HITEX, there are a lot of programs planned. During the festival in order to maintain the rules 
of Pandemic, they put a constraint that one person can only attend any one of the programs in one 
day according to planned days. Now it’s your aim to implement the "Solution" class in such a way 
that you need to return the maximum number of programs you can attend according to given 
constraints. 
Explanation: You have a list of programs ‘p’ and days ’d’, where you can attend only one program 
on one day. Programs [p] = [first day, last day], p is the program's first day and the last day. 
Input Format: 
Line-1: An integer N, number of programs. 
Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by 
space. Output Format: 
An integer, the maximum number of programs you can attend. 
Sample Input-1: 
4 
1 2,2 4,2 3,2 2 
Sample Output-1: 
4 
Sample Input-2: 
6 
1 5,2 3,2 4,2 2,3 4,3 5 
Sample Output-2: 
5  */

import java.util.*;

class Program1d {

    static class Program {
        int start;
        int end;

        Program(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public static int findMaxPrograms(int n, List<Program> programs) {
        int maxDays = 0;
        for (Program p : programs) {
            if (p.end > maxDays)
                maxDays = p.end;
        }

        SegmentTree st = new SegmentTree(maxDays);

        programs.sort((a, b) -> a.end - b.end);

        int count = 0;

        for (Program p : programs) {
            int day = st.query(1, 1, maxDays, p.start, p.end);
            if (day != -1) {
                st.update(1, 1, maxDays, day);
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        String[] input = sc.nextLine().split(",");
        List<Program> programs = new ArrayList<>();

        for (String pair : input) {
            String[] days = pair.trim().split(" ");
            int start = Integer.parseInt(days[0]);
            int end = Integer.parseInt(days[1]);
            programs.add(new Program(start, end));
        }

        int result = findMaxPrograms(n, programs);
        System.out.println(result);

        sc.close();

    }
}

class SegmentTree {
    int n;
    int[] tree;

    SegmentTree(int n) {
        this.n = n;
        tree = new int[4 * n];
        build(1, 1, n);
    }

    void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    int query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l || tree[node] == 0)
            return -1;
        if (l == r)
            return l;

        int mid = (l + r) / 2;
        int left = query(node * 2, l, mid, ql, qr);
        if (left != -1)
            return left;
        return query(2 * node + 1, mid + 1, r, ql, qr);
    }

    void update(int node, int l, int r, int day) {
        if (l == r) {
            tree[l] = 0;
            return;
        }

        int mid = (l + r) / 2;
        if (day <= mid) {
            update(node * 2, l, mid, day);
        } else {
            update(2 * node + 1, mid + 1, r, day);
        }

        tree[mid] = tree[node * 2] + tree[2 * node + 1];
    }
}