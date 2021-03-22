package presentationLayer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import businessLayer.Restaurant;


public class LoginGraphicalUserInterface extends JPanel{

    JFrame Frame;
    private JButton waiter, admin, chef;
    private JLabel username, password;
    private JTextField usernameField, passwordField;

    public LoginGraphicalUserInterface(Restaurant reste) {
        final Restaurant rest=reste;
        Frame = new JFrame ("Restaurant login");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(700, 450);
        Frame.setLayout(null);

        username = new JLabel("Username:");
        username.setBounds(175, 100, 100, 20);
        Frame.add(username);
        usernameField = new JTextField(10);
        usernameField.setBounds(300, 100, 250, 20);
        Frame.add(usernameField);

        password = new JLabel("Password:");
        password.setBounds(175, 150, 100, 20);
        Frame.add(password);
        passwordField = new JTextField(10);
        passwordField.setBounds(300, 150, 250, 20);
        Frame.add(passwordField);

        admin = new JButton("Admin Login");
        admin.setBounds(100, 200, 200, 50);
        Frame.add(admin);




        waiter = new JButton("Waiter Login");
        waiter.setBounds(400, 200, 200, 50);
        Frame.add(waiter);



        chef = new JButton("Chef Login");
        chef.setBounds(250, 300, 200, 50);
        Frame.add(chef);

        chef.addActionListener(new ActionListener() {
            public  void actionPerformed(ActionEvent e) {
                if(usernameField.getText().compareTo("chef") == 0 && passwordField.getText().compareTo("chef") == 0){
                    ChefGraphicalUserInterface d = new ChefGraphicalUserInterface(rest);
                    Frame.dispose();
                }
            }
        });
        waiter.addActionListener(new ActionListener() {

            public  void actionPerformed(ActionEvent e) {
                if(usernameField.getText().compareTo("waiter") == 0 && passwordField.getText().compareTo("waiter") == 0){
                    WaiterGraphicalUserInterface d = new WaiterGraphicalUserInterface(rest);
                    Frame.dispose();
                }
            }
        });
        admin.addActionListener(new ActionListener() {
            public  void actionPerformed(ActionEvent e) {
                if(usernameField.getText().compareTo("admin") == 0 && passwordField.getText().compareTo("admin") == 0){
                    AdministratorGraphicalUserInterface d = new AdministratorGraphicalUserInterface(rest);
                    Frame.dispose();
                }
            }
        });


        Frame.setVisible(true);
    }
}
