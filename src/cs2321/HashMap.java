package cs2321;

import net.datastructures.*;

public class HashMap<K, V> extends AbstractMap<K,V> {

	/* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	 * 
	 */
	private UnorderedMap<K,V>[]  table;
	int 	size;  // number of mappings(entries) 
	int 	capacity; // The size of the hash table. 
	int     DefaultCapacity = 17; //The default hash table size


	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor= 0.75;

	/**
	 * Constructor that takes a hash size
	 * @param hashtable size: the number of buckets to initialize 
	 */
	public HashMap(int hashtablesize) {
		table = new UnorderedMap[hashtablesize];
		capacity = hashtablesize;
		for (int i = 0; i < table.length; i++)
			table[i] = new UnorderedMap<K,V>();
	}

	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
		table = new UnorderedMap[DefaultCapacity];
		capacity = DefaultCapacity;
		for (int i = 0; i < table.length; i++)
			table[i] = new UnorderedMap<K,V>();
	}

	/* This method should be called by map an integer to the index range of the hash table 
	 */
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

	/*
	 * The purpose of this method is for testing if the table was doubled when rehashing is needed. 
	 * Return the the size of the hash table. 
	 * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
	 */
	public int tableSize() {
		if (table.length > DefaultCapacity * loadfactor) {
			DefaultCapacity = DefaultCapacity * 2;
		}
		return table.length;
	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V get(K key) {
		return table[hashValue(key)].get(key);
	}

	@Override
	public V put(K key, V value) {
		V temp = table[hashValue(key)].put(key, value);		// assigns table value to a temporary variable
		if (temp == null)
			size++;
		if (size > capacity * loadfactor) {		// checks load strain on the hashmap
			capacity = capacity * 2;					// doubles capacity of hashmap
			UnorderedMap<K,V>[] temp2 = table;
			table = new UnorderedMap[capacity];			// creates new hashmap with increased capacity
			for (int i = 0; i < table.length; i++) {
				table[i] = new UnorderedMap<K,V>();		// creates hashmap for each index in new hasmap
			}
			for (int j = 0; j < temp2.length; j++) {
				for (Entry<K,V> tempEntry : temp2[j].entrySet()) {	// adds data from old hashmap into new hashmap
					K tempK = tempEntry.getKey();
					V tempV = tempEntry.getValue();
					table[hashValue(tempK)].put(tempK, tempV);
				}
			}
		}
		return temp;				// returns value added into hashmap
	}

	@Override
	public V remove(K key) {
		size--;
		return table[hashValue(key)].remove(key);
	}

	// Returns an iterable collection of all key-value entries of the map
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for (int i = 0; i < capacity; i++)
			if (table[i] != null)
				for (Entry<K,V> entry : table[i].entrySet())
					buffer.addLast(entry);
		return buffer;
	}


}
