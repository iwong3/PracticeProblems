class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class MergeKSortedLists {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(3);
        ListNode b = new ListNode(2);
        b.next = new ListNode(4);
        ListNode[] lists = new ListNode[]{a, b};

        ListNode ans = mergeKLists(lists);
        ListNode temp = ans;
        while (temp != null) {
            System.out.print("["+temp.val+"]");
            temp = temp.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        
        // algo
        // merge lists 2 at a time, repeat until everything is merged
        // ex. lists a, b, c, d
        // 1. merge ab, cd
        // 2. merge abcd
        // TRY - using heaps
        
        if (lists.length < 1) {
            return null;
        }
        
        ListNode[] toMerge = lists;
        ListNode[] newList = lists;
        
        while (toMerge.length > 1) {
            if (toMerge.length % 2 == 0) {
                newList = new ListNode[toMerge.length / 2];
            } else {
                newList = new ListNode[toMerge.length / 2 + 1];
            }
            
            for (int i = 0; i < newList.length; i++) {
                if (i * 2 == toMerge.length - 1) {
                    newList[i] = toMerge[i * 2];
                    continue;
                }
                newList[i] = mergeLists(toMerge[i * 2], toMerge[i * 2 + 1]);
            }
            
            toMerge = newList;
        }
        
        return toMerge[0];
        
    }
    
    private static ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode finalAns = ans;
        ListNode list1 = l1;
        ListNode list2 = l2;
        
        while (list1 != null || list2 != null) {
            // if we added all of list1, append list2
            if (list1 == null) {
                ans.next = list2;
                break;
            }
            // if we added all of list2, append list1
            if (list2 == null) {
                ans.next = list1;
                break;
            }
            if (list1.val < list2.val) {
                ans.next = list1;
                ans = ans.next;
                list1 = list1.next;
            } else {
                ans.next = list2;
                ans = ans.next;
                list2 = list2.next;
            }
        }
        
        finalAns = finalAns.next;
        return finalAns;
    }
    
}