package model;

import org.junit.Assert;
class ClientTest {

    @org.junit.jupiter.api.Test
    void setDurataSerice() {
        Client c=new Client(0,0,0);
        c.setDurataSerice(2);
        Assert.assertEquals(c.getDurataSerice(),2);
    }

    @org.junit.jupiter.api.Test
    void setNrClient() {
        Client c=new Client(0,0,0);
        c.setNrClient(5);
        Assert.assertEquals(c.getNrClient(),5);
    }
}