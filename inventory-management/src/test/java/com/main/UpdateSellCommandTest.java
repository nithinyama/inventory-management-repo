package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.main.commands.CreateCommand;
import com.main.commands.UpdateBuyCommand;
import com.main.commands.UpdateSellCommand;
import com.main.stub.InventoryStub;

public class UpdateSellCommandTest {

	public CreateCommand cc = null;
	public UpdateSellCommand usc = null;
	public UpdateBuyCommand ubc = null;
	
	@Before
	public void init() {
		cc = new CreateCommand();
		usc = new UpdateSellCommand();
		ubc = new UpdateBuyCommand();
		
		InventoryStub.getInventoryMap().clear();
		InventoryStub.getBuyingListCount().clear();
		InventoryStub.getSellingListCount().clear();
	}
	
	@Test
	public void test() {
		String[] input0= {"create", "Food01", "1.47", "3.98"};
		String[] input3= {"create", "Med01", "51.47", "56.98"};

		cc.execute(input0);
		cc.execute(input3);
		
		long acutalBuyingCount = InventoryStub.getInstance().getBuyingListCount(input0[1]) ;
		Assert.assertEquals(0, acutalBuyingCount);
		
		String[] input1= {"updateBuy", "Food01", "10"};
		ubc.execute(input1);
		
		String[] input5= {"updateSell", "Food01", "2"};
		usc.execute(input5);
		
		long actualSellingCount = InventoryStub.getInstance().getSellingListCount(input0[1]) ;
		
		Assert.assertEquals(2, actualSellingCount);
		
		acutalBuyingCount = InventoryStub.getInstance().getBuyingListCount(input0[1]) ;
		Assert.assertEquals(10, acutalBuyingCount);
	}
}
