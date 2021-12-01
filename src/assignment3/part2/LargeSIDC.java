package assignment3.part2;

import java.util.Random;

public class LargeSIDC implements CleverSIDC{
    int size = 0;
    public Node root;

    public LargeSIDC() {
//        hash = new int[size];
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
        insert(key, value);
//        prettyPrint();
    }

    @Override
    public void remove(int key) {
        deleteNode(key);
//        prettyPrint();
    }

    @Override
    public String getValues(int key) {
        Node node = searchTree(key);
        return node == null ? null : node.value;
    }

    /**
     * if the key does not exist, return -1
     * @param key
     * @return the next Key
     */
    @Override
    public int nextKey(int key) {
        Node next = nextKeyNode(key);
        return next == null ? -1 : next.key;
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
            return node == null ? null: node;
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
            return node == null ? null: node;
        }
    }

    @Override
    public int rangeKey(int key1, int key2) {
        // find the position of key1
        Node node = root;
        Node node1, node2;
        while (node != null) {
            if (node.key > key1) {
                if (node.left != null && node.left.key < key1)
                node = node.right;
            } else if (node.key > key1) {
                node = node.left;
            } else {
                break;
            }
        }
        if (node == null) {
            return -1;
        }
        Node last;
        while (node != null && node.key <= key2) {
            last = node;
            if (node.key < key1) {
                node = node.right;
            } else if (node.key > key1) {
                node = node.left;
            } else {
                break;
            }
        }
        if (node == null) {
            return -1;
        }
        node1 = node;
        System.out.println(node1.key);

        return 0;
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

    private boolean contains(int key) {
        return searchTree(key) != null;
    }

    private Node searchTreeHelper(Node node, int key) {
        if (node == null || key == node.key) {
            return node;
        }
        if (key < node.key) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    private Node deleteNodeHelper(Node node, int key) {
        // search the key
        if (node == null) return node;
        else if (key < node.key) node.left = deleteNodeHelper(node.left, key);
        else if (key > node.key) node.right = deleteNodeHelper(node.right, key);
        else {
            // the key has been found, now delete it

            // case 1: node is a leaf node
            if (node.left == null && node.right == null) {
                node = null;
            }

            // case 2: node has only one child
            else if (node.left == null) {
                Node temp = node;
                node = node.right;
            }

            else if (node.right == null) {
                Node temp = node;
                node = node.left;
            }

            // case 3: has both children
            else {
                Node temp = minimum(node.right);
                node.key = temp.key;
                node.right = deleteNodeHelper(node.right, temp.key);
            }

        }

        // Write the update balance logic here
        // YOUR CODE HERE
        size--;
        return node;
    }

    // update the balance factor the node
    private void updateBalance(Node node) {
        if (node.bf < -1 || node.bf > 1) {
            rebalance(node);
            return;
        }

        if (node.parent != null) {
            if (node == node.parent.left) {
                node.parent.bf -= 1;
            }

            if (node == node.parent.right) {
                node.parent.bf += 1;
            }

            if (node.parent.bf != 0) {
                updateBalance(node.parent);
            }
        }
    }

    // rebalance the tree
    void rebalance(Node node) {
        if (node.bf > 0) {
            if (node.right.bf < 0) {
                rightRotate(node.right);
                leftRotate(node);
            } else {
                leftRotate(node);
            }
        } else if (node.bf < 0) {
            if (node.left.bf > 0) {
                leftRotate(node.left);
                rightRotate(node);
            } else {
                rightRotate(node);
            }
        }
    }

    // search the tree for the key k
    // and return the corresponding node
    public Node searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    // find the node with the minimum key
    public Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // find the node with the maximum key
    public Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // rotate left at node x
    void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;

        // update the balance factor
        x.bf = x.bf - 1 - Math.max(0, y.bf);
        y.bf = y.bf - 1 + Math.min(0, x.bf);
    }

    // rotate right at node x
    void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;

        // update the balance factor
        x.bf = x.bf + 1 - Math.min(0, y.bf);
        y.bf = y.bf + 1 + Math.max(0, x.bf);
    }


    // insert the key to the tree in its appropriate position
    public void insert(int key, String value) {
        // PART 1: Ordinary BST insert
        Node node = new Node(key, value);
        Node y = null;
        Node x = this.root;

        while (x != null) {
            y = x;
            if (node.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        // y is parent of x
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.key < y.key) {
            y.left = node;
        } else {
            y.right = node;
        }

        // PART 2: re-balance the node if necessary
        updateBalance(node);
        size++;
    }

    // delete the node from the tree
    Node deleteNode(int key) {
        return deleteNodeHelper(this.root, key);
    }

    // print the tree structure on the screen
    public void prettyPrint() {
        printHelper(this.root, "", true);
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

            System.out.println(currPtr.key + "(BF = " + currPtr.bf + ")");

            printHelper(currPtr.left, indent, false);
            printHelper(currPtr.right, indent, true);
        }
    }


    class Node {
        int key; // holds the key
        String value;
        Node parent; // pointer to the parent
        Node left; // pointer to left child
        Node right; // pointer to right child
        int bf; // balance factor of the node

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
            this.bf = 0;
        }
    }
}
