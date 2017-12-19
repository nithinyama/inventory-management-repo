package com.main.commands;

import com.main.stub.InventoryStub;

public class UpdateSellPriceCommand implements Command {

	@Override
	public String execute(String[] input) {
		if(input.length < 3)
			return "please enter correct inout.";
		
		InventoryStub.getInstance().updateSellingPrice(input[1], new Double(input[2]));
		return null;
	}
}
