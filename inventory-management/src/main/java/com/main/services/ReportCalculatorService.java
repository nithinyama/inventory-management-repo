package com.main.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

	public Map<String, List<String>> calculateReport(Map<String, List<Item>> inventoryMap, Map<String, Long> buyingListCount, Map<String, Long> sellingListCount, Map<String, Long> deletedItemCountMap) {
		Map<String, List<String>> reportMap = new TreeMap<>();
		DecimalFormat df = new DecimalFormat(".##");
		
		if(inventoryMap.size() <= 0)
			return reportMap;
		
		Set<String> itemNames = inventoryMap.keySet();
		BigDecimal totalValueOfAllAvailableItems = new BigDecimal(0.0);
		BigDecimal totalProfitForAllItems = new BigDecimal(0.0);
		
		for(String itemName : itemNames) {
			List<String> details = new ArrayList<>();
			List<Item> items = inventoryMap.get(itemName);
			Item item = items.get(0);
			
			if(!sellingListCount.isEmpty() && sellingListCount.containsKey(item.getItemName())) {
				BigDecimal totalProfitPriceForItem = getTotalProfitPrice(item.getSellingPrice(), item.getCostPrice(), sellingListCount.get(item.getItemName()));
				totalProfitForAllItems = totalProfitForAllItems.add(totalProfitPriceForItem);
				BigDecimal lossForDeletedItems = getLossForDeletedItems(item, deletedItemCountMap);
				totalProfitForAllItems = totalProfitForAllItems.subtract(lossForDeletedItems);
			}
			
			Long quantity = getAvaialbleQty(buyingListCount, sellingListCount, item);
			BigDecimal totalValueOfEachAvailableItems =  BigDecimal.valueOf((item.getCostPrice() * quantity));
			
			totalValueOfAllAvailableItems = totalValueOfAllAvailableItems.add(totalValueOfEachAvailableItems);
			
			details.add(df.format(item.getCostPrice()));
			details.add(df.format(item.getSellingPrice()));
			details.add(Long.toString(quantity));
			details.add(df.format(totalValueOfEachAvailableItems));
			reportMap.put(itemName, details);
		}
		
		Map<String, List<String>> finalMap = new HashMap<>();
		finalMap.putAll(reportMap);
		List<String> list = new ArrayList<>();
		list.add(df.format(totalValueOfAllAvailableItems));
		finalMap.put("TotVal", list);
		List<String> list1 = new ArrayList<>();
		list1.add(df.format(totalProfitForAllItems));
		finalMap.put("Pspr", list1);
		
		printReport(finalMap);
		return finalMap;
	}
	
	private void printReport(Map<String, List<String>> reportMap) {
		System.out.println("               INVENTORY RECEIPT                                     ");
  	    System.out.println("Item Name"+"       Bought At"+"         Sold At"+"            AvailableQty"+"            Value" );
  	    System.out.println("---------"+"       ---------"+"         -------"+"            -------------"+"           -----" );
  	    
  	    Set<String> itemKeys = reportMap.keySet();
  	    for(String name :itemKeys) {
  	    	if(!name.startsWith("TotVal") && !name.startsWith("Pspr")) {
  	    		List<String> details = reportMap.get(name);
  	  	    	System.out.println(name+"             "+details.get(0)+"             "+details.get(1)+"                  "+details.get(2)+"                  "+details.get(3));
  	    	}
  	    }
  	    System.out.println("----------------------------------------------------------------------------------------------------");
	    System.out.println("Total Value                                                                      "+reportMap.get("TotVal").get(0));
	    System.out.println("Profit since previous report                                                     "+reportMap.get("Pspr").get(0));
	}
	
	public BigDecimal getLossForDeletedItems(Item item, Map<String, Long> deletedItemsMap) {
		if(deletedItemsMap.size() > 0 && deletedItemsMap.containsKey(item.getItemName())) {
			return BigDecimal.valueOf(item.getCostPrice() * deletedItemsMap.get(item.getItemName()));
		}
		return new BigDecimal(0.0);
	}
	
	public BigDecimal getTotalProfitPrice(Double sp, Double cp, long soldCount) {
		BigDecimal sc = new BigDecimal(soldCount);
		Double diff = sp - cp;
		return sc.multiply(new BigDecimal(diff));
	}
	
	public BigDecimal getTotalBuyingPrice(Double cp, long count) {
		BigDecimal bd = new BigDecimal(count);
		BigDecimal result = bd.multiply(new BigDecimal(cp));
		return result;
	}
	
	private Long getAvaialbleQty(Map<String, Long> buyingListCount, Map<String, Long> sellingListCount, Item item) {
		long sellCount = 0;
		long buycount = 0;
		if(buyingListCount.containsKey(item.getItemName())) { buycount = buyingListCount.get(item.getItemName()); }
		if(sellingListCount.containsKey(item.getItemName())) { sellCount = sellingListCount.get(item.getItemName()); }
		return (buycount - sellCount);
	}
	
	
}
