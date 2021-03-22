package presentationLayer;
import businessLayer.*;
import DataLayer.RestaurantSerialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddBaseToComposite extends JPanel{

    JFrame Frame;
    private JButton finish, confirm;
    private JTable viewOrders;
    private JLabel name;
    private JComboBox bases;

    public AddBaseToComposite(CompositeProduct prods, Restaurant rests){
        final Restaurant rest=rests;
        final CompositeProduct prod=prods;
        Frame = new JFrame ("Restaurant: New Menu Item");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 350);
        Frame.setLayout(null);
        confirm = new JButton("Confirm");
        name = new JLabel("Name:");
        finish = new JButton("Finish");
        name.setBounds(175, 100, 100, 20);
        Frame.add(name);

        bases = new JComboBox();
        bases.setBounds(300, 100, 250, 20);

        for(int i=0 ;i<rest.menu.size();i++){
            if(rest.menu.get(i).getType().compareTo("base") == 0)
                bases.addItem(rest.menu.get(i).getName());
        }

        Frame.add(bases);


        confirm.setBounds(200, 200, 100, 50);
        Frame.add(confirm);




        finish.setBounds(350, 200, 100, 50);
        Frame.add(finish);

        finish.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {
                Frame.dispose();
            }
        });
        confirm.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {
                for(int i=0;i<rest.menu.size();i++){
                    if(bases.getSelectedItem().equals(rest.menu.get(i).getName()))
                    {
                        prod.addComposit(rest.menu.get(i));
                        prod.computePrice();
                        RestaurantSerialization.serializeRestaurant(rest.menu);
                    }
                }
            }
        });

        Frame.setVisible(true);

    }

}
