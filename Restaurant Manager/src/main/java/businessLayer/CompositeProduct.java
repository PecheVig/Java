package businessLayer;

import java.util.*;

public class CompositeProduct extends MenuItem {

    private static final long serialVersionUID = 1L;
    private ArrayList<MenuItem> composit = new ArrayList<MenuItem>();

    public CompositeProduct(int id, String name){
        super(id, name, 0, "composite");
    }
    public double computePrice() {

        double price = 0;

        for (MenuItem m : composit) {
            price += m.computePrice();
        }
        this.setPrice(price);

        return price;
    }

    public void addComposit(MenuItem newItem) {
        composit.add(newItem);
    }



}