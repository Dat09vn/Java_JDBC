package controller;

import models.BankRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

import java.time.LocalDateTime;

/**
 * GetAPI
 */
public class CollectBankRequest {

    public static List<BankRequest> getNewRequests(String status) throws SQLException {
        List<BankRequest> requests = new ArrayList<>();
        String selectSQL = "SELECT * FROM bank_requests WHERE status = ? ORDER BY submitted_at";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BankRequest request = mapResultSetToRequest(rs);
                requests.add(request);
            }
        }
        return requests;
    }
    
    private static BankRequest mapResultSetToRequest(ResultSet rs) throws SQLException {
        BankRequest request = new BankRequest();
        try {
            request.setRequestId(rs.getLong("request_id"));
        } catch (SQLException e) {
            // ignore if column missing
        }
        try { request.setRequestType(rs.getString("request_type")); } catch (SQLException e) {}
        try { request.setStatus(rs.getString("status")); } catch (SQLException e) {}
        try { request.setAccountNumber(rs.getString("account_number")); } catch (SQLException e) {}
        try { request.setAccountHolder(rs.getString("account_holder")); } catch (SQLException e) {}
        try { request.setInitialBalance(rs.getDouble("initial_balance")); } catch (SQLException e) {}
        try { request.setParameters(rs.getString("parameters")); } catch (SQLException e) {}
        try {
            Timestamp submittedTs = rs.getTimestamp("submitted_at");
            if (submittedTs != null) request.setSubmittedAt(submittedTs.toLocalDateTime());
        } catch (SQLException e) {}
        try {
            Timestamp processedTs = rs.getTimestamp("processed_at");
            if (processedTs != null) request.setProcessedAt(processedTs.toLocalDateTime());
        } catch (SQLException e) {}
        try { request.setResult(rs.getString("result")); } catch (SQLException e) {}
        return request;
    }
    
}
