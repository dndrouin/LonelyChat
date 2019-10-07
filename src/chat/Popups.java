package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Popups {

    //is triggered by menu button About being clicked, opens the About popup
    public static void openAbout(JFrame window){
        //TODO: figure out how to center align the jlabel text
        //components
        JPopupMenu pm1 = new JPopupMenu("About");
        JPanel pm1p1 = new JPanel();
        JPanel pm1p1p1 = new JPanel();
        JLabel pm1p1j1 = new JLabel("LonelyChat");
        JLabel pm1p1j2 = new JLabel("(c) Danielle Drouin 2019");
        JButton pm1p1b1 = new JButton("My Website");

        //setting sizes
        pm1p1b1.setPreferredSize(new Dimension(100,50));
        pm1p1p1.setPreferredSize(new Dimension(100,100));
        pm1.setPreferredSize(new Dimension(150,100));

        //setting fonts
        pm1p1b1.setFont(Main.Arial);
        pm1p1j1.setFont(Main.ArialBold);
        pm1p1b1.setFont(Main.Arial);
        pm1p1.setLayout(new BoxLayout(pm1p1,BoxLayout.Y_AXIS));

        //assembling it all
        pm1p1.add(pm1p1j1);
        pm1p1.add(pm1p1j2);
        pm1p1.add(pm1p1p1);
        pm1p1.add(pm1p1b1);
        pm1.add(pm1p1);
        pm1.pack();

        //shows the popup window
        pm1.show(window, 100, 100);

        //when "My Website" button is pressed, open browser and go to my website's url
        pm1p1b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL("http://dndrouin.com").toURI());
                } catch (Exception exc){
                    //do nothing
                }
            }
        });
    }


    public static void openOptions(){

    }

}
