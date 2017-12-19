package com.main.commands;

import com.main.stub.InventoryStub;

public class UpdateBuyCommand implements Command {

	@Override
	public String execute(String[] input) {
		if(input.length < 3)
			return "please enter correct inout.";
		
		InventoryStub.getInstance().updateBuyingQuantity(input[1], Long.valueOf(input[2]));
		return null;
	}
}
