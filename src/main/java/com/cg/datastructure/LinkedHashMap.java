package com.cg.datastructure;

import java.util.ArrayList;

public class LinkedHashMap<K, V> {
	private final int numBuckets;
	ArrayList<LinkedList<K>> bucketArray;

	public LinkedHashMap() {
		this.numBuckets = 10;
		this.bucketArray = new ArrayList<>(numBuckets);
		for (int i = 0; i < numBuckets; i++)
			this.bucketArray.add(null);
	}

	private int getBucketIndex(K key) {
		int hashCode = Math.abs(key.hashCode());
		int index = hashCode % numBuckets;
		System.out.println("Key: " + key + " hashcode: " + hashCode + " index: " + index);
		return index;
	}

	public V get(K key) {
		int index = this.getBucketIndex(key);
		LinkedList<K> linkedList = this.bucketArray.get(index);
		if (linkedList == null)
			return null;
		MapNode<K, V> mapNode = (MapNode<K, V>) linkedList.search(key);
		return (mapNode == null) ? null : mapNode.getValue();
	}

	public void add(K key, V value) {

		int index = this.getBucketIndex(key);
		LinkedList<K> linkedList = this.bucketArray.get(index);
		if (linkedList == null) {
			linkedList = new LinkedList<>();
			this.bucketArray.set(index, linkedList);
		}
		MapNode<K, V> mapNode = (MapNode<K, V>) linkedList.search(key);
		if (mapNode == null) {
			mapNode = new MapNode<>(key, value);
			linkedList.append(mapNode);
		} else
			mapNode.setValue(value);
	}
	
	public void remove(K key) {
		int index = this.getBucketIndex(key);
		LinkedList<K> linkedList = this.bucketArray.get(index);
		INode<K> tempNode = linkedList.search(key);
		linkedList.deleteNodeWithKey(tempNode.getKey());
	}

	@Override
	public String toString() {
		return "LinkedHashMap [numBuckets=" + numBuckets + ", bucketArray=" + bucketArray + "]";
	}

}
