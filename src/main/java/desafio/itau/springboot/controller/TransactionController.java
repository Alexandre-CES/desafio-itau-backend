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
    
    /*
        * POST /transacao 
        body = {
            "valor": 123.45,
            "dataHora": "2020-08-07T12:34:56.789-03:00"
        }

        returns =
        201 Created sem nenhum corpo
        A transação foi aceita (ou seja foi validada, está válida e foi registrada)
        422 Unprocessable Entity sem nenhum corpo
        A transação não foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram atendidos - por exemplo: uma transação com valor menor que 0)
        400 Bad Request sem nenhum corpo
        A API não compreendeu a requisição do cliente (por exemplo: um JSON inválido)
            
    */
    @PostMapping
    public ResponseEntity<Object> transaction(@Valid @RequestBody PostTransactionBody body){
        
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
}
