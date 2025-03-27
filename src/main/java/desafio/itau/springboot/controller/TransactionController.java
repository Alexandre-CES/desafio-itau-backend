package desafio.itau.springboot.controller;

//controller
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.ArrayList;

//service
import org.springframework.beans.factory.annotation.Autowired;
import desafio.itau.springboot.service.TransactionService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
//request
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//response
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//dto
import desafio.itau.springboot.dto.PostTransactionBody;
import desafio.itau.springboot.model.Transaction;


@RestController
@RequestMapping("/transacao")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
    @PostMapping
    public ResponseEntity<Object> postTransaction(@Valid @RequestBody PostTransactionBody body){
        
        ArrayList<String> errors = new ArrayList<String>();

        //check value
        if(body.getValor() <= 0){
            errors.add("Invalid value: "+ body.getValor());
        }

        //check dateTime
        if(body.getDataHora().isAfter(OffsetDateTime.now())){
            errors.add("Invalid date: "+ body.getDataHora());
        }

        //if any error, return unprocessable entity and errors
        if(errors.size() > 0){
            return ResponseEntity.unprocessableEntity().body(errors);
        }else{
            //adds transaction
            this.transactionService.addTransaction(
                new Transaction(
                    body.getValor(),
                    body.getDataHora()
                )
            );

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @DeleteMapping
    public void deleteTransaction(){
        this.transactionService.deleteAllTransactions();
    }
}
