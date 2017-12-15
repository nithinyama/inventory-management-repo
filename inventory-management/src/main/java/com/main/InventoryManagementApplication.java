package com.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.main.commands.Command;

public class InventoryManagementApplication {

	private static Scanner sc;
	private static Map<String, String> cmdMap;

	public static void main(String[] args) {

		init();
		sc = new Scanner(System.in);

		while (true) {
			String input = sc.nextLine();

			if (input.equals("quit")) {
				System.exit(0);
			}

			String[] inputArr = input.split(" ");
			if (!cmdMap.containsKey(inputArr[0])) {
				System.out.println("Please enter one of the following command.");
				System.out.println(cmdMap.keySet());
				System.exit(0);
			}

			if (inputArr.length > 0) {

				try {
					Command cmd = (Command) Class.forName(cmdMap.get(inputArr[0])).newInstance();
					String result = cmd.execute(inputArr);
					
					if (null != result && !(result.isEmpty()))
						System.out.println(result);
				}
				catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				catch (IllegalArgumentException e) {
					e.printStackTrace();
				} 

			}
		}
	}

	// initilalize commands
	public static void init() {
		cmdMap = new HashMap<>();
		cmdMap.put(Commands.CREATE, "com.main.commands.CreateCommand");
		cmdMap.put(Commands.DELETE, "com.main.commands.DeleteCommand");
		cmdMap.put(Commands.UPDATESELL, "com.main.commands.UpdateSellCommand");
		cmdMap.put(Commands.UPDATEBUY, "com.main.commands.UpdateBuyCommand");
		cmdMap.put(Commands.REPORT, "com.main.commands.ReportCommand");
		cmdMap.put(Commands.UPDATESELLPRICE, "com.main.commands.UpdateSellPriceCommand");

	}
}
