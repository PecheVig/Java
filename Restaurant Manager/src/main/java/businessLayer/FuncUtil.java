package businessLayer;

import java.util.ArrayList;

public class FuncUtil {

    public static double computePrice(ArrayList<MenuItem> items) {

        double price = 0;

        for (MenuItem m : items) {
            price += m.computePrice();
        }

        return price;
    }

}
