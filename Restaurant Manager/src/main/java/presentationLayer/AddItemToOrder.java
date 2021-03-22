package presentationLayer;

import businessLayer.*;
import businessLayer.MenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.*;

public class AddItemToOrder{

    JFrame Frame;
    private JButton finish, confirm;
    private JTable viewOrders;
    private JLabel name;
    private JComboBox bases;
    static ArrayList<MenuItem> items;

    public AddItemToOrder(Order ord, Restaurant rest){

        Frame = new JFrame ("Restaurant: New Menu Item");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 350);
        Frame.setLayout(null);

        name = new JLabel("Name:");
        name.setBounds(175, 100, 100, 20);
        Frame.add(name);

        bases = new JComboBox();
        bases.setBounds(300, 100, 250, 20);

        for(int i=0 ;i<rest.menu.size();i++){
            bases.addItem(rest.menu.get(i).getName());
        }

        Frame.add(bases);

        confirm = new JButton("Confirm");
        confirm.setBounds(200, 200, 100, 50);
        Frame.add(confirm);

        confirm.addActionListener(new ActionListener() {
            //@Override
            public  void actionPerformed(ActionEvent e) {
                items = new ArrayList<MenuItem>();
                for(int i=0;i<rest.menu.size();i++){
                    if(bases.getSelectedItem().equals(rest.menu.get(i).getName()))
                    {
                        items.add(rest.menu.get(i));

                        if(rest.menu.get(i).getType().compareTo("composite")==0)
                        {
                            Confirm c = new Confirm();
                            PopUp p = new PopUp();
                            c.addObserver(p);
                            c.itHappened(items);
                            ord.setCooked(false);
                        }
                    }
                }
            }
        });

        finish = new JButton("Finish");
        finish.setBounds(350, 200, 100, 50);
        Frame.add(finish);

        finish.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                rest.content.put(ord, items);
                Frame.dispose();
            }
        });

        Frame.setVisible(true);

    }

}