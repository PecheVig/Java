package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import businessLayer.FuncUtil;
import businessLayer.Restaurant;

public class ChefGraphicalUserInterface extends JPanel {

    JFrame Frame;
    private JButton back;
    private JButton doneOrder;
    private JTable viewOrders;

    public ChefGraphicalUserInterface(Restaurant reste) {
        final Restaurant rest=reste;
        Frame = new JFrame ("Restaurant: Chef area");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 750);
        Frame.setLayout(null);

        doneOrder = new JButton("Confirm order");
        doneOrder.setBounds(75, 50, 150, 25);
        Frame.add(doneOrder);

        String[] col = {"ID", "Table","Date", "Price", "Served", "Paid"};
        String[][] data = new String[rest.orders.size()+1][6];

        data[0][2]= col[2];
        data[0][3]= col[3];
        data[0][4]= col[4];
        data[0][5]= col[5];
        data[0][0]= col[0];
        data[0][1]= col[1];
        for(int i = 0 ; i < rest.orders.size();i++){

            data[i+1][1]= ""+rest.orders.get(i).getTable();
            data[i+1][2]= ""+ rest.orders.get(i).getDate();
            data[i+1][0]= ""+rest.orders.get(i).getId();
            data[i+1][3]= ""+ FuncUtil.computePrice(rest.content.get(rest.orders.get(i)));
            if(rest.orders.get(i).isCooked())
                data[i+1][4]= "X";
            else
                data[i+1][4]="_";
            if(rest.orders.get(i).isPaid())
                data[i+1][5]= "X";
            else
                data[i+1][5]="_";
        }
        back = new JButton("Back");
        viewOrders = new JTable(data,col);
        viewOrders.setBounds(50, 100, 600, 500);
        //JScrollPane sp = new JScrollPane(viewOrders);
        Frame.add(viewOrders);


        back.setBounds(600, 650, 75, 25);
        Frame.add(back);

        back.addActionListener(new ActionListener() {
            //  @Override
            public  void actionPerformed(ActionEvent e) {
                LoginGraphicalUserInterface d = new LoginGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });
        doneOrder.addActionListener(new ActionListener() {
            //@Override
            public  void actionPerformed(ActionEvent e) {
                ConfirmOrder d = new ConfirmOrder(rest);
                Frame.dispose();
            }
        });

        Frame.setVisible(true);
    }

}