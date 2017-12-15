package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.main.commands.CreateCommand;
import com.main.commands.UpdateSellCommand;
import com.main.controllers.InventoryStub;

public class UpdateSellCommandTest {

	public CreateCommand cc = null;
	public UpdateSellCommand usc = null;
	
	@Before
	public void init() {
		cc = new CreateCommand();
		usc = new UpdateSellCommand();
		
		InventoryStub.getInventoryMap().clear();
		InventoryStub.getBuyingListCount().clear();
		InventoryStub.getSellingListCount().clear();
	}
	
	@Test
	public void test() {
		String[] input0= {"create", "Food01", "1.47", "3.98"};
		String[] input2= {"create", "Food01", "57.67", "63.98"};
		String[] input3= {"create", "Med01", "51.47", "56.98"};
		String[] input4= {"create", "Food01", "5.47", "7.98"};

		cc.execute(input0);
		cc.execute(input2);
		cc.execute(input3);
		cc.execute(input4);
		
		long acutalBuyingCount = InventoryStub.getInstance().getBuyingListCount(input4[1]) ;
		Assert.assertEquals(3, acutalBuyingCount);
		
		String[] input5= {"updateSell", "Food01", "2"};
		usc.execute(input5);
		
		long actualSellingCount = InventoryStub.getInstance().getSellingListCount(input4[1]) ;
		
		Assert.assertEquals(2, actualSellingCount);
		
		Assert.assertEquals(3, acutalBuyingCount);
	}
}
