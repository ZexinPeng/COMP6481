package assignment3.part2;

import java.util.Random;

public class LinkedList implements CleverSIDC{
    private int size;
    // the dummy head
    private ListNode head;

    public LinkedList(AVL avl) {
        head = new ListNode();
        ListNode cur = head;
        int[] keyArr = avl.allKeys();
        size = keyArr.length;
        for (int key: keyArr) {
            cur.next = new ListNode(key, avl.getValues(key), null, cur);
            cur = cur.next;
        }
    }

    public LinkedList() {
        size = 0;
        // dummy head
        head = new ListNode();
    }

    public ListNode getHead() {
        return head.next;
    }

    @Override
    public int generate() {
        int key = new Random().nextInt((int) Math.pow(10, 8));
        while (contains(key)) {
            key = new Random().nextInt((int) Math.pow(10, 8));
        }
        return key;
    }

    private boolean contains(int key) {
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.key == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public void setSIDCThreshold(int size) {

    }

    @Override
    public int[] allKeys() {
        int[] res = new int[size];
        ListNode cur = head.next;
        int index = 0;
        while (cur != null) {
            res[index++] = cur.key;
            cur = cur.next;
        }
        return res;
    }

    @Override
    public void add(int key, String value) {
        size++;
        ListNode cur = head;
        while (cur.next != null) {
            if (key < cur.next.key) {
                ListNode tmp = new ListNode(key, value, cur.next, cur);
                cur.next.prev = tmp;
                cur.next = tmp;
                return;
            }
            cur = cur.next;
        }
        cur.next = new ListNode(key, value, null, cur);
    }

    @Override
    public boolean remove(int key) {
        ListNode cur = head;
        while (cur.next != null) {
            if (key == cur.next.key) {
                size--;
                ListNode next = cur.next;
                cur.next = cur.next.next;
                if (cur.next != null) {
                    cur.next.prev = cur;
                }
                next.next = null;
                next.prev = null;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String getValues(int key) {
        ListNode cur = head.next;
        while (cur != null && cur.key != key) {
            cur = cur.next;
        }
        return cur == null ? null : cur.value;
    }

    @Override
    public int nextKey(int key) {
        ListNode cur = head.next;
        while (cur != null && cur.key != key) {
            cur = cur.next;
        }
        return cur == null || cur.next == null ? -1 : cur.next.key;
    }

    @Override
    public int prevKey(int key) {
        ListNode cur = head.next;
        while (cur != null && cur.key != key) {
            cur = cur.next;
        }
        return cur == null || cur.prev == head ? -1 : cur.prev.key;
    }

    @Override
    public int rangeKey(int key1, int key2) {
        ListNode cur = head.next;
        while (cur != null && cur.key < key1) {
            cur = cur.next;
        }
        int res = 0;
        while (cur != null && cur.key <= key2) {
            cur = cur.next;
            res++;
        }
        return res;
    }

    public class ListNode {
        int key;
        String value;
        ListNode next;
        ListNode prev;

        public ListNode() {
            key = -1;
        }

        public ListNode(int key, String value, ListNode next, ListNode prev) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
