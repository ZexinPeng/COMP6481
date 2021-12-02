package assignment3.part2;

import java.util.Random;

public class AVL implements CleverSIDC{
    int size = 0;
    public Node root;

    public AVL() {

    }

    public AVL(LinkedList list) {
        LinkedList.ListNode node = list.getHead();
        while (node != null) {
            addNode(node.key, node.value);
            node = node.next;
            size++;
        }
    }

    @Override
    public int generate() {
        int key = new Random().nextInt((int)Math.pow(10, 8));
        while (contains(key)) {
            key = new Random().nextInt((int)Math.pow(10, 8));
        }
        return key;
    }

    @Override
    public void setSIDCThreshold(int size) {

    }

    @Override
    public int[] allKeys() {
        int[] keys = new int[size];
        preorder(root, keys, 0);
        return keys;
    }

    @Override
    public void add(int key, String value) {
        addNode(key, value);
//        prettyPrint();
    }

    @Override
    public boolean remove(int key) {
        return removeNode(key);
//        prettyPrint();
    }

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
        public String value(){
            return this.value;
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
        public int leftHeight() {
            if(this.left==null)return 0;
            return this.left.height();
        }
        public int rightHeight() {
            if(this.right==null)return 0;
            return this.right.height();
        }
        public boolean isBalanced() {
            int diff=leftHeight()-rightHeight();
            if(diff<2&&diff>-2)return true;
            return false;
        }
    }

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


    private Node findMin(Node node) {
        Node target=null;
        Node temp=node;
        while(temp!=null) {
            target=temp;
            temp=temp.left();
        }
        return target;
    }

    private Node findMax(Node node) {
        Node target=null;
        Node temp=node;
        while(temp!=null) {
            target=temp;
            temp=temp.right();
        }
        return target;
    }

    private Node findGreaterParent(Node node) {
        Node target=null;
        Node temp=node;
        while(temp!=null) {
            target=temp;
            if(temp.key - node.key>0)break;
            temp=temp.parent();
        }
        return target;
    }

    private Node findLessParent(Node node) {
        Node target=null;
        Node temp=node;
        while(temp!=null) {
            target=temp;
            if(temp.key - node.key<0)break;
            temp=temp.parent();
        }
        return target;
    }


    private void updateHeight(Node node) {
        if(node==null)return;
        int maxHeight=node.leftHeight()>node.rightHeight()?node.leftHeight():node.rightHeight();
        node.setHeight(maxHeight+1);
        updateHeight(node.parent());
    }
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

    public Node searchTree(Node node, int key) {
        if (node == null || key == node.key) {
            return node;
        }
        if (key < node.key) {
            return searchTree(node.left, key);
        }
        return searchTree(node.right, key);
    }

    private int preorder(Node node, int[] keys, int index) {
        if (node == null) {
            return index;
        }
        index = preorder(node.left, keys, index);
        keys[index++] = node.key;
        index = preorder(node.right, keys, index);
        return index;
    }

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

    @Override
    public int prevKey(int key) {
        Node prev = prevKeyNode(key);
        return prev == null ? -1 : prev.key;
    }

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



    private boolean contains(int key) {
        return searchTree(root, key) != null;
    }

    public Node getRoot() {
        return root;
    }

    public void print() {
        printHelper(root, "", true);
    }

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
