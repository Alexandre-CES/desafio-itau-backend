package desafio.itau.springboot.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

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

    public DoubleSummaryStatistics getStatistics(){

        ArrayList<Transaction> newTransactions = new ArrayList<Transaction>();

        //get transactions in the last 60 seconds
        OffsetDateTime timeLimit = OffsetDateTime.now().minusSeconds(30);
        if(!this.transactions.isEmpty()){
            for(Transaction transaction : this.transactions){
                if(transaction.getDataHora().isAfter(timeLimit)){
                    newTransactions.add(transaction);
                }
            }
        }
        
        if(newTransactions.isEmpty()){
            DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
            stats.accept(0.0);
            return stats;
        }else{
            return newTransactions
                .stream()
                .collect(Collectors.summarizingDouble(Transaction::getValor));
        }
    }

}
