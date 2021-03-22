import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MainClass  {
    public static void main(String[] args) throws IOException {
        ArrayList<MonitoredData> myData=new ArrayList<MonitoredData>();
        myData=task1();
        task2(myData);
        task3(myData);
        task4(myData);
        task5(myData);
        task6(myData);
    }
    private static ArrayList<MonitoredData> task1(){
        PrintWriter writer;
        ArrayList<MonitoredData>myD=new ArrayList<MonitoredData>();
        try {
            writer = new PrintWriter("Task1" + ".txt", "UTF-8");
            String name="Activity.txt";
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            File fisier=new File(name);

            try (Stream<String> linesStream = Files.lines(fisier.toPath())) {
                linesStream.forEach(line -> {
                    String [] arrOfStr = line.split("		");
                    MonitoredData info = new MonitoredData(LocalDateTime.parse(arrOfStr[0], format), LocalDateTime.parse(arrOfStr[1], format), arrOfStr[2]);
                    myD.add(info);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.println("Task 1\nList created.");
            writer.close();
        }catch (Exception e){
            System.out.println("alabala");
        }
        return myD;
    }
    private static void task2(ArrayList<MonitoredData> myD){
        PrintWriter writer;
        try {
            writer = new PrintWriter("Task2" + ".txt", "UTF-8");
            writer.println("Task2\n: Days conted:" + myD.stream().map(a -> a.startTime.getDayOfYear()).distinct().count());
            writer.close();
        }catch(Exception e){
            System.out.println("alabala");
        }
    }
    private static void task3(ArrayList<MonitoredData> myD){
        PrintWriter writer;
        try {
            writer = new PrintWriter("Task3" + ".txt", "UTF-8");
            writer.println("Task3:");
            Map<String, Long> activities = myD.stream().collect(Collectors.groupingBy(a -> a.activity, Collectors.counting()));
            for (Map.Entry<String, Long> entry : activities.entrySet()) {
                writer.println("Activity " + entry.getKey() + " has been counted " + entry.getValue() + " times");
            }
            writer.close();
        }catch (Exception e){
            System.out.println("alabala");
        }
    }
    private static void task4(ArrayList<MonitoredData> myD) {
        PrintWriter writer;
        try {
            writer = new PrintWriter("Task4" + ".txt", "UTF-8");
            writer.println("Task4:");

            Map<Integer, Map<String, Long>> mp = myD.stream().collect(Collectors.groupingBy(a -> a.startTime.toLocalDate().getDayOfMonth(),
                    Collectors.groupingBy(b -> b.activity, Collectors.counting())));
            for (Map.Entry<Integer, Map<String, Long>> entry : mp.entrySet()) {
                writer.println("Day " + entry.getKey() + "	{");
                for (Map.Entry<String, Long> entr : entry.getValue().entrySet()) {
                    writer.println(entr.getKey() + " = " + entr.getValue());
                }
                writer.println("}");
            }
            writer.close();
        }catch (Exception e){
            System.out.println("alabala");
        }
    }
    private static void task5(ArrayList<MonitoredData> myD) {
        PrintWriter writer;
        try {
            writer = new PrintWriter("Task5" + ".txt", "UTF-8");
            writer.println("Task5.");
            Map<String, Long> t5 = myD.stream().collect(Collectors.groupingBy(a -> a.activity, Collectors.summingLong(a -> a.getDuration())));
            for (Map.Entry<String, Long> entry : t5.entrySet()) {
                writer.println("Activity " + entry.getKey() + " duration: " + entry.getValue());
            }
            writer.close();
        }
        catch (Exception e){     System.out.println("alabala");

        }
    }

    private static void task6(ArrayList<MonitoredData> myD){
        PrintWriter writer;
        try {
            writer = new PrintWriter("Task6" + ".txt", "UTF-8");
            writer.println("Task6.");
            Map<String, Long> m1 = myD.stream().collect(Collectors.groupingBy(a -> a.activity, Collectors.counting()));
            Map<String, Long> m2 = myD.stream().filter(a -> a.getDuration() < 5).collect(Collectors.groupingBy(a -> a.activity, Collectors.counting()));
            List<String> myList = new ArrayList<>();
            for (Map.Entry<String, Long> entry1 : m1.entrySet()) {
                for (Map.Entry<String, Long> entry2 : m2.entrySet()) {
                    if (entry1.getKey().equals(entry2.getKey())) {
                        if ((100 * entry2.getValue()) / entry1.getValue() > 90) {
                            myList.add(entry1.getKey());
                        }
                    }
                }
            }
            for (Object entry : myList) {
                writer.println(entry);
            }
            writer.close();
        }catch (Exception e){
            System.out.println("alabala");
        }
    }
}
