package desafio.itau.springboot.model;

import java.util.ArrayList;

import desafio.itau.springboot.dto.SummaryStatisticsReturn;

/*
    *calc and store statistics
 
    The constructor receive an ArrayList<Transaction>
*/
public class SummaryStatistics {

    /*
        *initiate every var but min with 0

        ?min can't be initiated with a value cause 0 is the minimum possible-
        ?and no value can be assigned to be the max

        ?Other values can be initiated with 0-
        ?because it's the minimum value possible for them
    */
    private int count = 0;
    private Double sum = 0.0;
    private Double avg = 0.0;
    private Double min;
    private Double max = 0.0;

    //use the ArrayList<Transaction> to calculating stats, storing in variables
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
            //to avoid null results
            this.min = 0.0;
        }
    }

    //returns new SummaryStatisticsReturn Object
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
