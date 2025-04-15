package Day32;

/*In Hyderabad after a long pandemic gap, the Telangana Youth festival is 
Organized at HITEX. In HITEX, there are a lot of programs planned. During 
the festival in order to maintain the rules of Pandemic, they put a 
constraint that one person can only attend any one of the programs in 
one day according to planned days.

Now it’s your aim to implement the "Solution" class in such a way that 
you need to return the maximum number of programs you can attend according 
to given constraints.

Explanation:
You have a list of programs 'p' and days 'd', where you can attend only 

one program on one day. Programs [p] = [first day, last day], 
p is the program's first day and the last day.


Input Format:
-------------
Line-1: An integer N, number of programs.
Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by space.

Output Format:
--------------
An integer, the maximum number of programs you can attend.


Sample Input-1:
---------------
4
1 2,2 4,2 3,2 2

Sample Output-1:
----------------
4

Sample Input-2:
---------------
6
1 5,2 3,2 4,2 2,3 4,3 5

Sample Output-2:
----------------
5 */
import java.util.*;

public class Program2 {

    static class Event {
        int start, end;
        Event(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); sc.nextLine();
        String s = sc.nextLine();
        String[] e = s.split(",");
        sc.close();
        List<Event> events = new ArrayList<>();
        for(String str : e){
            String[] parts = str.trim().split(" ");
            events.add(new Event(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        events.sort(Comparator.comparingInt(ev -> ev.start));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int day = 1, i = 0, attended = 0;
        int maxLastDay = events.stream().mapToInt(ev -> ev.end).max().getAsInt();

        while (day <= maxLastDay) {
            while (i < events.size() && events.get(i).start == day) {
                pq.add(events.get(i).end);
                i++;
            }

            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                pq.poll();
                attended++;
            }

            day++;
        }

        System.out.println(attended);
    }
}
