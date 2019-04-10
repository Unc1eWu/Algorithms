/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Node(Item itm) {
            item = itm;
            next = null;
            prev = null;
        }
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    public void addFirst(Item item) {
        checkItem(item);
        Node n = new Node(item);
        if (isEmpty()) {
            last = n;
            first = n;
        } else {
            Node oldFirst = first;
            first = n;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        checkItem(item);
        Node n = new Node(item);
        if (isEmpty()) {
            first = n;
            last = n;
        } else {
            last.next = n;
            n.prev = last;
            last = n;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item itm = first.item;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            first.next.prev = null;
            first = first.next;
        }
        return itm;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item rItem = last.item;
        size--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
            last = last.prev;
        }
        return rItem;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Iterator remove function not supported.");
        }

    }

}