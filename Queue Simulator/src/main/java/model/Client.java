package model;
public class Client implements Comparable<Client> {
    private int nrClient;
    private int durataService;
    private int timpIntrare;
    private int introdus;

    public Client() {
    }

    public Client(int nrClient, int durataService, int timpIntrare) {
        this.nrClient = nrClient;
        this.durataService = durataService;
        this.timpIntrare = timpIntrare;
        this.introdus=0;
    }

    public int getIntrodus() {
        return introdus;
    }

    public void setIntrodus(int introdus) {
        this.introdus = introdus;
    }

    public int getDurataSerice() {
        return durataService;
    }

    public int getTimpIntrare() {
        return timpIntrare;
    }


    public int getNrClient() {
        return nrClient;
    }

    public void setDurataSerice(int durataSerice) {
        this.durataService = durataSerice;
    }

    public void setTimpIntrare(int timpIntrare) {
        this.timpIntrare = timpIntrare;
    }

    public void setNrClient(int nrClient) {
        this.nrClient = nrClient;
    }

    public int compareTo(Client m) {
        return ((Integer) (this.nrClient)).compareTo(m.nrClient);
    }
}