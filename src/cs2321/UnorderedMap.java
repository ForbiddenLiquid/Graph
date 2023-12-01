package cs2321;


import net.datastructures.Entry;
import net.datastructures.Map;

public class UnorderedMap<K,V> extends AbstractMap<K,V> {

	/* Use ArrayList or DoublyLinked list for the Underlying storage for the map of entries.
	 * TODO:  Uncomment one of these two lines;
	 * private DoublyLinkedList<mapEntry<K,V>> table;
	 */

	private ArrayList<mapEntry<K,V>> table;

	public UnorderedMap() {
		table = new ArrayList<>();
	}

	// returns index of an entry with equal key, or -1 if none found
	private int findIndex(K key) {
		int n = table.size();
		for (int j = 0; j < n; j++) {
			if (table.get(j).getKey().equals(key)) {
				return j;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V get(K key) {
		/* use equals method to compare keys, do NOT use == */
		int j = findIndex(key);
		if (j == -1)
			return null;
		return table.get(j).getValue();
	}

	@Override
	public V put(K key, V value) {
		/* use equals method to compare keys, do NOT use == */
		int j = findIndex(key);
		if (j == -1) {
			mapEntry<K,V> temp = new mapEntry(key, value);
			table.addLast(temp);		// add new entry
			return null;
		}	else {
			V returnValue = table.get(j).getValue();
			table.get(j).setValue(value);
			return returnValue;
		}
	}

	@Override
	public V remove(K key) {
		/* use equals method to compare keys, do NOT use == */
		int j = findIndex(key);
		int n = size();
		if (j == -1)
			return null;						// not found
		V answer = table.get(j).getValue();
		if (j != n - 1)
			table.set(j, table.get(n-1));		// relocate last entry to 'hole' created by removal
		table.remove(n-1);						// remove last entry of the table
		return answer;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> temp = new ArrayList<>();
		for (int i = 0; i < table.size(); i++) {
			if (table.get(i) != null) {
				mapEntry<K, V> temp1 = table.get(i);
				temp.add(i, temp1);
			}
		}
		return temp;				// returns an iterable collection of all key-value entries of the map
	}

}
