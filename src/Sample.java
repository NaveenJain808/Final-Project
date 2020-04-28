import java.util.ArrayList;
public class Sample {
    ArrayList<Double> OriginalData;
    ArrayList<ArrayList<Double>> nest; 
    ArrayList<Double> Averages;
    public Sample(int samplesize){
        this.OriginalData = new ArrayList<Double>();
        this.nest = new ArrayList<ArrayList<Double>>();
        this.Averages = new ArrayList<Double>();
        //initialize the nest arraylist to store the random samples
        for(int f = 0;f < samplesize;f++){
            nest.add(new ArrayList<Double>());
        }
    }
    public Sample(ArrayList<Double> averages2) {
	}
	ArrayList<Double> getOriginalData(){
        return this.OriginalData;
    }    
    ArrayList<ArrayList<Double>> getnest(){
        return this.nest;
    }
    ArrayList<Double> getAverages(){
        return this.Averages;
    }
    public double mean(ArrayList<Double> Averages){
        double count=0;
        for(int i=0;i<Averages.size();i++){
            count=count+Averages.get(i);
        }
        return count/Averages.size();
    }
}