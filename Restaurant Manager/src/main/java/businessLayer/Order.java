package businessLayer;
import java.io.Serializable;


public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String date;

    private boolean cooked;
    private boolean paid;
    private int table;

    public  Order(int id, String date, int table) {
        this.date = date;
        this.id = id;
        this.table = table;
        this.cooked = false;
        this.paid = false;
    }
    public  int hashCode() {
        int code = 0;
        code += id;
        code += table;
        int dateCode = 0;
        for (int i = 0; i < date.length(); i++) {
            dateCode += date.charAt(i);
        }

        code += dateCode;
        return code;
    }


    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (id != other.id)
            return false;
        if (table != other.table)
            return false;
        return true;
    }


    public boolean isCooked() {
        return cooked;
    }

    public  String getDate() {
        return date;
    }

    public  int getTable() {
        return table;
    }


    public void setCooked(boolean cooked) {
        this.cooked = cooked;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    public  int getId() {
        return id;
    }


}
