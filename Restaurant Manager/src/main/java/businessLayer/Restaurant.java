package businessLayer;

import DataLayer.*;

import java.io.Serializable;
import java.util.*;

public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    public ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    public ArrayList<Order> orders = new ArrayList<Order>();
    public HashMap<Order, ArrayList<MenuItem>> content;

    public  Restaurant() {
        this.content = new HashMap<Order, ArrayList<MenuItem>>();
        this.menu = RestaurantSerialization.deserializeRestaurant();
    }
}

