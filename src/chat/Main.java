//
// (c) danielle drouin 2019 - github.com/dndrouin
//

package chat;

import javax.swing.*;
import java.awt.*;
import java.lang.String.*;

public class Main {

    public static void main(String[] args) {
        //first opens login window to receive username from user
        String username = loginWindow();
        //then opens main chat window with their username
        mainWindow(username);
    }


    //opens main chat window
    public static void mainWindow(String username){
        JFrame wFrame;
        JMenuBar wMenuBar;
        JPanel wPanel;
        JPanel insidePanel;
        JPanel txtPanel;
        JMenu wMenu;
        JMenu oMenu;
        JMenuItem item1, item2;
        JButton butt;
        JTextArea entry;


        //opens the main chat client window after user entered username on login window
        wFrame = new JFrame("LonelyChat - " + username);
        wMenuBar = new JMenuBar();
        wPanel = new JPanel();
        txtPanel = new JPanel();
        insidePanel = new JPanel();
        entry = new JTextArea();


        wPanel.setLayout(new BorderLayout());
        insidePanel.setLayout(new BorderLayout());
        txtPanel.setLayout(new BorderLayout());



        wMenu = new JMenu("Menu");
        oMenu = new JMenu("Program");
        item1 = new JMenuItem("Settings");
        item2 = new JMenuItem("Credits");
        butt = new JButton("Send");

        //creating the menubar and adding it to the window
        oMenu.add(item1);
        oMenu.add(item2);
        wMenuBar.add(wMenu);
        wMenuBar.add(oMenu);
        wFrame.setJMenuBar(wMenuBar);

        //adding panels and other components together and onto the window
        insidePanel.add(butt, BorderLayout.SOUTH);
        wPanel.add(insidePanel, BorderLayout.LINE_END);
        wFrame.add(wPanel);
        txtPanel.add(entry, BorderLayout.SOUTH);
        wPanel.add(txtPanel, BorderLayout.WEST);

        //adjusting sizing
        butt.setPreferredSize(new Dimension(100,50));
        entry.setPreferredSize(new Dimension(270,50));
        wFrame.setPreferredSize(new Dimension(400,600));
        wFrame.pack();
        wFrame.setResizable(false);

        //make entry deal with overflow correctly
        entry.setLineWrap(true);
        entry.setWrapStyleWord(true);


        //this will eventually send a message, but for now it's me messing with it
        butt.addActionListener(e -> System.out.println("Stop that!"));

        //show window
        wFrame.setVisible(true);

    }

    //opens login window
    public static String loginWindow(){

        boolean usernameEntered = false;
        Dimension square = new Dimension(100,100);

        //setting up the initial window
        JFrame window = new JFrame("LonelyChat - Log In");
        window.setResizable(false);
        window.setPreferredSize(new Dimension(500,300));
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //panels used for alignment
        JPanel panel = new JPanel();
        JPanel bottom = new JPanel();
        JPanel top = new JPanel();
        JPanel side1 = new JPanel();
        JPanel side2 = new JPanel();
        JPanel topSide1 = new JPanel();
        JPanel topSide2 = new JPanel();
        JPanel bottomBottom = new JPanel();
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel programName = new JLabel("LonelyChat");

        //login button and username field
        JButton go = new JButton("Log In");

        JTextField username = new JTextField();

        //final string array to hold text field's text when button is pressed
        final String[] usernameStr = {""};
        go.addActionListener(e -> usernameStr[0] = username.getText());

        //setting layout manager for panels
        panel.setLayout(new BorderLayout());
        bottom.setLayout(new BorderLayout());
        top.setLayout(new BorderLayout());
        topSide1.setLayout(new BorderLayout());
        topSide2.setLayout(new BorderLayout());

        //setting sizing of panels and button
        side1.setPreferredSize(square);
        side2.setPreferredSize(square);
        top.setPreferredSize(square);
        topSide1.setPreferredSize(square);
        topSide2.setPreferredSize(square);
        bottomBottom.setPreferredSize(new Dimension(100,125));
        go.setPreferredSize(new Dimension(100,25));

        //TODO: make the same layout but with less panels

        //adding all the panels together
        panel.add(side1, BorderLayout.WEST);
        panel.add(side2, BorderLayout.EAST);
        panel.add(top, BorderLayout.NORTH);
        bottom.add(usernameLabel, BorderLayout.NORTH);
        bottom.add(username, BorderLayout.CENTER);
        bottomBottom.add(go, BorderLayout.CENTER);
        bottom.add(bottomBottom, BorderLayout.SOUTH);
        panel.add(bottom, BorderLayout.CENTER);
        top.add(topSide1,BorderLayout.WEST);
        top.add(topSide2,BorderLayout.EAST);
        top.add(programName,BorderLayout.CENTER);
        window.add(panel);

        //aligning the big program name text in the center and making it a little less ugly
        programName.setHorizontalAlignment(JLabel.CENTER);
        programName.setVerticalAlignment(JLabel.CENTER);
        programName.setFont(new Font("Arial", Font.BOLD, 24));

        //it's showtime, baby!
        window.setVisible(true);

        while(!usernameEntered){
            //user remains stuck in this loop until they enter text and press the button
            System.out.println("In here!");
            //TODO:  && usernameStr[0].trim.length > 0 figure out why java.lang isn't showing up
            if(!usernameStr[0].isEmpty()){
                usernameEntered = true;
            }
        }

        //return the username obtained from the user so it can be used in the other window
        return usernameStr[0];
    }

}
