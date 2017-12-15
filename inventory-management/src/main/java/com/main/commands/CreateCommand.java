package com.main.commands;

import com.main.controllers.InventoryStub;

public class CreateCommand implements Command {

	@Override
	public String execute(String[] input) {
		if(input.length < 3)
			return "please enter correct inout.";
		
		InventoryStub.getInstance().create(input[0], new Double(input[1]), new Double(input[2]));
		return null;
	}
}
