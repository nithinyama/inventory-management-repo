package com.main;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.main.commands.CreateCommand;
import com.main.commands.UpdateBuyCommand;
import com.main.commands.UpdateSellPriceCommand;
import com.main.model.Item;
import com.main.stub.InventoryStub;

public class UpdateSellPriceTest {

	public CreateCommand cc = null;
	public UpdateSellPriceCommand uspc = null;
	public UpdateBuyCommand ubc = null;
	
	@Before
	public void init() {
		cc = new CreateCommand();
		uspc = new UpdateSellPriceCommand();
		ubc = new UpdateBuyCommand();
		
		InventoryStub.getInventoryMap().clear();
		InventoryStub.getBuyingListCount().clear();
		InventoryStub.getSellingListCount().clear();
	}
	
	@Test
	public void testUpdateSellPriceItems() {
		String[] input0= {"create", "Food01", "3.47", "6.98"};
		cc.execute(input0);
		
		String[] input1= {"updateBuy", "Food01", "100"};
		ubc.execute(input1);
		
		List<Item> items = InventoryStub.getInventoryMap().get(input0[1]);
		for(Item item : items) {
			double sp =item.getSellingPrice();
			Assert.assertEquals("6.98", Double.toString(sp));
		}
		
		String[] input5= {"updateSellPrice", "Food01", "5.98"};
		uspc.execute(input5);
		
		items.clear();
		items = InventoryStub.getInventoryMap().get(input0[1]);
		for(Item item : items) {
			double sp =item.getSellingPrice();
			Assert.assertEquals("5.98", Double.toString(sp));
		}
	}
}
