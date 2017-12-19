package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.main.commands.CreateCommand;
import com.main.commands.UpdateBuyCommand;
import com.main.stub.InventoryStub;

public class UpdateBuyCommandTest {
	
	public CreateCommand cc = null;
	public UpdateBuyCommand ubc = null;
	
	@Before
	public void init() {
		cc = new CreateCommand();
		ubc = new UpdateBuyCommand();
		
		InventoryStub.getInventoryMap().clear();
		InventoryStub.getBuyingListCount().clear();
		InventoryStub.getSellingListCount().clear();
	}

	@Test
	public void testUpdateBuyingQuantity() {
		String[] input0= {"create", "Food01", "1.47", "3.98"};
		cc.execute(input0);
		long acutalCount = InventoryStub.getInstance().getBuyingListCount(input0[1]);
		Assert.assertEquals(0, acutalCount);
		
		String[] input1= {"updateBuy", "Food01", "11"};
		ubc.execute(input1);
		long actualCount1=InventoryStub.getInstance().getBuyingListCount(input0[1]);
		Assert.assertEquals(11, actualCount1);
	}
}
