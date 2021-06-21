import java.util.*;

class Event {
    int start;
    int end;
    int count;

    public Event(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
    }

    public Event(Event e) {
        this.start = e.start;
        this.end = e.end;
        this.count = e.count;
    }
}

class SortByStart implements Comparator<Event> {
    public int compare(Event a, Event b) {
        return a.start - b.start;
    }
}

class SortByEnd implements Comparator<Event> {
    public int compare(Event a, Event b) {
        return a.end - b.end;
    }
}

class Temp {
    
    ArrayList<Integer> counts;

    public static void main(String[] args) {

        ArrayList<Event> events = new ArrayList<Event>();
        Event e = new Event(0, 2, 5);
        events.add(e);
        e = new Event(2, 5, 2);
        events.add(e);
        e = new Event(1, 6, 1);
        events.add(e);

        // Should be 94
        System.out.println(countAtTime(1, events, 100));

    }

    public static void addEvent(Event e) {
        // modify count at the start time - subtract the count
        // from start time to 1 less than the end time, also subtract count
        // at end time, add back count
        // 0 1 2 3
        // ArrayList has a set size, so if we had a lot of values
        // linked list?
        // 0 count    45 count

    }

    public static int countAtTime(int time, ArrayList<Event> events, int total) {

        // time for parameter
        // iterate through list of counts at times
        // return the count at t1 where t1 <= time and time <= next time (t2)
        // 2 - 10 - 13
        // 99 - 94 - 97
        // t = 8


        // Create two arrays
        // 1 - Sorted by start time
        // 2 - Sorted by end time
        // Then, given a time
        // iterate through both lists. sum up the counts for both start / end
        // return start - end

        ArrayList<Event> sortedByStart = new ArrayList<Event>();
        ArrayList<Event> sortedByEnd = new ArrayList<Event>();
        // O(n)
        for (Event e : events) {
            sortedByStart.add(new Event(e));
            sortedByEnd.add(new Event(e));
        }
        // O(nlogn)
        Collections.sort(sortedByStart, new SortByStart());
        Collections.sort(sortedByEnd, new SortByEnd());
        int startSum = 0;
        int endSum = 0;

        // O(n)
        for (int i = 0; i < sortedByStart.size(); i++) {
            if (sortedByStart.get(i).start > time) {
                break;
            }
            startSum += sortedByStart.get(i).count;
        }

        // O(n)
        for (int i = 0; i < sortedByEnd.size(); i++) {
            if (sortedByEnd.get(i).end > time) {
                break;
            }
            endSum += sortedByEnd.get(i).count;
        }

        return total - startSum + endSum;



    }

}