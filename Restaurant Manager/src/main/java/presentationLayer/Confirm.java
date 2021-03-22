package presentationLayer;

import java.util.ArrayList;
import java.util.Observable;

import businessLayer.MenuItem;

public class Confirm extends Observable{

    public void itHappened(ArrayList<MenuItem> items){
        setChanged();
        notifyObservers(items);
    }

}