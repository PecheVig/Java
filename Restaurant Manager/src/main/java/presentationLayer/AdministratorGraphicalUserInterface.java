package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JTable;

import businessLayer.BaseProduct;
import businessLayer.Restaurant;


public class AdministratorGraphicalUserInterface extends JPanel {

    JFrame Frame;
    private JButton addMenuItem, deleteMenuItem, editMenuItem, back;
    private JTable viewMenuItems;

    public AdministratorGraphicalUserInterface(Restaurant rest) {

        Frame = new JFrame ("Restaurant: Admin area");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        addMenuItem = new JButton("Add Menu Item");
        addMenuItem.setBounds(75, 50, 150, 25);
        Frame.setSize(700, 750);
        Frame.setLayout(null);
        Frame.add(addMenuItem);


        editMenuItem = new JButton("Edit Menu Item");
        editMenuItem.setBounds(275, 50, 150, 25);
        Frame.add(editMenuItem);


        deleteMenuItem = new JButton("Delete Menu Item");
        deleteMenuItem.setBounds(475, 50, 150, 25);
        Frame.add(deleteMenuItem);


        String[] col = {"ID", "Name","Price", "Stock"};
        String[][] data = new String[rest.menu.size()+1][4];

        data[0][2]= col[2];
        data[0][3]= col[3];
        data[0][0]= col[0];
        data[0][1]= col[1];
        for(int i = 0 ; i < rest.menu.size();i++){

            data[i+1][1]= rest.menu.get(i).getName();
            data[i+1][2]= ""+ rest.menu.get(i).computePrice();
            data[i+1][0]= ""+rest.menu.get(i).getId();
            if(rest.menu.get(i).getType().compareTo("base") == 0)
                data[i+1][3]= ""+ ((BaseProduct)rest.menu.get(i)).getStock();
            else if(rest.menu.get(i).getType().compareTo("composite") == 0)
                data[i+1][3]= "-";
        }

        back = new JButton("Back");
        viewMenuItems = new JTable(data,col);
        viewMenuItems.setBounds(50, 100, 600, 500);
        //JScrollPane sp = new JScrollPane(viewMenuItems);
        Frame.add(viewMenuItems);

        back.setBounds(600, 650, 75, 25);
        Frame.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                LoginGraphicalUserInterface d = new LoginGraphicalUserInterface(rest);
                Frame.dispose();
            }
        });
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                DeleteMenuItem d = new DeleteMenuItem(rest);
                Frame.dispose();
            }
        });
        editMenuItem.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                EditMenuItem d = new EditMenuItem(rest);
                Frame.dispose();
            }
        });
        addMenuItem.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                AddMenuItem d = new AddMenuItem(rest);
                Frame.dispose();
            }
        });

        Frame.setVisible(true);
    }

}
