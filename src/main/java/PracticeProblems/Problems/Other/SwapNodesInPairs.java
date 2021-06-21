class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);
        a.next.next.next = new ListNode(4);

        ListNode ans = swapPairs(a);
        ListNode temp = ans;
        while (temp != null) {
            System.out.print("["+temp.val+"]");
            temp = temp.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode ans = new ListNode(0);
        ans.next = head;
        ListNode a = ans;
        ListNode b = head;
        
        while (b != null && b.next != null) {
            a.next = b.next;
            b.next = a.next.next;
            a.next.next = b;
            a = a.next.next;
            b = b.next;
        }
        return ans.next;
    }

}