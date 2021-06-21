package PracticeProblems.DataStructures;

import org.junit.Assert;
import org.junit.Test;

public class DoublyLinkedListSpec {

    @Test
    public void constructEmptyList() {
        DoublyLinkedList list = new DoublyLinkedList();
        Assert.assertEquals(null, list.head);
        Assert.assertEquals(null, list.tail);
        Assert.assertEquals(0, list.size);
    }

    @Test
    public void constructFromValue() {
        DoublyLinkedList list = new DoublyLinkedList(6);
        int[] asList = new int[]{6};
        Assert.assertEquals(true, list.equals(asList));
        Assert.assertEquals(6, list.head.val);
        Assert.assertEquals(6, list.tail.val);
        Assert.assertEquals(1, list.size);
    }

    @Test
    public void constructFromList() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        Assert.assertEquals(true, list.equals(asList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(5, list.size);
    }

    @Test
    public void addOneFromEmpty() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(10);
        int[] asList = new int[]{10};
        Assert.assertEquals(true, list.equals(asList));
        Assert.assertEquals(10, list.head.val);
        Assert.assertEquals(10, list.tail.val);
        Assert.assertEquals(1, list.size);
    }

    @Test
    public void addFromOne() {
        DoublyLinkedList list = new DoublyLinkedList(7);
        list.add(9);
        int[] asList = new int[]{7, 9};
        Assert.assertEquals(true, list.equals(asList));
        Assert.assertEquals(7, list.head.val);
        Assert.assertEquals(9, list.tail.val);
        Assert.assertEquals(2, list.size);
    }

