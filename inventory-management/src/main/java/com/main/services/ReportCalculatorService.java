package com.main.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.main.model.Item;

public class ReportCalculatorService {

	private static ReportCalculatorService rcs;
	
	public static ReportCalculatorService getInstance(){
		if(rcs == null)
			return new ReportCalculatorService();
		else
			return rcs;
	}

	public Map<String, List<String>> calculateReport(Map<String, List<Item>> inventoryMap, Map<String, Long> buyingListCount, Map<String, Long> sellingListCount) {
		Map<String, List<String>> reportMap = new TreeMap<>();
		
		if(inventoryMap.size() <= 0)
			return reportMap;
		
		Set<String> itemNames = inventoryMap.keySet();
		BigDecimal totalBuyingPriceForAllItems = new BigDecimal(0.0);
		BigDecimal totalProfitForAllItems = new BigDecimal(0.0);
		
		for(String itemName : itemNames) {
			List<String> details = new ArrayList<>();
			List<Item> items = inventoryMap.get(itemName);
			Item item = items.get(0);
			
			BigDecimal totalBuyingPriceForItem = getTotalBuyingPrice(item.getCostPrice(), buyingListCount.get(item.getItemName()));
			totalBuyingPriceForAllItems = totalBuyingPriceForAllItems.add(totalBuyingPriceForItem);
			
			if(!sellingListCount.isEmpty() && sellingListCount.containsKey(item.getItemName())) {
				BigDecimal totalProfitPriceForItem = getTotalProfitPrice(item.getSellingPrice(), item.getCostPrice(), sellingListCount.get(item.getItemName()));
				totalProfitForAllItems = totalProfitForAllItems.add(totalProfitPriceForItem);
			}
			details.add(Double.toString(item.getCostPrice()));
			details.add(Double.toString(item.getSellingPrice()));
			details.add(getAvaialbleQty(buyingListCount, sellingListCount, item));
			details.add(totalBuyingPriceForItem.toString());
			reportMap.put(itemName, details);
		}
		
		Map<String, List<String>> finalMap = new HashMap<>();
		finalMap.putAll(reportMap);
		List<String> list = new ArrayList<>();
		list.add(totalBuyingPriceForAllItems.toString());
		finalMap.put("Total value", list);
		List<String> list1 = new ArrayList<>();
		list1.add(totalProfitForAllItems.toString());
		finalMap.put("Profit since previous report", list1);
		
		printReport(finalMap);
		return finalMap;
	}
	
	private void printReport(Map<String, List<String>> reportMap) {
		System.out.println("               INVENTORY RECEIPT                                     ");
  	    System.out.println("Item Name"+"       Bought At"+"         Sold At"+"            AvailableQty"+"            Value" );
  	    System.out.println("---------"+"       ---------"+"         -------"+"            -------------"+"           -----" );
  	    
  	    Set<String> itemKeys = reportMap.keySet();
  	    for(String name :itemKeys) {
  	    	if(!name.startsWith("Total") && !name.startsWith("Profit")) {
  	    		List<String> details = reportMap.get(name);
  	  	    	System.out.println(name+"             "+details.get(0)+"             "+details.get(1)+"                  "+details.get(2)+"                  "+details.get(3));
  	    	}
  	    }
  	    System.out.println("----------------------------------------------------------------------------------------------------");
	    System.out.println("Total Value                                                                      "+reportMap.get("Total value").get(0));
	    System.out.println("Profit since previous report                                                     -NA");
	}
	
	public BigDecimal getTotalProfitPrice(Double sp, Double cp, long soldCount) {
		BigDecimal bd = new BigDecimal(soldCount);
		Double diff = sp - cp;
		return bd.multiply(new BigDecimal(diff));
	}
	
	public BigDecimal getTotalBuyingPrice(Double cp, long count) {
		BigDecimal bd = new BigDecimal(count);
		BigDecimal result = bd.multiply(new BigDecimal(cp));
		return result;
	}
	
	private String getAvaialbleQty(Map<String, Long> buyingListCount, Map<String, Long> sellingListCount, Item item) {
		long sellCount = 0;
		long buycount = 0;
		if(buyingListCount.containsKey(item.getItemName())) { buycount = buyingListCount.get(item.getItemName()); }
		if(sellingListCount.containsKey(item.getItemName())) { sellCount = sellingListCount.get(item.getItemName()); }
		return Long.toString((buycount - sellCount));
	}
	
	
}
