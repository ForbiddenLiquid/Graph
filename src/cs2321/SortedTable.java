package cs2321;

import cs2321.AbstractMap.mapEntry;
import net.datastructures.*;

public class SortedTable<K extends Comparable<K>, V> extends AbstractMap<K,V>  {

	/* 
	 * Use Sorted ArrayList for the Underlying storage for the map of entries.
	 */

	private ArrayList<mapEntry<K,V>> table;

	private int findIndex(K key, int low, int high) {
		if (high < low)				// no entry qualifies
			return high += 1;
		int mid = (low + high) / 2;
		int comp = key.compareTo(table.get(mid).getKey());
		if (comp == 0)
			return mid;								// found exact match
		else if (comp < 0)
			return findIndex(key, low, mid - 1);	// answer is left of mid
		else
			return findIndex(key, mid + 1, high);	// answer is right of mid
	}

	private int findIndex(K key) {
		return findIndex(key, 0, table.size() - 1);
	}

	public SortedTable(){
		table = new ArrayList<>();
	}

	@Override
	public V get(K key) {
		int j = findIndex(key);
		if (j == size() || key.compareTo(table.get(j).getKey()) != 0)
			return null;			// no match
		return table.get(j).getValue();
	}

	@Override
	public V put(K key, V value) {
		int j = findIndex(key);
		if (j < size() && key.compareTo(table.get(j).getKey()) == 0)	{		// match exists
			V tempV = table.get(j).getValue();
			table.get(j).setValue(value);
			return tempV;
		}
		table.add(j, new mapEntry<K,V>(key, value));				// otherwise new
		return null;
	}

	@Override
	public V remove(K key) {
		int j = findIndex(key);
		if (j == size() || key.compareTo(table.get(j).getKey()) != 0)
			return null;				// No match
		return table.remove(j).getValue();
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

	@Override
	public int size() {
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}
