package DataLayer;
import businessLayer.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RestaurantSerialization {

    public static  void serializeRestaurant(ArrayList<MenuItem> menu) {
        try {
            FileOutputStream fileOut = new FileOutputStream("restaurant.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(menu);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ArrayList<MenuItem> deserializeRestaurant() {
        ArrayList<MenuItem> menu = null;
        try {
            FileInputStream fileIn = new FileInputStream("restaurant.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            menu = (ArrayList<MenuItem>) in.readObject();
            in.close();
            fileIn.close();
        }catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return menu;

    }

}