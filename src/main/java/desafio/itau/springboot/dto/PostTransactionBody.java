package desafio.itau.springboot.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostTransactionBody {

    @NotNull
    Double valor;

    @NotNull
    OffsetDateTime dataHora;
}
