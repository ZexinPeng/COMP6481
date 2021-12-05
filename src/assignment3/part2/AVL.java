package assignment3.part2;

import java.util.Random;

/**
 * This class store all records in an AVL tree
 * @author Zexin Peng 40166520, Sijie Min 40152234
 */
public class AVL implements CleverSIDC{
    int size = 0;
    public Node root;

    /**
     * constructor
     */
    public AVL() {

    }

    /**
     * construc from the linked list
     * @param list the list
     */
    public AVL(LinkedList list) {
        LinkedList.ListNode node = list.getHead();
        while (node != null) {
            addNode(node.key, node.value);
            node = node.next;
        }
    }

    /**
     * This method will generate the key that does not exist
     * @return the key
     */
    @Override
    public int generate() {
        int key = new Random().nextInt((int)Math.pow(10, 8));
        while (contains(key)) {
            key = new Random().nextInt((int)Math.pow(10, 8));
        }
        return key;
    }

    /**
     * This method will set the threshold
     * @param size threshold
     */
    @Override
    public void setSIDCThreshold(int size) {

    }

    /**
     * This method will return all keys in ascending order
     * @return all keys
     */
    @Override
    public int[] allKeys() {
        int[] keys = new int[size];
        preorder(root, keys, 0);
        return keys;
    }

    /**
     * This method will insert the record into the tree
     * @param key the key
     * @param value the value
     */
    @Override
    public void add(int key, String value) {
        addNode(key, value);
    }

    /**
     * This method will remove the node whose key equals to given key in the tree
     * @param key the removed key
     * @return true if successfully removed
     */
    @Override
    public boolean remove(int key) {
        return removeNode(key);
    }

    /**
     * This method will return the value of the given key.
     * @param key the given key
     * @return the value of the given key
     */
    @Override
    public String getValues(int key) {
        Node node = searchTree(root, key);
        return node == null ? null : node.value;
    }

    /**
     * if the key does not exist, return -1
     * @param key the key
     * @return the next Key
     */
    @Override
    public int nextKey(int key) {
        Node next = nextKeyNode(key);
        return next == null ? -1 : next.key;
    }

    /**
     * This inner class represents the node in the tree.
     */
    public class Node{
        private int key;
        private String value;
        private Node left=null;
        private Node right=null;
        private Node parent=null;
        private int height=1;
        public Node(int key,String value) {
            this.key=key;
            this.value=value;
        }
        public void setKey(int key) {
            this.key=key;
        }
        public int getKey() {
            return this.key;
        }
        public void setValue(String value){
            this.value=value;
        }
        public void setLeft(Node left){
            this.left=left;
            if(left!=null) {
                left.setParent(this);
            }
        }
        public Node left(){
            return this.left;
        }
        public void setRight(Node right){
            this.right=right;
            if(right!=null) {
                right.setParent(this);
            }
        }
        public Node right(){
            return this.right;
        }
        public void setParent(Node parent) {
            this.parent=parent;
        }
        public Node parent() {
            return this.parent;
        }
        public void setHeight(int height) {
            this.height=height;
        }
        public int height() {
            return this.height;
        }

        /**
         * This method will return the height of left subtree.
         * @return The height of left subtree
         */
        public int leftHeight() {
            if(this.left==null)return 0;
            return this.left.height();
        }
        /**
         * This method will return the height of rightt subtree.
         * @return The height of right subtree
         */
        public int rightHeight() {
            if(this.right==null)return 0;
            return this.right.height();
        }

        /**
         * This method will judge the tree whether is balanced
         * @return true if the tree is balanced
         */
        public boolean isBalanced() {
            int diff=leftHeight()-rightHeight();
            if(diff<2&&diff>-2)return true;
            return false;
        }
    }

    /**
     * This method will add node into tree
     * @param key the key
     * @param value the value
     */
    public void addNode(int key,String value) {
        if(this.root==null) {
            this.root=new Node(key,value);
            this.size++;
            return;
        }
        Node temp=this.root;
        while(true) {
            int ret=key - temp.key;
            if(ret<0) {
                if(temp.left()==null) {
                    temp.setLeft(new Node(key,value));
                    if(temp.right()==null) {
                        updateHeight(temp);
                    }
                    this.size++;
                    break;
                }else {
                    temp=temp.left();
                }
            }else if(ret>0) {
                if(temp.right()==null) {
                    temp.setRight(new Node(key,value));
                    if(temp.left()==null) {
                        updateHeight(temp);
                    }
                    this.size++;
                    break;
                }else {
                    temp=temp.right();
                }
            }else {
                temp.setValue(value);
                break;
            }
        }
        keepBalance(temp.parent());
    }

