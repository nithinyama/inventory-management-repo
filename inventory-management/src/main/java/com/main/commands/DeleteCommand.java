package com.main.commands;

import com.main.controllers.InventoryStub;

public class DeleteCommand implements Command {

	@Override
	public String execute(String[] input) {
		if(input.length < 2)
			return "please enter correct inout.";
		
		InventoryStub.getInstance().delete(input[1]);
		return null;
	}
}
