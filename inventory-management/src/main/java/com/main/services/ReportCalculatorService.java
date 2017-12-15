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
		Map<String, List<String>> reportMap = new HashMap<>();
		
		if(inventoryMap.size() <= 0)
			return reportMap;
		
		Set<String> itemNames = reportMap.keySet();
		BigDecimal totalBuyingPriceForAllItems = new BigDecimal(0.0);
		BigDecimal totalProfitForAllItems = new BigDecimal(0.0);
		
		for(String itemName : itemNames) {
			List<String> details = new ArrayList<>();
			List<Item> items = inventoryMap.get(itemName);
			Item item = items.get(0);
			
			BigDecimal totalBuyingPriceForItem = getTotalBuyingPrice(item.getCostPrice(), buyingListCount.get(item.getItemName()));
			totalBuyingPriceForAllItems = totalBuyingPriceForAllItems.add(totalBuyingPriceForItem);
			
			BigDecimal totalProfitPriceForItem = getTotalProfitPrice(item.getSellingPrice(), item.getCostPrice(), sellingListCount.get(item.getItemName()));
			totalProfitForAllItems = totalProfitForAllItems.add(totalProfitPriceForItem);
			
			details.add(Double.toString(item.getCostPrice()));
			details.add(Double.toString(item.getSellingPrice()));
			details.add(buyingListCount.get(item.getItemName()).toString());
			details.add(totalBuyingPriceForItem.toString());
			reportMap.put(itemName, details);
		}
		
		List<String> list = new ArrayList<>();
		list.add(totalBuyingPriceForAllItems.toString());
		reportMap.put("Total value", list);
		list.clear();
		list.add(totalProfitForAllItems.toString());
		reportMap.put("Profit since previous report", list);
		
//		printReport(reportMap);
		return new TreeMap<>(reportMap);
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
	
	
}
