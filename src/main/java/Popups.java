//
// (c) danielle drouin 2019 - github.com/dndrouin
//

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
        pm1p1b1.setFont(Window.Arial);
        pm1p1j1.setFont(Window.ArialBold);
        pm1p1b1.setFont(Window.Arial);
        pm1p1.setLayout(new BoxLayout(pm1p1,BoxLayout.Y_AXIS));

        //assembling it all
        pm1p1.add(pm1p1j1);
        pm1p1.add(pm1p1j2);
        pm1p1.add(pm1p1p1);
        pm1p1.add(pm1p1b1);
        pm1.add(pm1p1);
        pm1.pack();

        //shows the popup window
        //pm1.show(window, 100, 100);
        pm1.setVisible(true);

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


    public static void openSettings(User user1){

        //settings window
        SpringLayout springLayout = new SpringLayout();

        //beginning setup of icons
        //panel container for icon in settings
        JPanel displayIcon = new JPanel();
        displayIcon.setPreferredSize(new Dimension(150,160));

        JPopupMenu pm2 = new JPopupMenu("Options");
        JPanel pm2p1 = new JPanel();

        pm2p1.setPreferredSize(new Dimension(300,150));
        pm2p1.setLayout(springLayout);

        //TODO: center things so they don't look so sloppy
        pm2.setPreferredSize(new Dimension(250,350));
        pm2.add(displayIcon);
        pm2.add(pm2p1);
        pm2.setVisible(true);

        //creates an organized springlayout in panel
        String[] ls = {"Username: ", "Real Name: ", "Location: ", "Icon: "};
        String[] iconarr = {"Default", "Cat", "Flowers", "Road"};
        JComboBox iconList = new JComboBox(iconarr);
        JTextField tf = new JTextField(10), tf1 = new JTextField(10), tf2 = new JTextField(10);
        for (int i=0;i<4;i++) {
            //place label first
            JLabel l1 = new JLabel(ls[i], JLabel.TRAILING);
            pm2p1.add(l1);
            //next place textfield or combobox depending on i's value
            if(i==0){
                //do not allow user to edit username
                //place username label and textfield
                tf.setText(user1.username);
                tf.setEditable(false);
                l1.setLabelFor(tf);
                pm2p1.add(tf);
            }
            else if(i==1){
                //place realname label and textfield
                tf1.setText(user1.realName);
                l1.setLabelFor(tf1);
                pm2p1.add(tf1);
            }
            else if(i==2){
                //place location label and textfield
                tf2.setText(user1.location);
                l1.setLabelFor(tf2);
                pm2p1.add(tf2);
            }
            else if(i==3) {
                //FIXME: popup disappears if you select thing in combobox
                //place jcombobox with icon image options in it
                iconList.setSelectedIndex(0);
                l1.setLabelFor(iconList);
                pm2p1.add(iconList);
            }
        }
        JPanel pm2p2 = new JPanel();
        JButton b2 = new JButton("Save Changes");
        pm2p2.add(b2);
        pm2.add(pm2p2);
        SpringUtilities.makeCompactGrid(pm2p1, 4, 2, 6, 20, 6, 6);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //press save in settings and it saves the things to your profile aka your user object
                user1.realName = tf1.getText();
                user1.location = tf2.getText();

                //changes user's icon url based on what index was chosen in the iconlist
                if(iconList.getSelectedIndex() == 0) {
                    //default icon chosen
                    user1.icon = "/icon/default.jpg";
                }
                else if(iconList.getSelectedIndex() == 1){
                    //cat icon chosen
                    user1.icon = "/icon/cat.jpg";
                }
                else if(iconList.getSelectedIndex() == 2){
                    //flowers icon chosen
                    user1.icon = "/icon/flowers.jpg";
                }
                else if(iconList.getSelectedIndex() == 3){
                    //road icon chosen
                    user1.icon = "/icon/road.jpg";
                }
                //updates the icon itself
                user1.uicon.updateIcon();
            }
        });

    }

}
