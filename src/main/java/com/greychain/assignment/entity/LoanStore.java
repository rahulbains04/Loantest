package com.greychain.assignment.entity;


import java.util.*;

public class LoanStore {



        private Map<String, Loan> loans;
        private StringBuilder log;

        public LoanStore() {
            loans = new HashMap<>();
            log = new StringBuilder();
        }

        public boolean addLoan(Loan loan) {
            try {
                loan.validatePaymentDate();
            }
            catch (IllegalArgumentException e) {
                log.append("Rejected loan " + loan.getLoanId() + " due to an invalid payment date.\n");
                return false;
            }

            if (loans.containsKey(loan.getLoanId())) {
                return false; // Loan with the same ID already exists.
            }

            loans.put(loan.getLoanId(), loan);
            return true;
        }

        public Map<String, Double> getRemainingAmountAggregationByLender() {
            Map<String, Double> aggregation = new HashMap<>();
            for (Loan loan : loans.values()) {
                String lenderId = loan.getLenderId();
                double remainingAmount = aggregation.getOrDefault(lenderId, 0.0) + loan.getRemainingAmount();
                aggregation.put(lenderId, remainingAmount);
            }
            return aggregation;
        }

        public Map<Integer, Double> getInterestAggregation() {
            Map<Integer, Double> aggregation = new HashMap<>();
            for (Loan loan : loans.values()) {
                int interestPerDay = loan.getInterestPerDay();
                double interestAmount = aggregation.getOrDefault(interestPerDay, 0.0) + (loan.getRemainingAmount() * interestPerDay / 100);
                aggregation.put(interestPerDay, interestAmount);
            }
            return aggregation;
        }

        public Map<String, Double> getRemainingAmountAggregationByCustomerId() {
            Map<String, Double> aggregation = new HashMap<>();
            for (Loan loan : loans.values()) {
                String customerId = loan.getCustomerId();
                double remainingAmount = aggregation.getOrDefault(customerId, 0.0) + loan.getRemainingAmount();
                aggregation.put(customerId, remainingAmount);
            }
            return aggregation;
        }

        public void checkPastDueLoans() {
            for (Loan loan : loans.values()) {
                if (loan.isPastDueDate()) {
                    log.append("Alert: Loan " + loan.getLoanId() + " has passed the due date.\n");
                }
            }
        }

        public String getLog() {
            return log.toString();
        }
    }




