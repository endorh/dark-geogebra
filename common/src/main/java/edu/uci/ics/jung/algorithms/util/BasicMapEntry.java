package edu.uci.ics.jung.algorithms.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * An simple minimal implementation of <code>Map.Entry</code>.
 *
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 */
public class BasicMapEntry<K, V> implements Map.Entry<K, V> {
	final K key;
	V value;

	/**
	 * Create new entry.
	 */
	public BasicMapEntry(K k, V v) {
		value = v;
		key = k;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V newValue) {
		V oldValue = value;
		value = newValue;
		return oldValue;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Map.Entry e)) {
			return false;
		}
		Object k1 = getKey();
		Object k2 = e.getKey();
		if (Objects.equals(k1, k2)) {
			Object v1 = getValue();
			Object v2 = e.getValue();
			if (Objects.equals(v1, v2)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (key == null ? 0 : key.hashCode())
				^ (value == null ? 0 : value.hashCode());
	}

	@Override
	public String toString() {
		return getKey() + "=" + getValue();
	}

	/**
	 * This method is invoked whenever the value in an entry is overwritten by
	 * an invocation of put(k,v) for a key k that's already in the HashMap.
	 */
	void recordAccess(HashMap<K, V> m) {
	}

	/**
	 * This method is invoked whenever the entry is removed from the table.
	 */
	void recordRemoval(HashMap<K, V> m) {
	}
}
