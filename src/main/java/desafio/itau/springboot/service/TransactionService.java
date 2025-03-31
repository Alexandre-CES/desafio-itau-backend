package desafio.itau.springboot.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import desafio.itau.springboot.dto.SummaryStatisticsReturn;
import desafio.itau.springboot.model.SummaryStatistics;
import desafio.itau.springboot.model.Transaction;

@Service
public class TransactionService {
    
    //List of transactions
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    //adds a new transaction to the list
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    //Delete all transactions of ArrayList
    public void deleteAllTransactions(){
        transactions.clear();
    }

    //Returns statistics from transactions in the last 60 seconds
    public SummaryStatisticsReturn getStatistics(){
        
        //New array to store just new transactions
        ArrayList<Transaction> newTransactions = new ArrayList<Transaction>();

        //get the last 60 seconds
        OffsetDateTime timeLimit = OffsetDateTime.now().minusSeconds(60);

        //check if list is not empty
        if(!this.transactions.isEmpty()){

            //iterate each transaction
            for(Transaction transaction : this.transactions){

                //check if transaction was created in the timeLimit
                if(transaction.getDataHora().isAfter(timeLimit)){

                    //add to newTransactions
                    newTransactions.add(transaction);
                }
            }
        }
        
        //create a new SummaryStatistic Object with newTransaction list
        SummaryStatistics summaryStatistics = new SummaryStatistics(newTransactions);

        //return statistics
        return summaryStatistics.getStatistics();
    }

}
