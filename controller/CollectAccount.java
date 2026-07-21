package controller;

import models.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

import java.time.LocalDate;

/**
 * GetAPI
 */
public class CollectAccount {

    public static List<Account> getAccount( String accountName) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String selectSQL = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NAME = ? ORDER BY CREATED_DATE DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, accountName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Account account = mapResultSetToAccount(rs);
                    accounts.add(account);
                }
            }
        }
        return accounts;
    }


    private static Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        try {
            account.setAccountId(rs.getLong("account_id"));
        } catch (SQLException e) {
            // ignore if column missing
        }
        try { account.setAccountName(rs.getString("account_name")); } catch (SQLException e) {}
        try {
            java.sql.Date sqlDate = rs.getDate("created_date");
            if (sqlDate != null) account.setCreatedDate(sqlDate.toLocalDate());
        } catch (SQLException e) {}
        try { account.setAmount(rs.getDouble("amount")); } catch (SQLException e) {}
        
        return account;
    }
    
}
