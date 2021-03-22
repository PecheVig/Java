//import model.Sim;
package Main;
import model.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length!=2){
            System.out.println("Input incorect!\n");
            System.exit(-1);
        }
        Scanner in=new Scanner(new FileInputStream(args[0]));
        int[] a={3,2,10,2,5,2,4};
        int i=0;
        while(in.hasNext()){
            a[i]=in.nextInt();
            i++;
        }
        Thread s=new Sim(a[0],a[1],a[2],a[3],a[4],a[5],a[6],args[1]);

        //Thread s=new Sim(a[0],a[1],a[2],a[3],a[4],a[5],a[6],"out-test-1.txt");
    }
}
