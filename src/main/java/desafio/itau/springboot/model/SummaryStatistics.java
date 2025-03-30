package desafio.itau.springboot.model;

import java.util.ArrayList;

import desafio.itau.springboot.dto.SummaryStatisticsReturn;

public class SummaryStatistics {

    private int count = 0;
    private Double sum = 0.0;
    private Double avg = 0.0;
    private Double min;
    private Double max = 0.0;

    public SummaryStatistics(ArrayList<Transaction> transactions){
        
        if(!transactions.isEmpty()){
            Double avg = 0.0;

            for(Transaction transaction : transactions){

                //count
                this.count += 1;

                //sum values
                Double value = transaction.getValor();
                this.sum += value;
                avg += value;

                //get max value
                if(this.max < value){
                    this.max = value;
                }

                //get min value
                if(this.min == null){
                    this.min = value;
                }else{
                    if(this.min > value){
                        this.min = value;
                    }
                }
            }

            //get average
            this.avg = avg / this.count;
            
        }else{
            this.min = 0.0;
        }
    }

    //returns summary
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
