/**
 * 
 */
package com.main.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.main.model.Item;
import com.main.services.ReportCalculatorService;

public class InventoryStub {
	private static Map<String, List<Item>> inventoryMap = new HashMap<>();
	private static Map<String, Long> sellingListCount=new HashMap<>();
	private static Map<String, Long> buyingListCount=new HashMap<>();
	
	ReportCalculatorService rcs = ReportCalculatorService.getInstance();
	
	private static InventoryStub is;
	
	public static InventoryStub getInstance(){
		if(is == null)
			return new InventoryStub();
		else
			return is;
	}

	public Item create(String itemName, double costPrice, double sellingPrice) {
		Item newitem = new Item();
		newitem.setItemName(itemName);
		newitem.setCostPrice(costPrice);
		newitem.setSellingPrice(sellingPrice);
		
		if(null != inventoryMap.get(itemName)) {
			List<Item> existItems = inventoryMap.get(itemName);
			existItems.add(newitem);
		}else {
			List<Item> items = new ArrayList<>();
			items.add(newitem);
			inventoryMap.put(itemName, items);
		}
		setBuyingListCount(itemName, getBuyingListCount(itemName) + 1);
		
		return newitem;
	}

	public String updateBuyingQuantity(String itemName, long quantity) {
		
		if(null != inventoryMap.get(itemName)) {
			List<Item> existItems = inventoryMap.get(itemName);
			List<Item> updatedItems = generateItems(existItems.get(0), quantity);
			existItems.addAll(updatedItems);
		}else {
			return "Please cretae an item before update.";
		}
		setBuyingListCount(itemName, getBuyingListCount(itemName) + quantity);
		
		return itemName+" updated and we have buying count of "+getBuyingListCount(itemName);
	}
	
	public String updateSellingQuantity(String itemName, long quantity) {
		
		if(null != inventoryMap.get(itemName)) {
			List<Item> existItems = inventoryMap.get(itemName);
			for(long i=0; i<quantity; i++) {
				existItems.remove(0);
			}
		}else {
			return "No items to update selling count.";
		}
		setSellingListCount(itemName, getSellingListCount(itemName) + quantity);
		
		return itemName+" updated and we have selling count of "+getBuyingListCount(itemName);
	}

	public String delete(String itemName) {

		if(null != inventoryMap.get(itemName)) {
			List<Item> existItems = inventoryMap.get(itemName);
			existItems.remove(0);
			setBuyingListCount(itemName, getBuyingListCount(itemName) - 1);
		}
		return itemName;
	}
	
	public Map<String, List<String>> report() {
		return rcs.calculateReport(inventoryMap, buyingListCount, sellingListCount);
	}
	
	private List<Item> generateItems(Item item, long quantity) {
		List<Item> createdItems = new ArrayList<>();
		for(long i=0; i<quantity; i++) {
			Item newItem = new Item();
			newItem.setItemName(item.getItemName());
			newItem.setSellingPrice(item.getSellingPrice());
			newItem.setCostPrice(item.getCostPrice());
			
			createdItems.add(newItem);
		}
		return createdItems;
	}

	/**
	 * @return the sellingListCount
	 */
	public long getSellingListCount(String itemName) {
		return sellingListCount.get(itemName);
	}

	/**
	 * @param l the sellingListCount to set
	 */
	public void setSellingListCount(String itemName, long l) {
		InventoryStub.sellingListCount.put(itemName, l);
	}

	/**
	 * @return the buyingListCount
	 */
	public long getBuyingListCount(String itemName) {
		if (buyingListCount.size() > 0 && buyingListCount.containsKey(itemName)) 
			return buyingListCount.get(itemName);
		else 
			return 0;
	}

	/**
	 * @param l the buyingListCount to set
	 */
	public void setBuyingListCount(String itemName, long l) {
		InventoryStub.buyingListCount.put(itemName, l);
	}
	
	public static Map<String, List<Item>> getInventoryMap() {
		return inventoryMap;
	}
}

