package model;

import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Sim extends Thread {
    private int nrClienti;
    private int timpAsteptatCuTotul;
    private int clientiIntrodusi;
    private int nrCozi;
    private int timp;
    private int timpSimulare;
    private int minArr;
    private int maxArr;
    private int minServ;
    private int maxServ;
    private boolean goOn;
    private String wr;
    private int ok;
    Thread[] thread;
    private ArrayList<Coada> listaCozi;

    public Sim(int nrClienti, int nrCozi, int timpSimulare, int minArr, int maxArr, int minServ, int maxServ, String wr) {
        this.nrClienti = nrClienti;
        this.nrCozi = nrCozi;
        this.timpSimulare = timpSimulare;
        this.minArr = minArr;
        this.timp = 0;
        this.maxArr = maxArr;
        this.minServ = minServ;
        this.maxServ = maxServ;
        this.timpAsteptatCuTotul= 0;
        this.clientiIntrodusi= 0;
        this.wr = wr;
        ok=0;
        this.thread=new Thread[nrClienti];
        listaCozi = new ArrayList<Coada>();
        goOn = true;
        this.setCozi();
        this.run();
    }


    private void setCozi() {
        int i = 0;
        while (i < nrCozi) {
            listaCozi.add(new Coada(nrClienti, timpSimulare, i));
            i++;
        }
        int j = 0;

    }

    public Client[] initializareClientiR() {
        Client[] cl = new Client[nrClienti];
        int k = 0;
        int i = 0;
        while (i < nrClienti) {
            cl[i] = new Client(0, 0, 0);
            cl[i].setNrClient(k);
            cl[i].setDurataSerice(new Random().nextInt((maxServ - minServ) + 1) + minServ);
            cl[i].setTimpIntrare(new Random().nextInt((maxArr - minArr) + 1) + minArr);
            i++;
            k++;
        }
        return cl;
    }

    public void verificaClienti(Client[] c) {
        goOn = false;
        int k = 0;
        if (timp < timpSimulare) {
            goOn = true;
        }
        for (Coada coad : listaCozi) {
            coad.cresteTimp();
        }
        int t = 0;
        while (t < nrClienti) {
            if (c[t].getTimpIntrare() == timp) {
                listaCozi.get(0).addClient(c[t]);
                if(thread[listaCozi.get(0).getNrCoada()].isAlive()!=true){
                    thread[listaCozi.get(0).getNrCoada()].start();
                    if(ok==1){
                        thread[listaCozi.get(0).getNrCoada()].run();
                    }
                }
                listaCozi.get(0).setCoadaGoala(0);
                timpAsteptatCuTotul+=c[t].getDurataSerice()+c[t].getTimpIntrare();
                clientiIntrodusi++;
                c[t].setIntrodus(1);
                Collections.sort(listaCozi);
            }
            t++;
        }
    }

    public void afisareClientiRamasi(FileWriter w, Client[] cl) {
        try { for (Client c : cl) {
            if (c.getIntrodus() == 0) {

                w.write("(" + c.getNrClient() + "," + c.getTimpIntrare() + "," + c.getDurataSerice() + ");");
            }
        }
            w.write("\n");
        }catch (Exception e) {
        }
    }

    public void run() {
        try {
            FileWriter f = new FileWriter(new File(wr));
            Client[] c = new Client[nrClienti];
            int k = 0;
            c = initializareClientiR();
            while (goOn) {
                verificaClienti(c);
                f.write("Time " + timp + "\n");
                f.write("Waiting clients: \n");
                afisareClientiRamasi(f, c);
                for (Coada d : listaCozi) {
                    f.write("Queue" + d.getNrCoada() + ":");
                    if (d.getNrClienti() != 0) {
                        for (k = 0; k < d.getNrClienti(); k++)
                            f.write("(" + d.clienti[k].getNrClient() + "," + d.clienti[k].getTimpIntrare() + "," + d.clienti[k].getDurataSerice() + ");");
                    }
                    f.write("\n");
                }
                /*if(clientiIntrodusi<nrClienti){
                    timpAsteptatCuTotul++;
                }*/
                timp++;
            }
            verificareListeSiClienti(f);
            //double ans=(double)timpAsteptatCuTotul/(double)Math.pow(2,nrCozi);
            //f.write("Average Waiting time: "+ans);
            f.close();
        } catch (Exception e) {
        }
    }
    public void verificareListeSiClienti(FileWriter w){

        try {
            int ok=1;
            for (Coada c : listaCozi) {
                if (c.getNrClienti() != 0) ok = 0;
                else{
                    thread[listaCozi.get(0).getNrCoada()].interrupt();
                }
            }
            if (nrClienti > clientiIntrodusi) ok = 0;
            if(ok==1){
                w.write("Average Waiting time: "+(double)timpAsteptatCuTotul/(double)nrCozi);
            }
        }catch (Exception e){

        }
    }

}
