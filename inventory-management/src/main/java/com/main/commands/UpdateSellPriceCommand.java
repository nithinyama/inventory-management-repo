package com.main.commands;

public class UpdateSellPriceCommand implements Command {

	@Override
	public String execute(String[] input) {
		if(input.length < 3)
			return "please enter correct inout.";
		
//		InventoryStub.getInstance().updateSellingQuantity(input[1], Long.valueOf(input[2]));
		return null;
	}
}
