package chat;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

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


    public static Message sendMessage(User sendr, String message){

        Message sending = new Message(sendr);

        JPanel msgPanel = new JPanel();
        JPanel iconPanel = new JPanel();
        JLabel messageJ = new JLabel(message);

        msgPanel.setLayout(new BorderLayout());
        msgPanel.add(iconPanel,BorderLayout.WEST);
        msgPanel.add(messageJ,BorderLayout.EAST);
        iconPanel.setPreferredSize(new Dimension(75,100));
        msgPanel.setPreferredSize(new Dimension(200,100));
        msgPanel.setBackground(Color.decode("#7df59d"));
        iconPanel.setBackground(Color.decode("#75967e"));

        System.out.println("Message sent!");
        sending.panel = msgPanel;
        return sending;
    }

}
