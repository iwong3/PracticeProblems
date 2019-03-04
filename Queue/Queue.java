class Node {

    int value;
    Node next;
    
    public Node(int value) {
        this.value = value;
    }
    
}

class Queue {
    
    Node head;
    Node end;
    int size;
    
    public Queue(Node n) {
        this.head = n;
        this.end = n;
        this.size = 1;
    }
    
    public void pushBack(int num) {
        Node temp = new Node(num);
        // If Queue is empty, set head and end to new node
        if (head == null) {
            this.head = temp;
            this.end = temp;
        // Otherwise, set new node to be end's next, then update end
        } else {
            this.end.next = temp;
            this.end = temp;
        }
        this.size++;
    }
    
    // Returns -1 if nothing in the queue
    public int popFront() {
        if (this.head == null) {
            return -1;
        }
        int headValue = this.head.value;
        this.head = this.head.next;
        this.size--;
        return headValue;
    }
    
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print("["+temp.value+"]");
            temp = temp.next;
        }
    }
    
}

class Test {

    public static void main(String[] args) {

        Node n = new Node(1);
        Queue q = new Queue(n);
        q.display();

    }

}