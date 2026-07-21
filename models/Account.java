package models;

import java.time.LocalDate;

/**
 * Represents a bank account in the system
 */

// ID	ACCOUNT_NAME	CREATED_DATE	AMOUNT
// 1	PNYBGPUEDL	    22-OCT-23	    4584.55
public class Account {
    
    private long accountId;
    private String accountName;
    private LocalDate createdDate;
    private double amount;

    public Account() {}

    public Account(String accountName, LocalDate createdDate, double amount) {
        this.accountName = accountName;
        this.createdDate = createdDate;
        this.amount = amount;
    }

    // Getters and Setters
    public long getAccountId() { return accountId; }
    public void setAccountId(long accountId) { this.accountId = accountId; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "{" +
                "accountId=" + accountId + ", " +
                "accountName='" + accountName + '\'' +
                ", createdDate=" + createdDate +
                ", amount=" + amount +
                '}';
    }
}
