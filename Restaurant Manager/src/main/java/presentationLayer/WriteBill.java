package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.*;

import businessLayer.FuncUtil;
import businessLayer.Restaurant;

public class WriteBill extends JPanel {

    JFrame Frame;
    private JButton confirm, back, pick;
    private JLabel id, table, price, tableField, priceField;
    private JComboBox bases;
    static int index;

    public WriteBill(Restaurant rests){
        final Restaurant rest=rests;
        //Am de schimbat tot
        Frame = new JFrame ("Restaurant: Bill");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 750);
        Frame.setLayout(null);

        id = new JLabel("ID:");
        id.setBounds(175, 100, 200, 20);
        Frame.add(id);

        bases = new JComboBox();
        bases.setBounds(250, 100, 250, 20);
        for(int i=0 ;i<rest.orders.size();i++){
            if(!(rest.orders.get(i).isPaid()) && rest.orders.get(i).isCooked())
                bases.addItem(rest.orders.get(i).getId());
        }
        Frame.add(bases);

        pick = new JButton("Pick");
        pick.setBounds(550, 100, 75, 20);;
        Frame.add(pick);

        confirm = new JButton("Confirm");
        table = new JLabel("Table:");
        price = new JLabel("Price:");
        table.setBounds(175, 200, 200, 20);
        Frame.add(table);
        tableField = new JLabel("   ");
        tableField.setBounds(300, 200, 250, 20);
        Frame.add(tableField);


        price.setBounds(175, 250, 100, 20);
        Frame.add(price);
        priceField = new JLabel("   ");
        priceField.setBounds(300, 250, 250, 20);
        Frame.add(priceField);


        confirm.setBounds(250, 400, 100, 50);;
        Frame.add(confirm);



        back = new JButton("Back");
        back.setBounds(600, 650, 75, 25);
        Frame.add(back);

        back.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {
                WaiterGraphicalUserInterface d = new WaiterGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });
        confirm.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {
                rest.orders.get(index).setPaid(true);
                PrintWriter writer;
                try {
                    writer = new PrintWriter("bill_at_Table_"+rest.orders.get(index).getTable()+".txt", "UTF-8");
                    writer.println("Been paid: "+FuncUtil.computePrice(rest.content.get(rest.orders.get(index))));
                    writer.println("Order was: ");
                    for(int i=0; i<rest.content.get(rest.orders.get(index)).size(); i++){
                        writer.println(" "+rest.content.get(rest.orders.get(index)).get(i));
                    }
                    writer.println("Date : "+rest.orders.get(index).getDate());
                    writer.close();
                } catch (FileNotFoundException | UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                WaiterGraphicalUserInterface d = new WaiterGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });
        pick.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {
                for(int i=0;i<rest.orders.size();i++){
                    if(bases.getSelectedItem().equals(rest.orders.get(i).getId()))
                    {
                        tableField.setText(""+rest.orders.get(i).getTable());
                        priceField.setText(""+FuncUtil.computePrice(rest.content.get(rest.orders.get(i))));
                        index = i;
                    }
                }
            }
        });

        Frame.setVisible(true);
    }

}