    /**
     * This method will remove the element with the given key
     * @param key the given key
     * @return true if the element is removed
     */
    public boolean removeNode(int key) {
        Node node=searchTree(root, key);
        if(node==null)return false;
        this.size--;
        Node parent=node.parent();
        if(node.left()==null&&node.right()==null) {
            if(parent==null) {
                this.root=null;
            }else {
                if(parent.left()==node) {
                    parent.setLeft(null);
                }else{
                    parent.setRight(null);
                }
            }
        }else if(node.left()!=null&&node.right()!=null) {
            Node rightMin=findMin(node.right());

            Node rightMinParent=rightMin.parent();
            if(rightMinParent.left()==rightMin) {
                rightMinParent.setLeft(null);
            }else{
                rightMinParent.setRight(rightMin.right());
            }

            if(parent==null) {
                this.root=rightMin;
                rightMin.setParent(null);
            }else {
                if(parent.left()==node) {
                    parent.setLeft(rightMin);
                }else{
                    parent.setRight(rightMin);
                }
            }

            rightMin.setLeft(node.left);
            rightMin.setRight(node.right);

            if(node==rightMinParent) {
                parent=rightMin;
            }else {
                parent=rightMinParent;
            }
        }else {
            Node child=node.left();
            if(child==null)child=node.right();
            if(parent==null) {
                this.root=child;
            }else {
                if(parent.left()==node) {
                    parent.setLeft(child);
                }else{
                    parent.setRight(child);
                }
            }
        }
        updateHeight(parent);
        keepBalance(parent);
        return true;
    }

    /**
     * This method will return the minimum value in the tree
     * @param node the node
     * @return the minimum value
     */
    private Node findMin(Node node) {
        Node target=null;
        Node temp=node;
        while(temp!=null) {
            target=temp;
            temp=temp.left();
        }
        return target;
    }

    /**
     * This method will update the height of the given node
     * @param node the given node
     */
    private void updateHeight(Node node) {
        if(node==null)return;
        int maxHeight=node.leftHeight()>node.rightHeight()?node.leftHeight():node.rightHeight();
        node.setHeight(maxHeight+1);
        updateHeight(node.parent());
    }

    /**
     * This method will perform the right rotation
     * @param node the node
     * @return root after rotation
     */
    private Node rotateRight(Node node) {
        Node parent=node.parent();
        Node left=node.left();
        Node leftRight=left.right();
        node.setLeft(leftRight);
        left.setRight(node);
        left.setParent(parent);
        if(parent!=null) {
            if(parent.left()==node) {
                parent.setLeft(left);
            }else {
                parent.setRight(left);
            }
        }
        updateHeight(node);
        return left;
    }

    /**
     * This method will perform the left rotation
     * @param node the node
     * @return the root after rotation
     */
    private Node rotateLeft(Node node) {
        Node parent=node.parent();
        Node right=node.right();
        Node rightLeft=right.left();
        node.setRight(rightLeft);
        right.setLeft(node);
        right.setParent(parent);
        if(parent!=null) {
            if(parent.left()==node) {
                parent.setLeft(right);
            }else {
                parent.setRight(right);
            }
        }
        updateHeight(node);
        return right;
    }

    /**
     * This method will keep balance in the tree
     * @param node the node
     */
    private void keepBalance(Node node) {
        if(node==null)return;
        if(node.isBalanced()) {
            keepBalance(node.parent());
        } else {
            boolean flag=false;
            if(node==this.root)flag=true;
            Node ret=null;
            if(node.leftHeight()>node.rightHeight()) {
                if(node.left().leftHeight()>node.left().rightHeight()) {
                    ret=rotateRight(node);
                }else {
                    rotateLeft(node.left());
                    ret=rotateRight(node);
                }
            }else {
                if(node.right().rightHeight()>node.right().leftHeight()) {
                    ret=rotateLeft(node);
                }else {
                    rotateRight(node.right());
                    ret=rotateLeft(node);
                }
            }
            if(flag)this.root=ret;
        }
    }

    /**
     * This method will search the node with given key in the tree
     * @param node the node
     * @param key the key
     * @return null if does not exist
     */
    public Node searchTree(Node node, int key) {
        if (node == null || key == node.key) {
            return node;
        }
        if (key < node.key) {
            return searchTree(node.left, key);
        }
        return searchTree(node.right, key);
    }

