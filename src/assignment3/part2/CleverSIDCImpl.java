package assignment3.part2;

/**
 * This class is implementation of CleverSIDC
 * @author Zexin Peng 40166520, Sijie Min 40152234
 */
public class CleverSIDCImpl implements CleverSIDC{
    CleverSIDC cleverSIDC = new LinkedList();
    private int threshold = 1000;
    int size = 0;

    /**
     * Generates a key that does not exist in the cleverSIDC
     * @return int, new key
     */
    @Override
    public int generate() {
        return cleverSIDC.generate();
    }

    /**
     * Set threshold of the cleverSIDC
     */
    @Override
    public void setSIDCThreshold(int size) {
        threshold = size;
    }

    /**
     * Get all keys
     * @return int[], all keys
     */
    @Override
    public int[] allKeys() {
        return cleverSIDC.allKeys();
    }

    /**
     * Add a pair
     */
    @Override
    public void add(int key, String value) {
        cleverSIDC.add(key, value);
        size++;
        if (size > threshold && ! (cleverSIDC instanceof AVL)) {
            System.out.println("===============");
            System.out.println("turn from LinkedList to AVL");
            System.out.println("===============");
            cleverSIDC = new AVL((LinkedList) cleverSIDC);
        }
    }

    /**
     * Remove pair by key
     * @param key int
     * @return boolean, true if success else false
     */
    @Override
    public boolean remove(int key) {
        boolean res = cleverSIDC.remove(key);
        if (res) {
            size--;
            if (size <= threshold && ! (cleverSIDC instanceof LinkedList)) {
                System.out.println("===============");
                System.out.println("turn from AVL to LinkedList");
                System.out.println("===============");
                cleverSIDC = new LinkedList((AVL) cleverSIDC);
            }
        }
        return res;
    }

    /**
     * Get value by key
     * @param key int
     * @return String, the value corresponding to the key
     */
    @Override
    public String getValues(int key) {
        return cleverSIDC.getValues(key);
    }

    /**
     * Get the next key
     * @param key int
     * @return int, next key
     */
    @Override
    public int nextKey(int key) {
        return cleverSIDC.nextKey(key);
    }

    /**
     * Get previous key
     * @param key int
     * @return int, previous key
     */
    @Override
    public int prevKey(int key) {
        return cleverSIDC.prevKey(key);
    }

    /**
     * Get the number of keys between key1 and key2
     * @param key1 int, begin
     * @param key2 int, end
     * @return int, the number of keys between key1 and key2
     */
    @Override
    public int rangeKey(int key1, int key2) {
        return cleverSIDC.rangeKey(key1, key2);
    }
}
