package desafio.itau.springboot.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import desafio.itau.springboot.model.Transaction;

@Service
public class TransactionService {
    
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    //adds a new transaction to the list
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void deleteAllTransactions(){
        transactions.clear();
    }
}
