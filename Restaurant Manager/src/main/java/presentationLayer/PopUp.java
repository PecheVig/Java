package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PopUp implements Observer{

    public void update(Observable obj, Object arg)
    {
        final JFrame frame = new JFrame ("NEW ORDER!!!");

        JButton close= new JButton("Close");
        frame.setSize(400, 200);
        frame.setLayout(null);
        close.setBounds(150, 100, 100, 50);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });
        frame.add(close);
        frame.setVisible(true);
    }

}