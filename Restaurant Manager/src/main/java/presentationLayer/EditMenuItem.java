package presentationLayer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import businessLayer.BaseProduct;
import businessLayer.Restaurant;
import DataLayer.RestaurantSerialization;

public class EditMenuItem extends JPanel {

    JFrame Frame;
    private JButton confirm, back, pick;
    private JLabel name, price, id, stock;
    private JTextField nameField, priceField, stockField;
    private JComboBox bases;
    private static int loc;

    public EditMenuItem(Restaurant rest){


        Frame = new JFrame ("Restaurant: Edit Menu Item");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 750);
        Frame.setLayout(null);
        bases = new JComboBox();
        id = new JLabel("Name:");
        id.setBounds(175, 100, 100, 20);
        Frame.add(id);

        bases.setBounds(250, 100, 250, 20);
        for(int i=0 ;i<rest.menu.size();i++){
            bases.addItem(rest.menu.get(i).getName());
        }
        Frame.add(bases);
        pick = new JButton("Pick");
        pick.setBounds(550, 100, 75, 20);;
        Frame.add(pick);


        name = new JLabel("Name:");
        name.setBounds(175, 200, 200, 20);
        Frame.add(name);
        nameField = new JTextField(10);
        nameField.setBounds(300, 200, 250, 20);
        Frame.add(nameField);

        price = new JLabel("Price:");
        price.setBounds(175, 250, 100, 20);
        Frame.add(price);
        priceField = new JTextField(10);
        priceField.setBounds(300, 250, 250, 20);
        Frame.add(priceField);

        stock = new JLabel("Stock:");
        stock.setBounds(175, 300, 100, 20);
        Frame.add(stock);
        stockField = new JTextField(10);
        stockField.setBounds(300, 300, 250, 20);
        Frame.add(stockField);


        confirm = new JButton("Confirm");
        confirm.setBounds(250, 400, 100, 50);;
        Frame.add(confirm);


        back = new JButton("Back");
        back.setBounds(600, 650, 75, 25);
        Frame.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                AdministratorGraphicalUserInterface d = new AdministratorGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                BaseProduct b = new BaseProduct(rest.menu.size(), nameField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(stockField.getText()));
                rest.menu.set(loc,b);
                RestaurantSerialization.serializeRestaurant(rest.menu);
                AdministratorGraphicalUserInterface d = new AdministratorGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });
        pick.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                for(int i=0;i<rest.menu.size();i++){
                    if(bases.getSelectedItem().equals(rest.menu.get(i).getName()))
                    {
                        nameField.setText(rest.menu.get(i).getName());
                        priceField.setText(""+rest.menu.get(i).getPrice());
                        stockField.setText(""+((BaseProduct)rest.menu.get(i)).getStock());
                        loc = i;
                    }
                }
            }
        });

        Frame.setVisible(true);
    }

}