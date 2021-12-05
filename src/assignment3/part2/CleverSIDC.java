package assignment3.part2;

/**
 * This class is the interface of CleverSIDC
 * @author Zexin Peng 40166520, Sijie Min 40152234
 */
public interface CleverSIDC {
	/**
	 * randomly generates new non-existing key of 8 digits;
	 * @return key
	 */
	int generate();

	/**
	 * set the threshold
	 * @param size
	 */
	void setSIDCThreshold (int size);

	/**
	 * return all keys in CleverSIDC as a sorted sequence;
	 * @return all keys
	 */
	int[] allKeys();

	/**
	 * add an entry for the given key and value;
	 * @param key key
	 * @param value value
	 */
	void add(int key, String value);

	/**
	 * remove the entry for the given key;
	 * @param key key
	 * @return result
	 */
	boolean remove(int key);

	/**
	 * return the values of the given key;
	 * @param key key
	 * @return value
	 */
	String getValues(int key);

	/**
	 * return the key for the successor of key;
	 * @param key key
	 * @return next key
	 */
	int nextKey(int key);

	/**
	 * return the key for the predecessor of key;
	 * @param key key
	 * @return prev key
	 */
	int prevKey(int key);

	/**
	 * returns the number of keys that are within the specified range of the two keys key1 and key2.
	 * @param key1 key1
	 * @param key2 key2
	 * @return the number of keys
	 */
	int rangeKey(int key1,int key2);
}
