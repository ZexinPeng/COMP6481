package assignment3.part2;

public class CleverSIDCImpl implements CleverSIDC{
    private CleverSIDC cleverSIDC;

    @Override
    public int generate() {
        return cleverSIDC.generate();
    }

    @Override
    public void setSIDCThreshold(int size) {
        if (size <= 1000) {
            cleverSIDC = new SmallSIDC();
        } else {
            cleverSIDC = new LargeSIDC();
        }
    }

    @Override
    public int[] allKeys() {
        return cleverSIDC.allKeys();
    }

    @Override
    public void add(int key, String value) {
        cleverSIDC.add(key, value);
    }

    @Override
    public void remove(int key) {
        cleverSIDC.remove(key);
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
