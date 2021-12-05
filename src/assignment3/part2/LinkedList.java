package assignment3.part2;

import java.util.Random;

/**
 * This class store all records in a linked list
 * @author Zexin Peng 40166520, Sijie Min 40152234
 */
public class LinkedList implements CleverSIDC{
    private int size;
    // the dummy head
    private ListNode head;

    /**
     * Construct the linked list with data from the AVL tree
     * @param avl AVL
     */
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

    /**
     * Default constructor
     */
    public LinkedList() {
        size = 0;
        // dummy head
        head = new ListNode();
    }

    /**
     * Get 1st node
     * @return ListNode, first node
     */
    public ListNode getHead() {
        return head.next;
    }

    /**
     * Generates a key that does not exist in the linked list
     * @return int, new key
     */
    @Override
    public int generate() {
        int key = new Random().nextInt((int) Math.pow(10, 8));
        while (contains(key)) {
            key = new Random().nextInt((int) Math.pow(10, 8));
        }
        return key;
    }

    /**
     * Check whether the key exists
     * @param key int
     * @return boolean, true if key exists else false
     */
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

    /**
     * Get all keys
     * @return int[], all keys
     */
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

    /**
     * Add a pair, the list is guaranteed to be ordered by key
     */
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

    /**
     * Remove node by key
     * @param key int
     * @return boolean, true if success else false
     */
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

    /**
     * Get node by key
     * @param key
     * @return ListNode, the node if key exists else null
     */
    private ListNode getNode(int key) {
        ListNode cur = head.next;
        while (cur != null && cur.key < key) {
            cur = cur.next;
        }
        return cur == null || cur.key == key? cur : null;
    }

    /**
     * Get value by key
     * @param key int
     * @return String, the value corresponding to the key
     */
    @Override
    public String getValues(int key) {
        ListNode cur = head.next;
        while (cur != null && cur.key != key) {
            cur = cur.next;
        }
        return cur == null ? null : cur.value;
    }

    /**
     * Get the next key
     * @param key int
     * @return int, next key
     */
    @Override
    public int nextKey(int key) {
        ListNode node = nextNode(key);
        return node == null ? -1 : node.key;
    }

    /**
     * Get the next node
     * @param key int
     * @return ListNode, next node of the key
     */
    public ListNode nextNode(int key) {
        ListNode cur = head;
        while (cur.next != null && key >= cur.next.key) {
            cur = cur.next;
        }
        return cur.next == null ? null : cur.next;
    }

    /**
     * Get previous key
     * @param key int
     * @return int, previous key
     */
    @Override
    public int prevKey(int key) {
        ListNode node = prevNode(key);
        return node == null ? -1 : node.key;
    }

    /**
     * Get previous node
     * @param key int
     * @return ListNode, previous node of the key
     */
    public ListNode prevNode(int key) {
        ListNode cur = head;
        while (cur.next != null && key > cur.next.key) {
            cur = cur.next;
        }
        return cur.key == -1 ? null : cur;
    }

    /**
     * Get the number of keys between key1 and key2
     * @param key1 int, begin
     * @param key2 int, end
     * @return int, the number of keys between key1 and key2
     */
    @Override
    public int rangeKey(int key1, int key2) {
        if (key1 > key2) {
            int tmp = key2;
            key1 = key2;
            key2 = tmp;
        }
        int res = 0;
        ListNode node1, node2;
        node1 = getNode(key1);
        node2 = getNode(key2);

        if (node1 == null) {
            node1 = nextNode(key1);
            // ceiling is too large
            if (node1 == null) {
                return res;
            }
        }
        if (node2 == null) {
            node2 = prevNode(key2);
            // floor is too small
            if (node2 == null) {
                return res;
            }
        }
        // key1 and key2 within two nodes
        if (node1.key > node2.key) {
            return res;
        }
        while (node1 != node2) {
            res++;
            node1 = node1.next;
        }
        return res + 1;
    }

    /**
     *	Node class of LinkedList
     */
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
