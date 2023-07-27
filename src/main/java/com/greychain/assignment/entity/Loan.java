package com.greychain.assignment.entity;

import java.util.Date;

public class Loan {
    //Defining all the fields
    private String loanId;
    private String customerId;
    private String lenderId;
    private double amount;
    private double remainingAmount;
    private Date paymentDate;
    private int interestPerDay;
    private Date dueDate;
    private double penaltyPerDay;


    //constructor with all fields


    public Loan(String loanId, String customerId, String lenderId, double amount, double remainingAmount, Date paymentDate, int interestPerDay, Date dueDate, double penaltyPerDay) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.lenderId = lenderId;
        this.amount = amount;
        this.remainingAmount = remainingAmount;
        this.paymentDate = paymentDate;
        this.interestPerDay = interestPerDay;
        this.dueDate = dueDate;
        this.penaltyPerDay = penaltyPerDay;
    }

    public String getLoanId() {
        return loanId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getLenderId() {
        return lenderId;
    }

    public double getAmount() {
        return amount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public int getInterestPerDay() {
        return interestPerDay;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public double getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public void validatePaymentDate() {
        if (paymentDate == null || dueDate == null) {
            throw new IllegalArgumentException("Payment date or due date is missing.");
        }

        if (paymentDate.compareTo(dueDate) > 0) {
            throw new IllegalArgumentException("Payment date cannot be greater than the due date.");
        }
    }

    public boolean isPastDueDate() {
        return new Date().after(dueDate);
    }
}