    /**
     * This method will traverse the tree in preorder
     * @param node node
     * @param keys result array
     * @param index current index in the array
     * @return the result array
     */
    private int preorder(Node node, int[] keys, int index) {
        if (node == null) {
            return index;
        }
        index = preorder(node.left, keys, index);
        keys[index++] = node.key;
        index = preorder(node.right, keys, index);
        return index;
    }

    /**
     * This method will find the next key node in the tree
     * @param key key
     * @return next key node
     */
    private Node nextKeyNode(int key) {
        if (key < 0 ) {
            return null;
        }
        Node node = root;
        while (node != null) {
            if (node.key <= key) {
                Node next = nextNode(node);
                if (next == null ) {
                    return null;
                }
                if (next.key > key) {
                    return next;
                } else {
                    node = next;
                }
            } else {
                Node prev = prevNode(node);
                if (prev == null) {
                    return node;
                }
                if (prev.key <= key) {
                    return node;
                } else {
                    node = prev;
                }
            }
        }
        return null;
    }

    /**
     * This method will find the next key node in the tree
     * @param node node
     * @return next key node
     */
    public Node nextNode(Node node) {
        int key = node.key;
        if (node.right != null) {
            Node right = node.right;
            while (right.left != null) {
                right = right.left;
            }
            return right;
        } else {
            node = node.parent;
            while (node != null && node.key < key) {
                node = node.parent;
            }
            return node;
        }
    }

    /**
     * This method will find the prev key node in the tree
     * @param key key
     * @return prev key node
     */
    @Override
    public int prevKey(int key) {
        Node prev = prevKeyNode(key);
        return prev == null ? -1 : prev.key;
    }

    /**
     * This method will find the prev key node in the tree
     * @param key key
     * @return prev key node
     */
    private Node prevKeyNode(int key) {
        if (key >=  (int)Math.pow(10, 8)) {
            return null;
        }
        Node node = root;
        while (node != null) {
            if (node.key < key) {
                Node next = nextNode(node);
                if (next == null ) {
                    return node;
                }
                if (next.key > key) {
                    return node;
                } else {
                    node = next;
                }
            } else {
                Node prev = prevNode(node);
                if (prev == null) {
                    return null;
                }
                if (prev.key < key) {
                    return prev;
                } else {
                    node = prev;
                }
            }
        }
        return null;
    }

    /**
     * This method will find the prev key node in the tree
     * @param node node
     * @return prev key node
     */
    public Node prevNode(Node node) {
        int key = node.key;
        if (node.left != null) {
            Node left = node.left;
            while (left.right != null) {
                left = left.right;
            }
            return left;
        } else {
            node = node.parent;
            while (node != null && node.key >= key) {
                node = node.parent;
            }
            return node;
        }
    }

    /**
     * This method will find the number of nodes between two keys
     * @param key1 key1
     * @param key2 key2
     * @return the number of nodes
     */
    @Override
    public int rangeKey(int key1, int key2) {
        if (key1 > key2) {
            int tmp = key2;
            key1 = key2;
            key2 = tmp;
        }
        int res = 0;
        Node node1, node2;
        node1 = searchTree(root, key1);
        node2 = searchTree(root, key2);

        if (node1 == null) {
            node1 = nextKeyNode(key1);
            // ceiling is too large
            if (node1 == null) {
                return res;
            }
        }
        if (node2 == null) {
            node2 = prevKeyNode(key2);
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
            node1 = nextNode(node1);
        }
        return res + 1;
    }

    /**
     * This method will return whether the tree contains the node with the given key
     * @param key key
     * @return true if the node exists in the tree
     */
    private boolean contains(int key) {
        return searchTree(root, key) != null;
    }

    /**
     * This method will return the root of the tree
     * @return the root node
     */
    public Node getRoot() {
        return root;
    }

    /**
     * This method will print the tree
     */
    public void print() {
        printHelper(root, "", true);
    }

    /**
     * This method will print the tree
     * @param currPtr current node
     * @param indent result String
     * @param last true if current node is the right child
     */
    private void printHelper(Node currPtr, String indent, boolean last) {
        // print the tree structure on the screen
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            System.out.println(currPtr.key);

            printHelper(currPtr.left, indent, false);
            printHelper(currPtr.right, indent, true);
        }
    }
}
