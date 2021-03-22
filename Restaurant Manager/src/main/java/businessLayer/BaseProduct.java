package businessLayer;

public class BaseProduct extends MenuItem {

    private static final long serialVersionUID = 1L;
    private int stock;

    public BaseProduct(int id, String name, double price, int stock){
        super(id, name, price, "base");
        this.stock = stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public double computePrice(){
        return this.getPrice();
    }

}


