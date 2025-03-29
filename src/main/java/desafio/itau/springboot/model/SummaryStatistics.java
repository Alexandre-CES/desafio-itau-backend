package desafio.itau.springboot.model;

import java.util.ArrayList;

import desafio.itau.springboot.dto.SummaryStatisticsReturn;

public class SummaryStatistics {

    private int count = 0;
    private Double sum = 0.0;
    private Double avg = 0.0;
    private Double min = 0.0;
    private Double max = 0.0;

    public SummaryStatistics(ArrayList<Transaction> transactions){
        if(!transactions.isEmpty()){

        }
    }

    public SummaryStatisticsReturn getStatistics(){
        return new SummaryStatisticsReturn(
            this.count,
            this.sum,
            this.avg,
            this.min,
            this.max
        );
    }
}
