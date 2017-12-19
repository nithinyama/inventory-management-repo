package com.main;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.main.commands.CreateCommand;
import com.main.model.Item;
import com.main.stub.InventoryStub;

public class CreateCommandTest {
	
	public CreateCommand cc = null; 
	
	@Before
	public void init() {
		cc = new CreateCommand();
		
		InventoryStub.getInventoryMap().clear();
		InventoryStub.getBuyingListCount().clear();
		InventoryStub.getSellingListCount().clear();
	}
	
	
	@Test
	public void testCreateItem() {
		String[] input1= {"create", "Book01", "10.50", "13.79"};
			cc.execute(input1);
			long acutalCount = InventoryStub.getInstance().getBuyingListCount(input1[1]);
			Assert.assertEquals(1, acutalCount);
			
			Map<String, List<Item>> map = InventoryStub.getInventoryMap();
			List<Item> items = map.get(input1[1]);
			for(Item item : items) {
				Assert.assertEquals(input1[1], item.getItemName());
				Assert.assertEquals(input1[2], Double.toString(item.getCostPrice())+"0");
				Assert.assertEquals(input1[3],  Double.toString(item.getSellingPrice()));
			}
	}

	@Test
	public void testCreatefiveItems() {
		String[] input0= {"create", "Food01", "1.47", "3.98"};
		cc.execute(input0);
		String[] input2= {"create", "Good01", "57.67", "63.98"};
		cc.execute(input2);
		String[] input3= {"create", "Med01", "51.47", "56.98"};
		cc.execute(input3);
		String[] input4= {"create", "Flour01", "5.47", "7.98"};
		cc.execute(input4);
		String[] input5= {"create", "Tab01", "100.47", "133.98"};
		cc.execute(input5);
		String[] input6= {"create", "Food01", "1.47", "3.98"};
		cc.execute(input6);
		long acutalCount = InventoryStub.getInstance().getBuyingListCount(input5[1]) ;
		Assert.assertEquals(1, acutalCount);
		acutalCount = InventoryStub.getInstance().getBuyingListCount(input6[1]) ;
		Assert.assertEquals(2, acutalCount);
	}
	
}
