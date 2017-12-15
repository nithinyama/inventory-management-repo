package com.main.commands;

import com.main.controllers.InventoryStub;

public class UpdateSellCommand implements Command {

	@Override
	public String execute(String[] input) {
		if(input.length < 3)
			return "please enter correct inout.";
		
		InventoryStub.getInstance().updateSellingQuantity(input[1], Long.valueOf(input[2]));
		return null;
	}
}
