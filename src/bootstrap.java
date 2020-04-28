import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.*;
 
public class bootstrap{   
    public static void main(String[] args)throws FileNotFoundException{
        Scanner console = new Scanner(System.in);
        System.out.println("enter file location e.g. C:/Users/redso/Desktop/HeightData.txt");
        String location = console.nextLine();
        System.out.println("enter a number of samples");
        int samplesize = console.nextInt();
        System.out.println("enter size of sample");
        int samplelength=console.nextInt();

        Sample mean = new Sample(samplesize);
        //original data will store all observations from a dataset
        ArrayList<Double> OriginalData=mean.getOriginalData();
        //nest is the nested arraylist to store random samples from the data set, and to take averages of those samples 
        ArrayList<ArrayList<Double>> nest=mean.getnest();
        //this will store the values of each sample, and will be used to create a confidence interval for the population average
        ArrayList<Double> Averages=mean.getAverages();

        readFile(location, OriginalData);
        fillSample(OriginalData, nest,samplelength);
        getAverage(nest,Averages);
        sort(Averages);
    }
    public static String readFile(String location,ArrayList OriginalData) throws FileNotFoundException{
        //reads file and puts each observation into original data arraylist
        //file format should be 1 dimensional observation values seperated by spaces or lines. No non-numerical characters
        //for an example see attatched file
        File names = new File(location);
        Scanner scnr = new Scanner(names);
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            String[] newline = line.split("\\s+");
            int count = 0;
            for(int i = 0;i < newline.length;i++){
                if(newline[i].length() == 0){
                }
                else{
                    OriginalData.add(i,Double.parseDouble(newline[i]));
                    count = count + 1;
                }
            }
            
        }
        //this will calculate the ordinary average of the data to test the population estimate against
        double newAverage = 0;
            for(int z= 0 ;z < OriginalData.size();z++){
                double item = (double) OriginalData.get(z);
                newAverage = newAverage + item;
            }
            newAverage = newAverage / OriginalData.size();
            System.out.println("the basic average of the data is "+ newAverage);
        return "ERROR";
    }
    public static void fillSample(ArrayList<Double> OriginalData,ArrayList<ArrayList<Double>> nest,int samplelength){
        for(int i = 0;i < nest.size();i++){
            for(int j = 0;j < samplelength;j++){
                Random rand = new Random();
                int index = rand.nextInt(OriginalData.size());
                nest.get(i).add(OriginalData.get(index));
            }
        }
        
    }
    public static void getAverage(ArrayList<ArrayList<Double>> nest,ArrayList<Double> Averages){
        for(int i = 0;i < nest.size();i++){
            double average = 0;
            for(int j = 0;j < nest.get(i).size();j++){
                average = average + nest.get(i).get(j);
                
            }
            average = average / nest.get(i).size();
            Averages.add(i,average);
            average = 0;
        }
        statistics stat=new statistics(Averages);
        double av=stat.mean(Averages);
        System.out.println("population estimate= "+av);
        System.out.println("Standard deviation of population estimates= "+stat.standardDeviation(av));
    }
    //calcualte confidence values with sorted sample averages
    public static void sort(ArrayList<Double> Averages){
        Collections.sort(Averages);
        int x = (int) (Averages.size() * .025);
        int y = (int) (Averages.size() * .9775);
        System.out.println("If the true population is normally distributed, 95% of the confidence intervals generated will contain the population mean which lies between "+Averages.get(x)+" and "+Averages.get(y)+" with 95 percent certainty");
        System.out.println("upper average= "+Averages.get(Averages.size()-1));
    }
}