//
// (c) danielle drouin 2019 - github.com/dndrouin
//

import javax.swing.*;
import java.awt.*;

public class customizedPanel extends JPanel {

    customizedPanel(Dimension size, BorderLayout layout, String text, String username){

        //offsets to add padding to sides of messages
        JPanel offset = new JPanel();
        offset.setPreferredSize(new Dimension(40,50));
        JPanel offset2 = new JPanel();
        offset2.setPreferredSize(new Dimension(10,50));


        //every other message has a light gray background to increase readability
        if((Message.id)%2 == 0) {
            customizedPanel.super.setBackground(Color.decode("#e3e3e3"));
            offset.setBackground(Color.decode("#e3e3e3"));
            offset2.setBackground(Color.decode("#e3e3e3"));
        }
        else{
            customizedPanel.super.setBackground(Color.WHITE);
            offset.setBackground(Color.WHITE);
            offset2.setBackground(Color.WHITE);
        }


        //assemble sender jlabel which states who sent the message
        JLabel sender = new JLabel(" " + username);
        sender.setForeground(Color.decode("#00aaff"));
        sender.setFont(Window.ArialBold);

        //assemble txt jtextarea which contains actual message content
        JTextArea txt = new JTextArea(text);
        txt.setEditable(false);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setBackground(null);
        txt.setFont(Window.Arial);

        //setting up customizedPanel jPanel
        customizedPanel.super.setPreferredSize(size);
        customizedPanel.super.setMinimumSize(size);
        customizedPanel.super.setLayout(layout);
        customizedPanel.super.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //adding components to the customizedPanel jPanel
        customizedPanel.super.add(offset2, BorderLayout.WEST);
        customizedPanel.super.add(sender, BorderLayout.NORTH);
        customizedPanel.super.add(txt, BorderLayout.CENTER);
        customizedPanel.super.add(offset, BorderLayout.EAST);
    }

}
