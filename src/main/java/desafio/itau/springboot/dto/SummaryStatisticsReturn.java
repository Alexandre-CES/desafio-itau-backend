package desafio.itau.springboot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SummaryStatisticsReturn {

    @NotNull
    private int count;

    @NotNull
    private Double sum;

    @NotNull
    private Double avg;

    @NotNull
    private Double min;
    
    @NotNull
    private Double max;

    public SummaryStatisticsReturn(
        int count, Double sum, Double avg, Double min, Double max
    ){
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max; 
    }
}