    @Test
    public void addMultiple() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(5);
        list.add(2);
        list.add(3);
        int[] asList = new int[]{5, 2, 3};
        Assert.assertEquals(true, list.equals(asList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(3, list.size);
    }

    @Test
    public void addAtIndex() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 2, 8, 4, 1, 3};
        Assert.assertEquals(true, list.addAtIndex(8, 2));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(6, list.size);
    }

    @Test
    public void addAtIndexHead() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{8, 5, 2, 4, 1, 3};
        Assert.assertEquals(true, list.addAtIndex(8, 0));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(8, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(6, list.size);
    }

    @Test
    public void addAtIndexOne() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 8, 2, 4, 1, 3};
        Assert.assertEquals(true, list.addAtIndex(8, 1));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(6, list.size);
    }

    @Test
    public void addAtIndexTail() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 2, 4, 1, 8, 3};
        Assert.assertEquals(true, list.addAtIndex(8, 4));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(6, list.size);
    }

    @Test
    public void addAtIndexOneBeforeTail() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 2, 4, 8, 1, 3};
        Assert.assertEquals(true, list.addAtIndex(8, 3));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(6, list.size);
    }

    @Test
    public void addAtIndexOneAfterTail() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 2, 4, 1, 3, 8};
        Assert.assertEquals(true, list.addAtIndex(8, 5));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(8, list.tail.val);
        Assert.assertEquals(6, list.size);
    }

    @Test
    public void addAtIndexOutOfBounds() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 2, 4, 1, 3};
        Assert.assertEquals(false, list.addAtIndex(8, 10));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(5, list.size);
    }

    @Test
    public void addAtIndexEmptyList() {
        DoublyLinkedList list = new DoublyLinkedList();
        int[] expectedList = new int[]{8};
        Assert.assertEquals(true, list.addAtIndex(8, 0));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(8, list.head.val);
        Assert.assertEquals(8, list.tail.val);
        Assert.assertEquals(1, list.size);
    }

    @Test
    public void removeFirst() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        list.removeFirst();
        int[] expectedList = new int[]{2, 4, 1, 3};
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(2, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(4, list.size);
    }

    @Test
    public void removeFirstEmptyList() {
        DoublyLinkedList list = new DoublyLinkedList();
        Assert.assertEquals(false, list.removeFirst());
        Assert.assertEquals(null, list.head);
        Assert.assertEquals(null, list.tail);
        Assert.assertEquals(0, list.size);
    }

    @Test
    public void removeFirstSizeOne() {
        DoublyLinkedList list = new DoublyLinkedList(5);
        Assert.assertEquals(true, list.removeFirst());
        Assert.assertEquals(null, list.head);
        Assert.assertEquals(null, list.tail);
        Assert.assertEquals(0, list.size);
    }

    @Test
    public void removeLast() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 2, 4, 1};
        Assert.assertEquals(true, list.removeLast());
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(1, list.tail.val);
        Assert.assertEquals(4, list.size);
    }

    @Test
    public void removeLastEmptyList() {
        DoublyLinkedList list = new DoublyLinkedList();
        Assert.assertEquals(false, list.removeLast());
        Assert.assertEquals(null, list.head);
        Assert.assertEquals(null, list.tail);
        Assert.assertEquals(0, list.size);
    }

    @Test
    public void removeLastSizeOne() {
        DoublyLinkedList list = new DoublyLinkedList(5);
        Assert.assertEquals(true, list.removeLast());
        Assert.assertEquals(null, list.head);
        Assert.assertEquals(null, list.tail);
        Assert.assertEquals(0, list.size);
    }

    @Test
    public void removeAtIndexFirst() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{2, 4, 1, 3};
        Assert.assertEquals(true, list.removeAtIndex(0));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(2, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(4, list.size);
    }

    @Test
    public void removeAtIndex() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 2, 1, 3};
        Assert.assertEquals(true, list.removeAtIndex(2));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(4, list.size);
    }

    @Test
    public void removeAtIndexMultiple() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 3};
        Assert.assertEquals(true, list.removeAtIndex(1));
        Assert.assertEquals(true, list.removeAtIndex(1));
        Assert.assertEquals(true, list.removeAtIndex(1));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(2, list.size);
    }

    @Test
    public void removeAtIndexLast() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 2, 4, 1};
        Assert.assertEquals(true, list.removeAtIndex(4));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(1, list.tail.val);
        Assert.assertEquals(4, list.size);
    }

    @Test
    public void removeAtIndexOutOfBounds() {
        int[] asList = new int[]{5, 2, 4, 1, 3};
        DoublyLinkedList list = new DoublyLinkedList(asList);
        int[] expectedList = new int[]{5, 2, 4, 1, 3};
        Assert.assertEquals(false, list.removeAtIndex(10));
        Assert.assertEquals(true, list.equals(expectedList));
        Assert.assertEquals(5, list.head.val);
        Assert.assertEquals(3, list.tail.val);
        Assert.assertEquals(5, list.size);
    }

    @Test
    public void equalToLinkedList() {
        int[] asList1 = new int[]{1, 2, 3, 4, 5};
        int[] asList2 = new int[]{1, 2, 3, 4, 5};
        DoublyLinkedList list1 = new DoublyLinkedList(asList1);
        DoublyLinkedList list2 = new DoublyLinkedList(asList2);
        Assert.assertEquals(true, list1.equals(list2));
    }

    @Test
    public void notEqualToLinkedList() {
        int[] asList1 = new int[]{1, 2, 3, 4, 5};
        int[] asList2 = new int[]{5, 4, 3, 2, 1};
        DoublyLinkedList list1 = new DoublyLinkedList(asList1);
        DoublyLinkedList list2 = new DoublyLinkedList(asList2);
        Assert.assertEquals(false, list1.equals(list2));
    }

    @Test
    public void equalToList() {
        int[] asList1 = new int[]{1, 2, 3, 4, 5};
        int[] asList2 = new int[]{1, 2, 3, 4, 5};
        DoublyLinkedList list1 = new DoublyLinkedList(asList1);
        Assert.assertEquals(true, list1.equals(asList2));
    }

    @Test
    public void notEqualToList() {
        int[] asList1 = new int[]{1, 2, 3, 4, 5};
        int[] asList2 = new int[]{5, 4, 3, 2, 1};
        DoublyLinkedList list1 = new DoublyLinkedList(asList1);
        Assert.assertEquals(false, list1.equals(asList2));
    }

}
