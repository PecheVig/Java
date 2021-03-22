package model;
public class Coada implements Comparable<Coada> {
    private int nrClienti;
    private int nrCoada;
    private int coadaGoala;
    private int timp;
    private int timpAsteptare;
    private int nrMaximClienti;
    private int timpMaxim;
    public Client[] clienti;

    public Coada(int nrMaximClienti, int timpMaxim,int nrCoada) {
        this.nrMaximClienti = nrMaximClienti;
        this.timpAsteptare = 0;
        this.nrClienti = 0;
        this.timp = 0;
        this.nrCoada=nrCoada;
        this.timpMaxim = timpMaxim;
        this.coadaGoala=1;
        clienti = new Client[nrMaximClienti];

        this.setClienti();
    }
    public void setClienti(){
        int k=0;
        while(k<nrMaximClienti){
            clienti[k]=new Client(0,0,2);
            k++;
        }
    }
    public int getNrClienti() {
        return nrClienti;
    }
    public int getNrCoada() {
        return nrCoada;
    }

    public int getCoadaGoala() {
        return coadaGoala;
    }

    public void setCoadaGoala(int coadaGoala) {
        this.coadaGoala = coadaGoala;
    }

    public int getTimpAsteptare() {
        return timpAsteptare;
    }
    public void addClientNull(Client c){
        int k=0;
        while(k<nrClienti){
            clienti[k] = c;
        }
    }
    public void addClient(model.Client c) {
        // System.out.println("Client "+c.getNrClient()+" "+c.getTimpIntrare());
        if (c.getTimpIntrare() == timp-1) {
            clienti[nrClienti] = c;
            nrClienti++;
            timpAsteptare += c.getDurataSerice();
        }
    }

    public void scoateClient() {
        int i = 0;
        while (i < nrClienti-1) {
            clienti[i] = clienti[i + 1];
            i++;
        }
        nrClienti -= 1;
    }

    public void cresteTimp() {
        if (timpMaxim > timp) {
            timp += 1;
            if(nrClienti!=0)
                if (clienti[0].getDurataSerice() > 1) {
                    clienti[0].setDurataSerice(clienti[0].getDurataSerice() - 1);
                } else {
                    scoateClient();
                }
            if (timpAsteptare > 0)
                timpAsteptare -= 1;
        }
    }

    public int compareTo(Coada m) {
        return ((Integer) (this.timpAsteptare)).compareTo(m.timpAsteptare);
    }
}
