//
// (c) danielle drouin 2019 - github.com/dndrouin
//

package chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.lang.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    static Message received;

    public static void main(String[] args) {
        //first opens login window to receive username from user
         String username = loginWindow();
        //create new user object with username received
        User human = new User(username);
        //then opens main chat window with their username
        mainWindow(human);
    }


    //opens main chat window
    public static void mainWindow(User user1) {
        //assorted variables - explained when used in code
        Dimension full = new Dimension(500,700);
        boolean looping=true;
        AtomicBoolean newMsg = new AtomicBoolean(false);
        final String[] temp = new String[1];

        //panels
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        //menu things
        JMenuBar mb1 = new JMenuBar();
        JMenu m1 = new JMenu("Menu");
        JMenu m2 = new JMenu("Program");
        JMenuItem i1 = new JMenuItem("Options");
        JMenuItem i2 = new JMenuItem("Credits");
        JMenuItem i3 = new JMenuItem("Settings");

        //main window
        JFrame mainFrame = new JFrame("LonelyChat - " + user1.username);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(full);
        mainFrame.setResizable(false);
        mainFrame.pack();

        //creating the menubar and adding it to the window
        m2.add(i1);
        m2.add(i2);
        m1.add(i3);
        mb1.add(m1);
        mb1.add(m2);
        mainFrame.setJMenuBar(mb1);

        //other various components
        JButton b1 = new JButton("Send");
        JTextArea entry = new JTextArea();
        entry.setLineWrap(true);
        entry.setWrapStyleWord(true);

        //resizing components
        p1.setPreferredSize(full);
        p2.setMaximumSize(new Dimension(500,150));
        p3.setMaximumSize(new Dimension(500,540));
        b1.setPreferredSize(new Dimension(100,100));
        mainFrame.add(p1);
        entry.setPreferredSize(new Dimension(340,100));
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));

        //setting margins for bottom box
        p2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //putting everything together with a scrollbar
        JScrollPane p3Scroll = new JScrollPane(p3);
        p1.add(p3Scroll, BorderLayout.WEST);
        p3Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        p1.add(p2);
        p2.setLayout(new BorderLayout());
        p2.add(b1, BorderLayout.EAST);
        p2.add(entry, BorderLayout.WEST);
        JScrollPane scrollText = new JScrollPane(entry);
        p2.add(scrollText, BorderLayout.WEST);
        scrollText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //make window visible to user
        mainFrame.setVisible(true);

        //when b1 is clicked, sends a message
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp[0] = entry.getText();
                received = Message.sendMessage(user1, temp[0]);
            }
        });

        //make them the same so when temp changes tempPrev can compare and conclude it has changed, thus flipping value of newMsg
        //if the user wants to send themselves the same message twice then i guess that's just too bad
        temp[0] = "null";
        String tempPrev = "null";

        //basically equivalent to refreshing for new messages
        while(looping){
            if(!tempPrev.equals(temp[0])){
                System.out.println("New message received!");
                tempPrev = temp[0];
                newMsg.set(true);
            }
            if(newMsg.get()){
            //wipe the textarea after a message is sent
                entry.setText("");
                //add message's panel to the panel where messages should display
                p3.add(received.panel,BorderLayout.SOUTH);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //do nothing, because it's working as intended
                }

            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                //do nothing, because it's working as intended
            }
            //reset newMsg
            newMsg.set(false);
        }

    }

    //opens login window
    public static String loginWindow() {

        boolean inputConfirmed = false, spaceDetected = false;
        Dimension square = new Dimension(100, 100);

        //setting up the initial window
        JFrame window = new JFrame("LonelyChat - Log In");
        window.setResizable(false);
        window.setPreferredSize(new Dimension(500, 300));
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
        bottomBottom.setPreferredSize(new Dimension(100, 125));
        go.setPreferredSize(new Dimension(100, 25));

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
        top.add(topSide1, BorderLayout.WEST);
        top.add(topSide2, BorderLayout.EAST);
        top.add(programName, BorderLayout.CENTER);
        window.add(panel);

        //aligning the big program name text in the center and making it a little less ugly
        programName.setHorizontalAlignment(JLabel.CENTER);
        programName.setVerticalAlignment(JLabel.CENTER);
        programName.setFont(new Font("Arial", Font.BOLD, 24));

        //it's showtime, baby!
        window.setVisible(true);


        //do not allow spaces in username input
        while(!inputConfirmed){
            //if usernameStr isn't empty, check all the chars in its string for whitespace
            //for some reason it doesn't work without this timeout, so i guess i'm leaving it there
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                //working as intended, don't care
            }
            if(!usernameStr[0].isEmpty()){

                for(int i=0;i<usernameStr[0].length();i++){
                    if(Character.isWhitespace(usernameStr[0].charAt(i))){
                        //if whitespace detected, spaceDetected becomes true
                        spaceDetected = true;
                    }

                }
                //spaceDetected warns user by for 1 second changing the text field background to red
                if(spaceDetected){
                    username.setBackground(Color.decode("#f55151"));
                    //make string empty so next time when it goes around the while loop it waits for new input
                    usernameStr[0] = "";
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        //do nothing because it's working as intended
                    }
                    username.setBackground(null);
                    //reset spaceDetected
                    spaceDetected = false;
                }
                else{
                    //valid entry received, can move on
                    inputConfirmed = true;
                }
            }
        }

            //close the login window
            window.dispose();
            //return the username obtained from the user so it can be used in the other window
            return usernameStr[0];


    }
}
