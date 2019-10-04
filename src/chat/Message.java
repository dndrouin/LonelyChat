package chat;

import javax.swing.*;
import java.awt.*;

public class Message extends JPanel {

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

        JPanel msgPanel = new JPanel();
        JPanel iconPanel = new JPanel();
        JLabel messageJ = new JLabel(message);

        //put together entire user message panel
        msgPanel.setLayout(new BorderLayout());
        msgPanel.add(iconPanel,BorderLayout.WEST);
        msgPanel.add(messageJ,BorderLayout.CENTER);
        iconPanel.setPreferredSize(new Dimension(75,100));
        msgPanel.setPreferredSize(new Dimension(250,100));
        msgPanel.setBackground(Color.decode("#7df59d"));
        iconPanel.setBackground(Color.decode("#75967e"));

        System.out.println("Message sent!");
        sending.panel = msgPanel;
        return sending;
    }

    //TODO: maybe create a refresh method?

}
