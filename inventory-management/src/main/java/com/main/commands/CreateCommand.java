package com.main.commands;

import com.main.stub.InventoryStub;

public class CreateCommand implements Command {
	
	public CreateCommand() {}

	@Override
	public String execute(String[] input) {
		if(input.length < 4)
			return "please enter correct inout.";
		
		InventoryStub.getInstance().create(input[1], new Double(input[2]), new Double(input[3]));
		return null;
	}
}
