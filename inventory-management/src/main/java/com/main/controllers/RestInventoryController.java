/**
 * 
 *//*
package com.main.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Item;

@RestController
@RequestMapping("api/im/")
public class RestInventoryController {

	@RequestMapping(value="/create")
	public Item createItem(@RequestBody String itemName, double costPrice, double sellingPrice) {
		return InventoryStub.create(itemName, costPrice, sellingPrice);
	}
	@RequestMapping(value="/delete")
	public String deleteItem(String itemName) {
		return InventoryStub.delete(itemName);
	}
	@RequestMapping(value="/updateBuy") 
	public String updateBuy(String itemName, long quantity) {
		return InventoryStub.updateBuyingQuantity(itemName, quantity);
	}
	@RequestMapping(value="/updateSell")
	public String updateSell(String itemName, long quantity) {
		return InventoryStub.updateSellingQuantity(itemName, quantity);
	}
	@RequestMapping(value="/report")
	public Map<String, List<String>> report() {
//		InventoryStub.report();
		return null;
	}  
}
*/