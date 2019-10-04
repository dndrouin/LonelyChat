//
// (c) danielle drouin 2019 - github.com/dndrouin
//

package chat;

import javax.swing.*;
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
        boolean looping=true;
        AtomicBoolean newMsg = new AtomicBoolean(false);
        JFrame wFrame;
        JMenuBar wMenuBar;
        JPanel wPanel;
        JPanel insidePanel;
        JPanel txtPanel;
        JPanel messagesPanel;
        JMenu wMenu;
        JMenu oMenu;
        JMenuItem item1, item2;
        JButton butt;
        JTextArea entry;
        final String[] temp = new String[1];


        //opens the main chat client window after user entered username on login window
        wFrame = new JFrame("LonelyChat - " + user1.username);
        wFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wMenuBar = new JMenuBar();
        wPanel = new JPanel();
        txtPanel = new JPanel();
        insidePanel = new JPanel();
        entry = new JTextArea();
        messagesPanel = new JPanel();

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
        butt.setPreferredSize(new Dimension(100, 50));
        entry.setPreferredSize(new Dimension(285, 50));
        wFrame.setPreferredSize(new Dimension(400, 600));
        messagesPanel.setPreferredSize(new Dimension(285,500));
        wFrame.pack();
        wFrame.setResizable(false);

        //make entry deal with overflow correctly
        entry.setLineWrap(true);
        entry.setWrapStyleWord(true);


        //seeing what things are by what color they turn, remember to delete this
        /*
        wFrame.setBackground(Color.CYAN);
        insidePanel.setBackground(Color.MAGENTA);
        txtPanel.setBackground(Color.ORANGE);
        messagesPanel.setBackground(Color.RED);
*/
        txtPanel.add(messagesPanel, BorderLayout.CENTER);


        //sends a message
        butt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp[0] = entry.getText();
                received = Message.sendMessage(user1, temp[0]);
            }
        });

        //make them the same so when temp changes tempPrev can compare and conclude it has changed, thus flipping value of newMsg
        temp[0] = "null";
        String tempPrev = "null";

        //make messages panel display all messages vertically
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));

        //show window
        wFrame.setVisible(true);

        //basically equivalent to refreshing for new messages
        while(looping){
            if(!tempPrev.equals(temp[0])){
                tempPrev = temp[0];
                newMsg.set(true);
            }
            if(newMsg.get()){

                System.out.println("New message received!");
                messagesPanel.add(received.panel);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    //do nothing
                }

            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                //do nothing
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
