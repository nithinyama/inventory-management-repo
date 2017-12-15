package com.main.model;

import java.util.Comparator;

public class ItemNameComparator implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		Item item1 = (Item) o1;
		Item item2 = (Item) o2;
		
		return (item1.getItemName().compareTo(item2.getItemName()));
	}
}
