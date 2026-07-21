import controller.CollectBankRequest;
import models.BankRequest;

import java.sql.SQLException;
import java.util.List;

public class main {

	// function to print BankRequest objects in a readable format
	private static void printBankRequests(String status) {
		try {
			List<BankRequest> requests = CollectBankRequest.getNewRequests(status);
			if (requests == null || requests.isEmpty()) {
				System.out.println("No new requests found.");
				return;
			}
			for (BankRequest r : requests) {
				System.out.println(r);
			}
		} catch (SQLException e) {
			System.err.println("Database error while fetching new requests: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// function to print Account objects in a readable format
	private static void printAccounts(String createdDate, String accountName) {
		try {
			List<models.Account> accounts = controller.CollectAccount.getAccount(accountName);
			if (accounts == null || accounts.isEmpty()) {
				System.out.println("No accounts found.");
				return;
			}
			for (models.Account a : accounts) {
				System.out.println(a);
			}
		} catch (SQLException e) {
			System.err.println("Database error while fetching accounts: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Main method to run the application
	public static void main(String[] args) {
		// Create a terminal for user input to choose CollectAccount or CollectBankRequest
		while(true) {
			System.out.println("Choose an option:");
			System.out.println("1. Collect Account");
			System.out.println("2. Collect Bank Request");
			System.out.println("3. Exit");
			System.out.print("Enter your choice (1, 2, or 3): ");
			int choice = 0;
			try {
				choice = Integer.parseInt(new java.util.Scanner(System.in).nextLine());
			} catch (NumberFormatException e) {
				System.err.println("Invalid input. Please enter 1, 2, or 3.");
				continue;
			}

			if (choice == 1) {
				// Implementation for collecting account information
				printAccounts("2023-10-22", "PNYBGPUEDL");
			} else if (choice == 2) {
				// Implementation for collecting bank requests
				printBankRequests("NEW");
			} else if (choice == 3) {
				System.out.println("Exiting...");
				break;
			} else {
				System.err.println("Invalid choice. Please enter 1, 2, or 3.");
			}
			System.out.println(); // Add a blank line for better readability between iterations
		}
	}
}
