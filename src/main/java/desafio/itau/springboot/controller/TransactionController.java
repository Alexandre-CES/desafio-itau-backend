package desafio.itau.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.itau.springboot.service.TransactionService;

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
    public ResponseEntity<Object> transaction(){
        this.transactionService.postTransaction();

        return ResponseEntity.ok(HttpStatus.OK);
    }


}
