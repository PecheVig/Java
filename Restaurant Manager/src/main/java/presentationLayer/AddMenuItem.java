package presentationLayer;

import businessLayer.*;
import DataLayer.RestaurantSerialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddMenuItem extends JPanel {

    JFrame Frame;
    private JButton confirm, back;
    private JRadioButton r1, r2;
    private JLabel name, price, stock;
    private JTextField nameField, priceField, stockField;

    public AddMenuItem (Restaurant rest){

        Frame = new JFrame ("Restaurant: New Menu Item");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 750);
        Frame.setLayout(null);

        name = new JLabel("Name:");
        name.setBounds(175, 100, 100, 20);
        Frame.add(name);
        nameField = new JTextField(10);
        nameField.setBounds(300, 100, 250, 20);
        Frame.add(nameField);

        price = new JLabel("Price:");
        price.setBounds(175, 150, 100, 20);
        Frame.add(price);
        priceField = new JTextField(10);
        priceField.setBounds(300, 150, 250, 20);
        Frame.add(priceField);

        stock = new JLabel("Stock:");
        stock.setBounds(175, 200, 100, 20);
        Frame.add(stock);
        stockField = new JTextField(10);
        stockField.setBounds(300, 200, 250, 20);
        Frame.add(stockField);

        r1 = new JRadioButton("Base");
        r2 = new JRadioButton("Composite");
        r1.setBounds(200,250,100,30);
        r2.setBounds(300,250,100,30);
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        Frame.add(r1);
        Frame.add(r2);


        confirm = new JButton("Confirm");
        confirm.setBounds(250, 300, 100, 50);
        Frame.add(confirm);

        confirm.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                if(r1.isSelected()){
                    BaseProduct b = new BaseProduct(rest.menu.size(), nameField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(stockField.getText()));
                    rest.menu.add(b);
                }
                else if(r2.isSelected()) {
                    CompositeProduct c = new CompositeProduct(rest.menu.size(), nameField.getText());
                    rest.menu.add(c);
                    AddBaseToComposite d = new AddBaseToComposite(c, rest);
                }
                RestaurantSerialization.serializeRestaurant(rest.menu);
                AdministratorGraphicalUserInterface d = new AdministratorGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });

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

        Frame.setVisible(true);
    }

}