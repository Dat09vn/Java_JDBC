import controller.GetAPI;
import models.BankRequest;

import java.sql.SQLException;
import java.util.List;

public class main {
	public static void main(String[] args) {
		try {
			List<BankRequest> requests = GetAPI.getNewRequests();
			if (requests == null || requests.isEmpty()) {
				System.out.println("No new requests found.");
				return;
			}

			System.out.println("New Bank Requests:");
			for (BankRequest r : requests) {
				System.out.println(r);
			}

		} catch (SQLException e) {
			System.err.println("Database error while fetching new requests: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
