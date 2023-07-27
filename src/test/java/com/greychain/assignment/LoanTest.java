package com.greychain.assignment;

import com.greychain.assignment.entity.Loan;
import com.greychain.assignment.entity.LoanStore;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;
import java.util.Map;


@SpringBootTest
public class LoanTest {

    @Test(expected = IllegalArgumentException.class)
    public void testLoanValidationWithInvalidPaymentDate() {
        Loan loan1 = new Loan("L1", "C1", "LEN1", 10000, 10000, new Date(123,8,5),
                1, new Date(123,7,5), 0.01);

        loan1.validatePaymentDate(); // Expecting IllegalArgumentException
    }

    @Test
    public void testLoanValidationWithValidPaymentDate() {
        Loan loan2 = new Loan("L2", "C1", "LEN1", 20000, 5000,new Date(123,6,1),
                1, new Date(123,8,05), 0.01);

        loan2.validatePaymentDate(); // Should pass without any exceptions
    }

    @Test
    public void testRemainingAmountAggregationByLender() {
        LoanStore loanStore = new LoanStore();

        Loan loan1 = new Loan("L1", "C1", "LEN1", 10000, 10000, new Date(123,6,5),
                1, new Date(123,7,5), 0.01);
        Loan loan2 = new Loan("L2", "C1", "LEN1", 20000, 5000, new Date(123,6,1),
                1, new Date(123,8,05), 0.01);
        Loan loan3 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(123,4,4),
                2, new Date(123,5,4), 0.02);
        Loan loan4 = new Loan("L4","C3","LEN2",50000,30000,new Date(123,4,4),
                2,new Date(123,5,4),0.02);

        loanStore.addLoan(loan1);
        loanStore.addLoan(loan2);
        loanStore.addLoan(loan3);
        loanStore.addLoan(loan4);

        Map<String, Double> lenderAggregation = loanStore.getRemainingAmountAggregationByLender();

        assertEquals(2, lenderAggregation.size());
        assertEquals(15000, lenderAggregation.get("LEN1"), 0.01);
        assertEquals(30000, lenderAggregation.get("LEN2"), 0.01);
    }
}
