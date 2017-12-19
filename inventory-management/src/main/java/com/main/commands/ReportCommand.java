package com.main.commands;

import com.main.stub.InventoryStub;

public class ReportCommand implements Command {

	@Override
	public String execute(String[] input) {
		InventoryStub.getInstance().report();
		return null;
		
	}
}
