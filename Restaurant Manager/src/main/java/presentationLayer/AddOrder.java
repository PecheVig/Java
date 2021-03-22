package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import businessLayer.*;

public class AddOrder extends JPanel {

    JFrame Frame;
    private JButton confirm, back;
    private JLabel table, date;
    private JTextField tableField, dateField;

    public AddOrder (Restaurant rest){


        Frame = new JFrame ("Restaurant: New Order");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 750);
        Frame.setLayout(null);

        table = new JLabel("Table:");
        table.setBounds(175, 100, 100, 20);
        Frame.add(table);
        tableField = new JTextField(10);
        tableField.setBounds(300, 100, 250, 20);
        Frame.add(tableField);

        date = new JLabel("Date:");
        date.setBounds(175, 150, 100, 20);
        Frame.add(date);
        dateField = new JTextField(10);
        dateField.setBounds(300, 150, 250, 20);
        Frame.add(dateField);

        confirm = new JButton("Confirm");
        confirm.setBounds(250, 200, 100, 50);
        Frame.add(confirm);

        confirm.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                Order a = new Order(rest.orders.size(), dateField.getText(), Integer.parseInt(tableField.getText()));
                rest.orders.add(a);
                a.setCooked(true);
                AddItemToOrder d = new AddItemToOrder(a, rest);
            }
        });

        back = new JButton("Back");
        back.setBounds(600, 650, 75, 25);
        Frame.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                WaiterGraphicalUserInterface d = new WaiterGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });

        Frame.setVisible(true);
    }

}
