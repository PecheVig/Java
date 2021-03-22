package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import businessLayer.FuncUtil;
import businessLayer.Restaurant;

public class ConfirmOrder extends JPanel {

    JFrame Frame;
    private JButton confirm, back, pick;
    private JLabel id, table, price, tableField, priceField;
    private JComboBox bases;
    static int index;

    public ConfirmOrder(Restaurant rest){

        Frame = new JFrame ("Restaurant: Confirm cooked");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 750);
        Frame.setLayout(null);

        id = new JLabel("ID:");
        id.setBounds(175, 100, 200, 20);
        Frame.add(id);

        bases = new JComboBox();
        bases.setBounds(250, 100, 250, 20);
        for(int i=0 ;i<rest.orders.size();i++){
            if(!(rest.orders.get(i).isPaid()) && !(rest.orders.get(i).isCooked()))
                bases.addItem(rest.orders.get(i).getId());
        }
        Frame.add(bases);

        pick = new JButton("Pick");
        pick.setBounds(550, 100, 75, 20);;
        Frame.add(pick);
        pick.addActionListener(new ActionListener() {
            @Override
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
        table = new JLabel("Table:");
        table.setBounds(175, 200, 200, 20);
        Frame.add(table);
        tableField = new JLabel("   ");
        tableField.setBounds(300, 200, 250, 20);
        Frame.add(tableField);

        price = new JLabel("Price:");
        price.setBounds(175, 250, 100, 20);
        Frame.add(price);
        priceField = new JLabel("   ");
        priceField.setBounds(300, 250, 250, 20);
        Frame.add(priceField);

        confirm = new JButton("Confirm");
        confirm.setBounds(250, 400, 100, 50);;
        Frame.add(confirm);

        confirm.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                rest.orders.get(index).setCooked(true);
                ChefGraphicalUserInterface d = new ChefGraphicalUserInterface(rest);
                Frame.dispose();
            }

        });

        back = new JButton("Back");
        back.setBounds(600, 650, 75, 25);
        Frame.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                ChefGraphicalUserInterface d = new ChefGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });

        Frame.setVisible(true);
    }

}
