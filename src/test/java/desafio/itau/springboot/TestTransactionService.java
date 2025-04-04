package desafio.itau.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import desafio.itau.springboot.dto.SummaryStatisticsReturn;
import desafio.itau.springboot.model.Transaction;
import desafio.itau.springboot.service.TransactionService;

@SpringBootTest
public class TestTransactionService {

    @Autowired
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

    @Test
    public void deleteAllTransactions(){
        OffsetDateTime dateTime = OffsetDateTime.now();

        Transaction transaction = new Transaction(123.0, dateTime);

        //check if list isn't empty before test
        assertTrue(transactionService.getStatistics().getCount() == 0);
        transactionService.addTransaction(transaction);
        assertFalse(transactionService.getStatistics().getCount() == 0);

        transactionService.deleteAllTransactions();
        assertTrue(transactionService.getStatistics().getCount() == 0);
    }

    @Test
    public void getStatistics(){
        
        //create two transactions
        Transaction transaction = new Transaction(150.0, OffsetDateTime.now());
        Transaction transaction2 = new Transaction(150.0, OffsetDateTime.now());

        //add them to transaction service
        assertTrue(transactionService.getStatistics().getCount() == 0);
        transactionService.addTransaction(transaction);
        transactionService.addTransaction(transaction2);
        assertTrue(transactionService.getStatistics().getCount() == 2);

        SummaryStatisticsReturn result = transactionService.getStatistics();

        assertEquals(2, result.getCount());
        assertEquals(300.0, result.getSum());
        assertEquals(150.0, result.getAvg());
        assertEquals(150.0, result.getMax());
        assertEquals(150.0, result.getMin());
    }

}
