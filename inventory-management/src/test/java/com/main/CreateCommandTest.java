package com.main;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.main.commands.CreateCommand;
import com.main.controllers.InventoryStub;
import com.main.model.Item;

public class CreateCommandTest {
	
	CreateCommand cc = new CreateCommand();
	
	//@Test
	/*public void testCreateItem() {
		String[] input1= {"Book01", "10.50", "13.79"};
//		while(true) {
			cc.execute(input1);
			long acutalCount = InventoryStub.getInstance().getBuyingListCount(input1[0]);
			Assert.assertEquals(1, acutalCount);
			
			InventoryStub.getInstance();
			Map<String, List<Item>> map = InventoryStub.getInventoryMap();
			List<Item> items = map.get(input1[0]);
			for(Item item : items) {
				Assert.assertEquals(input1[0], item.getItemName());
				Assert.assertEquals(input1[1], Double.toString(item.getCostPrice())+"0");
				Assert.assertEquals(input1[2],  Double.toString(item.getSellingPrice()));
			}
//			break;
//		}
	}*/

	@Test
	public void testCreatefiveItems() {
		String[] input0= {"Food01", "1.47", "3.98"};
		cc.execute(input0);
		String[] input2= {"Good01", "57.67", "63.98"};
		cc.execute(input2);
		String[] input3= {"Med01", "51.47", "56.98"};
		cc.execute(input3);
		String[] input4= {"Flour01", "5.47", "7.98"};
		cc.execute(input4);
		String[] input5= {"Tab01", "100.47", "133.98"};
		cc.execute(input5);
		long acutalCount = InventoryStub.getInstance().getBuyingListCount(input5[0]) ;
		Assert.assertEquals(5, acutalCount);
	}
	
}
