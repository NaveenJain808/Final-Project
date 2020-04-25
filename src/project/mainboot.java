import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
public class mainboot{
    ArrayList<Double> OriginalData;
    ArrayList<Sample> cluster= new ArrayList<Sample>();
    public mainboot(){
        this.OriginalData = new ArrayList<Double>();
    }
    ArrayList<Double> getOriginalData(){
        return this.OriginalData;
    }    
    public static void main(String[] args)throws FileNotFoundException{
        Scanner console=new Scanner(System.in);
        System.out.println("enter file location e.g. C:/Users/redso/Desktop/test2.txt");
        String location=console.nextLine();
        System.out.println("enter a number of samples");
        int samplesize=console.nextInt();
        System.out.println("enter size of sample");
        int largess=console.nextInt();

        mainboot mean=new mainboot();
        ArrayList<Double> OriginalData=mean.getOriginalData();
        readFile(location, OriginalData);
        ArrayList<ArrayList<Double>> nest = new ArrayList<ArrayList<Double>>();
        for(int f=0;f<samplesize;f++){
            nest.add(new ArrayList<Double>());
        }
        for(int j=0;j<OriginalData.size();j++){
            
        }   

        fillSample(OriginalData, nest,largess);
        getAverage(nest);
    }
    public static String readFile(String location,ArrayList OriginalData) throws FileNotFoundException{
        //reads file and puts each sample into original data arraylist
        File names = new File(location);
        Scanner scnr = new Scanner(names);
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            String[] newline=line.split("\\s+");
            int count=0;
            for(int i=0;i<newline.length;i++){
                if(newline[i].length()==0){
                }
                else{
                    OriginalData.add(i,Double.parseDouble(newline[i]));
                    count=count+1;
                }
            }
        }
        return "ERROR";
    }
    public static void fillSample(ArrayList<Double> OriginalData,ArrayList<ArrayList<Double>> nest,int largess){
        System.out.println("nest size"+nest.size());
        for(int i=0;i<nest.size();i++){
            for(int j=0;j<largess;j++){
                Random rand=new Random();
                int index = rand.nextInt(OriginalData.size());
                nest.get(i).add(OriginalData.get(index));
            }
            
        }
    }
    public static void getAverage(ArrayList<ArrayList<Double>> nest){
        ArrayList<Double> Averages=new ArrayList<Double>();
        for(int i=0;i<nest.size();i++){
            double average=0;
            for(int j=0;j<nest.get(i).size();j++){
                average=average+nest.get(i).get(j);
                
            }
            average=average/nest.get(i).size();
            Averages.add(i,average);
            average=0;
        }
        double count=0;
        for(int z=0;z<Averages.size();z++){
            count=count+Averages.get(z);
        }
        double AverageOfAverages=count/Averages.size();
        System.out.println("average of averages= "+AverageOfAverages);
    }

}