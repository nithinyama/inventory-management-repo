package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.main.commands.CreateCommand;
import com.main.commands.DeleteCommand;
import com.main.stub.InventoryStub;

public class DeleteCommandTest {

	public CreateCommand cc = null;
	public DeleteCommand dc = null;
	
	@Before
	public void init() {
		cc = new CreateCommand();
		dc = new DeleteCommand();
		
		InventoryStub.getInventoryMap().clear();
		InventoryStub.getBuyingListCount().clear();
		InventoryStub.getSellingListCount().clear();
	}
	
	@Test
	public void testDeleteItems() {
		String[] input1= {"create", "Book01", "10.50", "13.79"};
		cc.execute(input1);
		long actualCount = InventoryStub.getInstance().getBuyingListCount(input1[1]);
		Assert.assertEquals(1, actualCount);
		
		String[] input2= {"delete", input1[1]};
		dc.execute(input2);
		actualCount = InventoryStub.getInstance().getBuyingListCount(input1[1]);
		Assert.assertEquals(0, actualCount);
	}

}
