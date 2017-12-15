package com.main;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.main.commands.CreateCommand;
import com.main.commands.UpdateSellPriceCommand;
import com.main.controllers.InventoryStub;
import com.main.model.Item;

public class UpdateSellPriceTest {

	public CreateCommand cc = null;
	public UpdateSellPriceCommand uspc = null;
	
	@Before
	public void init() {
		cc = new CreateCommand();
		uspc = new UpdateSellPriceCommand();
	}
	
	@Test
	public void testUpdateSellPriceItems() {
		String[] input0= {"create", "Food01", "3.47", "6.98"};
		String[] input2= {"create", "Food01", "3.47", "6.98"};
		String[] input4= {"create", "Food01", "3.47", "6.98"};
		
		cc.execute(input0);
		cc.execute(input2);
		cc.execute(input4);
		
		double sp = InventoryStub.getInventoryMap().get(input0[1]).get(0).getSellingPrice();
		Assert.assertEquals("6.98", Double.toString(sp));
		
		String[] input5= {"updateSellPrice", "Food01", "5.98"};
		
		uspc.execute(input5);
		List<Item> items = InventoryStub.getInventoryMap().get(input0[1]);
		for(Item item : items) {
			sp =item.getSellingPrice();
			Assert.assertEquals("5.98", Double.toString(sp));
		}
	}
}
