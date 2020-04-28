import java.util.ArrayList;
import java.util.*;
public class statistics extends Sample {
    ArrayList<Double> Averages;
    public statistics(ArrayList<Double> Averages) {
        super(Averages);
        this.Averages=Averages;
    }
    public double standardDeviation(double average){
        double store=0;
        for(int i=0;i<Averages.size();i++){
            double data=(Averages.get(i)-average)*(Averages.get(i)-average);
            store=store+data;
        }   
        store=store/Averages.size();
        store=Math.sqrt(store);
        return store;
    }
}