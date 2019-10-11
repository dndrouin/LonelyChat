//
// (c) danielle drouin 2019 - github.com/dndrouin
//

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {

    //TODO: sockets, dealing with old messages, move login window into window class

    //classloader to make resource access easier
    static  ClassLoader cl = Main.class.getClassLoader();
    //the newest message's object
    static Message received;

    //main method
    public static void main(String[] args) {
        //start message ID at zero when program starts
        Message.id = 0;
        //first opens login window to receive username from user
         String username = loginWindow();
        //create new user object with username received
        User human = new User(username);
        //get settings 150x150 icon and msg 50x50 icon ready for use and saved in resources>icons>current folder
        human.uicon.updateIcon();
        //then opens main chat window with their username
        Window mw = new Window(human);

        mw.loggedIn();
    }

        /*entry.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                //when the user begins entering text in the entry textarea, begin refreshing looking for input
                refresh();
            }

            //does nothing but is required when adding a keylistener
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                refresh();
            }

            //does nothing but is required when adding a keylistener
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                refresh();
            }

        });
   }*/


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
        usernameLabel.setFont(Window.Arial);

        //login button and username field
        JButton go = new JButton("Log In");
        go.setFont(Window.Arial);
        window.getRootPane().setDefaultButton(go);

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

        username.setFont(Window.Arial);

        //set look and feel to make windows look better
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            //do nothing
        } catch (InstantiationException e) {
            //do nothing
        } catch (IllegalAccessException e) {
            //do nothing
        } catch (UnsupportedLookAndFeelException e) {
            //do nothing
        }

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
