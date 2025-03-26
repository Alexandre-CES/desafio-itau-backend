package desafio.itau.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private Double valor;
    private OffsetDateTime dataHora;
}
