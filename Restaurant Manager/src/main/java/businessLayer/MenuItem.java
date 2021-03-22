package businessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable{

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double price;
    private String type;

    public MenuItem(int id, String name, double price, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String toString(){
        return this.name +"....."+this.price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }




    public abstract double computePrice();


}
