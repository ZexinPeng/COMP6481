package assignment3.part2;

public class CleverSIDCImpl implements CleverSIDC{
    CleverSIDC cleverSIDC = new LinkedList();
    private int threshold = 1000;
    int size = 0;

    @Override
    public int generate() {
        return cleverSIDC.generate();
    }

    @Override
    public void setSIDCThreshold(int size) {
        threshold = size;
    }

    @Override
    public int[] allKeys() {
        return cleverSIDC.allKeys();
    }

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

    @Override
    public String getValues(int key) {
        return cleverSIDC.getValues(key);
    }

    @Override
    public int nextKey(int key) {
        return cleverSIDC.nextKey(key);
    }

    @Override
    public int prevKey(int key) {
        return cleverSIDC.prevKey(key);
    }

    @Override
    public int rangeKey(int key1, int key2) {
        return cleverSIDC.rangeKey(key1, key2);
    }
}
