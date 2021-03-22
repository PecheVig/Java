package presentationLayer;

import businessLayer.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import businessLayer.Restaurant;

public class WaiterGraphicalUserInterface extends JPanel {

    JFrame Frame;
    private JButton addOrder, makeBill, back;
    private JTable viewOrders;

    public WaiterGraphicalUserInterface(Restaurant rests) {
        final Restaurant rest=rests;
        Frame = new JFrame ("Restaurant: Waiter area");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 750);
        Frame.setLayout(null);

        addOrder = new JButton("Add Order");
        addOrder.setBounds(75, 50, 150, 25);
        Frame.add(addOrder);

        makeBill = new JButton("Write Bill");
        makeBill.setBounds(275, 50, 150, 25);
        Frame.add(makeBill);



        String[] col = {"ID", "Table","Date", "Price", "Served", "Paid"};
        String[][] data = new String[rest.orders.size()+1][6];
        data[0][0]= col[0];
        data[0][1]= col[1];
        data[0][2]= col[2];
        data[0][3]= col[3];
        data[0][4]= col[4];
        data[0][5]= col[5];
        for(int i = 0 ; i < rest.orders.size();i++){
            data[i+1][0]= ""+rest.orders.get(i).getId();
            data[i+1][1]= ""+rest.orders.get(i).getTable();
            data[i+1][2]= ""+ rest.orders.get(i).getDate();
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

        viewOrders = new JTable(data,col);
        viewOrders.setBounds(50, 100, 600, 500);
        //JScrollPane sp = new JScrollPane(viewOrders);
        Frame.add(viewOrders);

        back = new JButton("Back");
        back.setBounds(600, 650, 75, 25);
        Frame.add(back);



        back = new JButton("Back");
        back.setBounds(600, 650, 75, 25);
        Frame.add(back);

        back.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {
                LoginGraphicalUserInterface d = new LoginGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });
        back.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {
                LoginGraphicalUserInterface d = new LoginGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });
        makeBill.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {

                WriteBill d = new WriteBill(rest);
                Frame.dispose();
            }
        });
        addOrder.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {
                AddOrder d = new AddOrder(rest);
                Frame.dispose();
            }
        });

        Frame.setVisible(true);
    }

}
