//
// (c) danielle drouin 2019 - github.com/dndrouin
//

package chat;

import javax.swing.*;
import java.awt.*;

public class Message{

    User sender;
    JPanel panel;

    Message(){
        sender = new User();
        panel = new JPanel();

    }

    Message(User user){
        sender = user;
        //string message is added to the jpanel
        panel = new JPanel();
}

    //TODO: prevent blank messages

    public static Message sendMessage(User sendr, String message){

        Message sending = new Message(sendr);
        JLabel messageJ = new JLabel(message);

        //put together entire user message panel
        sending.panel.setLayout(new BorderLayout());
        sending.panel.add(messageJ,BorderLayout.CENTER);
        sending.panel.setPreferredSize(new Dimension(500,200));

        //sending.panel is marked as invalid for some reason and therefore not showing up
        //System.out.println("Panel Validity: " + sending.panel.isValid());

        //testing to see if i can see the panel
        sending.panel.setBackground(Color.decode("#00aaff"));
        //confirmation that the method finished
        System.out.println("Message sent!");
        return sending;
    }

    //TODO: maybe create a refresh method?

}
