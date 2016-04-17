package org.alext.linkedList;

/**
 * Created by alex on 4/15/2016.
 */
public class LinkedList<T> {

    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<T>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }


    }

    public int size() {
        Node<T> pointer = head;
        int size = 0;

        while (pointer != null) {
            pointer = pointer.next;
            size++;
        }
        return size;
    }

    public T get() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        return value;


    }


    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }
        Node<T> prv = null;
        Node<T> next = null;

        do {
            next = head.next;
            head.next = prv;
            prv = head;
            if(next!=null)
            head=next;
        }
        while (next != null);
    }
    /*
    public void reverse() {

        if(head==null || head.next==null){
            return;
        }
        Node<T> prv=head;
        Node<T> ptr=head.next;
        head.next=null;

        do{
            Node next=ptr.next;
            ptr.next=prv;
            prv=ptr;
            head=ptr;
            ptr=next;
        }
        while (ptr!=null);
    }*/

    private static class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }


}
