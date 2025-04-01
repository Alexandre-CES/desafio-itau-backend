package desafio.itau.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import desafio.itau.springboot.model.Transaction;
import desafio.itau.springboot.service.TransactionService;

public class TestTransactionService {

    private TransactionService transactionService;

    @BeforeEach
    public void startTransactionService(){
        transactionService = new TransactionService();
    }

    @Test
    public void testAddTransaction(){
        OffsetDateTime dateTime = OffsetDateTime.now();

        Transaction transaction = new Transaction(123.0, dateTime);

        //check if list is empty
        assertTrue(transactionService.getStatistics().getCount() == 0);

        transactionService.addTransaction(transaction);

        //check if list isn't empty
        assertFalse(transactionService.getStatistics().getCount() == 0);
    }


}
