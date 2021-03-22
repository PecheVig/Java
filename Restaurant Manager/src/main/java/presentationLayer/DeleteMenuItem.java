package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import businessLayer.Restaurant;
import DataLayer.RestaurantSerialization;

public class DeleteMenuItem extends JPanel {

    JFrame Frame;
    private JButton confirm, back, pick;
    private JLabel name, price, id, nameField, priceField;
    private JComboBox bases;

    public DeleteMenuItem(Restaurant rest){


        Frame = new JFrame ("Restaurant: Delete Menu Item");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 750);
        Frame.setLayout(null);

        bases = new JComboBox();
        id = new JLabel("Name:");
        id.setBounds(175, 100, 200, 20);
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
        nameField = new JLabel("   ");
        nameField.setBounds(300, 200, 250, 20);
        Frame.add(nameField);

        price = new JLabel("Price:");
        price.setBounds(175, 250, 100, 20);
        Frame.add(price);
        priceField = new JLabel("   ");
        priceField.setBounds(300, 250, 250, 20);
        Frame.add(priceField);

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
                for(int i=0;i<rest.menu.size();i++){
                    if(bases.getSelectedItem().equals(rest.menu.get(i).getName()))
                    {
                        rest.menu.remove(i);
                    }
                }
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
                    }
                }
            }
        });

        Frame.setVisible(true);
    }

}