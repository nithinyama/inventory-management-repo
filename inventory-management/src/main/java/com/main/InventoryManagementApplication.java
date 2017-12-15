package com.main;

import java.util.HashMap;
import java.util.Map;

import com.main.commands.Command;
import com.main.commands.CreateCommand;
import com.main.commands.DeleteCommand;
import com.main.commands.ReportCommand;
import com.main.commands.UpdateBuyCommand;
import com.main.commands.UpdateSell;

public class InventoryManagementApplication {

	public static void main(String[] args) {
		if(args == null) {
			System.out.println("Please enter the command");
			System.exit(0);
		}

		Map<String, Object> cmdMap = init();
		
		while (true) {
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("exit"))break;
				Command cmd = (Command) cmdMap.get(args[0]);
				String result = cmd.execute(args);
				if(null != result && !(result.isEmpty())) System.out.println(result);
			}
		}
	}
	
	//initilalize commands
	public static Map<String, Object> init() {
		Map<String, Object> map = new HashMap<>();
		map.put(Commands.CREATE, CreateCommand.class);
		map.put(Commands.DELETE, DeleteCommand.class);
		map.put(Commands.UPDATESELL, UpdateSell.class);
		map.put(Commands.UPDATEBUY, UpdateBuyCommand.class);
		map.put(Commands.REPORT, ReportCommand.class);
//		map.put(Commands.UPDATESELLPRICE, UpdateSellPriceCommand.class);
		return null;
	}
}
