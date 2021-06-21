package PracticeProblems.DataStructures;

public class SinglyLinkedList {

    public class Node {

        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

    }

    Node head;
    Node tail;
    int size;

    /** create a new empty linked list */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /** create a new linked list with one node */
    public SinglyLinkedList(int val) {
        Node n = new Node(val);
        this.head = n;
        this.tail = n;
        this.size = 1;
    }

    /** create a new linked list from an array */
    public SinglyLinkedList(int[] list) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        for (int val : list) {
            this.add(val);
        }
    }

    /** add value to linked list */
    public void add(int val) {
        Node n = new Node(val);
        // first node in list
        if (this.head == null) {
            this.head = n;
            this.tail = n;
        // one node in list
        } else if (this.size == 1) {
            this.head.next = n;
            this.tail = n;
        } else {
            this.tail.next = n;
            this.tail = n;
        }
        this.size++;
    }

    /** add value at given index */
    public boolean addAtIndex(int val, int index) {
        if (index > this.size) {
            return false;
        }
        if (index == this.size) {
            add(val);
            return true;
        }

        Node n = new Node(val);
        if (index == 0) {
            n.next = this.head;
            this.head = n;
            this.size++;
            return true;
        }

        Node curr = this.head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        Node next = curr.next;
        curr.next = n;
        n.next = next;
        this.size++;
        return true;
    }

    /** remove the first node in the list */
    public boolean removeFirst() {
        if (this.size == 0) {
            return false;
        }
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            return true;
        }
        this.head = this.head.next;
        this.size--;
        return true;
    }

    public boolean removeLast() {
        if (this.size == 0) {
            return false;
        }
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            return true;
        }
        return removeAtIndex(this.size - 1);
    }

    public boolean removeAtIndex(int index) {
        if (index >= this.size) {
            return false;
        }
        if (index == 0) {
            return removeFirst();
        }
        Node curr = this.head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        if (null == curr.next) {
            this.tail = curr;
        }
        this.size--;
        return true;

    }

    /** check if two linked lists are equal */
    public boolean equals(SinglyLinkedList list) {
        Node currA = this.head;
        Node currB = list.head;
        while (currA != null && currB != null) {
            if (currA.val == currB.val) {
                currA = currA.next;
                currB = currB.next;
            } else {
                return false;
            }
        }
        return currA == currB;

    }

    /** check if linked list is equal to list */
    public boolean equals(int[] list) {
        Node curr = this.head;
        for (int i = 0; i < list.length; i++) {
            if (curr == null) {
                return false;
            }
            if (curr.val != list[i]) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }


}
