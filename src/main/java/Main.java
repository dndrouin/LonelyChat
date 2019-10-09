//
// (c) danielle drouin 2019 - github.com/dndrouin
//

import javafx.embed.swing.JFXPanel;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Main {

    //TODO: possibly figure out sockets and make lonelychat a little less lonely

    //classloader to make resource access easier
    static  ClassLoader cl = Main.class.getClassLoader();

    //the newest message's object
    static Message received;

    //newMsg true if previous message id != new message id, refreshBool true until user wants to exit program/log out
    static boolean newMsg = false, refreshBool = true;
    //previous message id tracker and character counter for entry textarea
    static int prevID = 0, count = -1;

    //main method
    public static void main(String[] args) {
        //start message ID at zero when program starts
        Message.id = 0;
        //first opens login window to receive username from user
         String username = loginWindow();
        //create new user object with username received
        User human = new User(username);
        //then opens main chat window with their username
        mainWindow(human);

        Window window = new Window();
        window.loggedIn(username);

    }


    //assembles and opens main chat window
    public static void mainWindow(User user1) {


        //now refresh entry textarea repeatedly to count number of letters input
        refresh(user1);



        //TODO: figure out what to do with old messages
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

        //TODO: change naming in this method to work with new window class

        public static void refresh(User user1){

            //keeps looking for new messages
            while (refreshBool) {
                //continuously update the letter count for entry
                count = (entry.getText()).length();
                countDisplay.setText(count + "/150");
                //if user enters over 150 characters, disable the button so they can't send the message and turn letter count red as warning
                //if user enters nothing, disable the button as well. no blank messages allowed!
                if (count > 150 || count == 0) {
                    if (count > 150) {
                        //only make countdisplay red if too many characters
                        countDisplay.setForeground(Color.RED);
                    }
                    b1.setEnabled(false);
                } else {
                    countDisplay.setForeground(null);
                    b1.setEnabled(true);
                }
                //if previous message id isn't the same as the current message id, a new message must have been sent
                if (prevID != Message.id) {
                    prevID = Message.id;
                    newMsg = true;
                }
                if (newMsg) {
                    //wipe the textarea after a message is sent
                    entry.setText("");
                    System.out.println("New message detected!");
                    displayNewMsg(p3, p3ScrollBar, placeholder);
                    System.out.println("Message should now be visible.");
                    //reset newMsg
                    newMsg = false;
                }

            }

            //when b1 is clicked, sends a message if number of characters doesnt go over the limit (150). if it does, turns
            //the text area red for a second to warn the user.
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    received = Message.sendMessage(user1, entry.getText());
                }
            });

            //when i2 is clicked, open the "about" popup
            i2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Popups.openAbout(mainFrame);
                }
            });

            //when i3 is clicked, open the "settings" popup
            i3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Popups.openSettings(user1);
                }
            });
    }


    public static void displayNewMsg(JPanel msgArea, JScrollBar msgAreaScrollBar, JPanel placeholder){
        //create a customizedPanel with message text and add it to the message display area
        msgArea.add(new customizedPanel(new Dimension(500,60), new BorderLayout(), received.text, received.sender.username));
        //recalculate subtract, which subtracts p3's placeholder's size by the height of the latest message to make room for new message
        int subtract = msgArea.getComponent(msgArea.getComponentCount() - 1).getSize().height;
        placeholder.setPreferredSize(new Dimension(msgArea.getWidth(), msgArea.getSize().height - subtract));
        msgArea.validate();
        //scrolls down to most recent message
        msgAreaScrollBar.setValue(msgAreaScrollBar.getMaximum());
    }


    //update icon based on what was chosen by user and stored in their user object
    public static void updateIcon(User user1) {
        String selectedPath = pathPurifier(cl.getResource(user1.icon).toString());
        //create 50x50 and 150x150 versions of image and save them for later use
        createIcons(selectedPath);
        System.out.println("Ok, did that.");
        circlefier(smallPath, largePath);
        System.out.println("And did that too.");
    }

    //mask the current icons small and large to a circle shape and use it to overwrite the non-circle ones
    public static void circlefier(String smallPath, String largePath){
        //turns the large icon image into a circle and saves it
        Circle iconCircle = new Circle(75,75,75, new ImagePattern(largeImg));
        //add circle to vbox and then take a snapshot of it
        VBox vb = new VBox();
        vb.getChildren().add(iconCircle);
        WritableImage snapshot = vb.snapshot(new SnapshotParameters(), null);
        //cast snapshot to renderedimage which can be turned into a javaxt image and then save it in the same path
        javaxt.io.Image toBeSaved = new javaxt.io.Image((RenderedImage) snapshot);
        toBeSaved.saveAs(largePath);

        //repeat for small icon image
        iconCircle = new Circle(25,25,25, new ImagePattern(smallImg));
        //add circle to vbox and then take a snapshot of it
        //empty the vbox first this time
        vb.getChildren().clear();
        vb.getChildren().add(iconCircle);
        snapshot = vb.snapshot(new SnapshotParameters(), null);
        //cast snapshot to renderedimage which can be turned into a javaxt image and then save it in the same path
        toBeSaved = new javaxt.io.Image((RenderedImage) snapshot);
        toBeSaved.saveAs(smallPath);
    }

    //takes in a path and removes any %20 "space" characters and the first 6 characters which are "file:/" that would cause things to freeze if left in the path
    private static String pathPurifier(String path){
        //replace %20 with spaces because my user file has one in it
        path = path.replaceAll("%20", " ");
        //remove first 6 letters in the file path because they are "file:/" and not needed
        path = path.substring(6);
        //return the improved path
        return path;
    }

    //creates the small and large versions of the icon files for use later in the program
    public static void createIcons(String p){
        //small and large versions of icons always will be saved in the same place
        //fix the terrible path formatting when using cl.getResource and save it as the path
        smallPath = pathPurifier(cl.getResource("icons/current/50.jpg").toString());
        largePath = pathPurifier(cl.getResource("icons/current/150.jpg").toString());

        //load user provided path as a javaxt image, which can resize and save more easily than other image types
        javaxt.io.Image icon = new javaxt.io.Image(pathPurifier(cl.getResource(p).toString()));

        System.out.println("Creating and saving icons.");

        //resize image to 150x150 and save it at largePath
        icon.resize(150,150);
        icon.saveAs(new File(largePath));
        //resize image to 50x50 and save it at smallPath
        icon.resize(50,50);
        icon.saveAs(new File(smallPath));

        //set smallimg and largeimg to these newly saved files
        smallImg = new Image("file:/" + smallPath);
        largeImg = new Image("file:/" + largePath);

        System.out.println("Created new icons successfully!");
    }

    //opens login window
    public static String loginWindow() {

        boolean inputConfirmed = false, spaceDetected = false;
        Dimension square = new Dimension(100, 100);

        //TODO: make the same layout but with less panels

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
        usernameLabel.setFont(Arial);

        //login button and username field
        JButton go = new JButton("Log In");
        go.setFont(Arial);
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

        username.setFont(Arial);

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
