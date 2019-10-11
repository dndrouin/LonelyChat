//
// (c) danielle drouin 2019 - github.com/dndrouin
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Window {

    //TODO: put login window ♦in here as well

    //defining these so the class can open both login windows and main windows
    public static final int LOGIN = 0, MAIN = 1;

    //fonts
    static Font Arial = new Font("Arial", Font.PLAIN, 11);
    static Font ArialBold = new Font("Arial", Font.BOLD, 11);

    JFrame mainFrame;
    Vector<JPanel> panels;
    JTextArea entry;
    Vector<JButton> buttons;
    Vector<JMenuItem> menuItems;
    User currentUser;

    //default constructor, which is probably never getting used
    Window(){
        mainFrame = new JFrame();
        entry = new JTextArea();
        currentUser = new User();
    }

    Window(User u){
        entry = new JTextArea();
        currentUser = u;
        mainFrame = new JFrame("LonelyChat - " + u.username);
    }

    public void loggedIn(){

        boolean keepRefreshing = true, newMsg = false;
        int count = -1, prevID = 0;

        //fill panels vector with needed number of jpanels
        for(int i=0;i<4;i++) {
            panels.add(new JPanel());
            }

        //menu things
        JMenuBar mb1 = new JMenuBar();
        JMenu m1 = new JMenu("User");
        JMenu m2 = new JMenu("Program");

        //fill menuitem vector with needed number of menuitems
        //array to hold labels for menuitems
        String[] labels = {"Options", "About", "Settings", "Log Out", "Exit Program"};
        for(int i=0;i<3;i++) {
            menuItems.add(new JMenuItem());
            menuItems.get(i).setText(labels[i]);
        }

        //only need one button for now so just do that
        buttons.add(new JButton("Save"));

        //main window
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(500, 700));
        mainFrame.setResizable(false);
        mainFrame.pack();


        //creating the menubar and adding it to the window
        m2.add(menuItems.get(0));
        m2.add(menuItems.get(1));
        m1.add(menuItems.get(2));
        m1.add(menuItems.get(3));
        m1.add(menuItems.get(4));
        mb1.add(m1);
        mb1.add(m2);
        mainFrame.setJMenuBar(mb1);

        //entry textArea adjustments
        entry.setLineWrap(true);
        entry.setWrapStyleWord(true);

        //resizing components
        panels.get(0).setPreferredSize(new Dimension(500, 700));
        panels.get(1).setMaximumSize(new Dimension(500, 150));
        buttons.get(1).setPreferredSize(new Dimension(100, 100));
        mainFrame.add(panels.get(0));
        entry.setPreferredSize(new Dimension(340, 100));
        panels.get(0).setLayout(new BoxLayout(panels.get(0), BoxLayout.Y_AXIS));

        //setting margins for bottom box
        panels.get(1).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //putting components together with a scrollbar
        JScrollPane p3Scroll = new JScrollPane(panels.get(2));
        JScrollBar p3ScrollBar = new JScrollBar();
        p3ScrollBar = p3Scroll.getVerticalScrollBar();
        p3Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panels.get(2).setPreferredSize(new Dimension(500, 1000));
        panels.get(0).add(p3Scroll, BorderLayout.WEST);
        p3Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panels.get(0).add(panels.get(1));
        panels.get(1).setLayout(new BorderLayout());
        panels.get(1).add(buttons.get(0), BorderLayout.EAST);
        panels.get(1).add(entry, BorderLayout.WEST);
        JScrollPane scrollText = new JScrollPane(entry);
        panels.get(1).add(scrollText, BorderLayout.WEST);
        scrollText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panels.get(2).setLayout(new BoxLayout(panels.get(2), BoxLayout.Y_AXIS));

        //make window visible to user
        mainFrame.setVisible(true);

        //placeholder setup
        panels.get(3).setPreferredSize(panels.get(2).getSize());
        panels.get(3).setBackground(Color.WHITE);
        panels.get(2).add(panels.get(3));

        //countdisplay setup
        JLabel countDisplay = new JLabel(0  + "/150");
        panels.get(1).add(countDisplay, BorderLayout.SOUTH);

        //setting fonts
        entry.setFont(Arial);
        countDisplay.setFont(Arial);
        buttons.get(0).setFont(Arial);
        m1.setFont(Arial);
        m2.setFont(Arial);
        menuItems.get(0).setFont(Arial);
        menuItems.get(1).setFont(Arial);
        menuItems.get(2).setFont(Arial);
        menuItems.get(3).setFont(Arial);
        menuItems.get(4).setFont(Arial);


        while (keepRefreshing) {
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
                buttons.get(0).setEnabled(false);
            } else {
                countDisplay.setForeground(null);
                buttons.get(0).setEnabled(true);
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
                displayNewMsg();
                //scroll the scrollbar down all the way
                p3ScrollBar.setValue(p3ScrollBar.getMaximum());
                System.out.println("Message should now be visible.");
                //reset newMsg
                newMsg = false;
            }
        }

        //when b1 is clicked, sends a message if number of characters doesnt go over the limit (150). if it does, turns
        //the text area red for a second to warn the user.
        buttons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.received = Message.sendMessage(currentUser, entry.getText());
            }
        });

        //when i2 is clicked, open the "about" popup
        menuItems.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popups.openAbout(mainFrame);
            }
        });

        //when i3 is clicked, open the "settings" popup
        menuItems.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popups.openSettings(currentUser);
            }
        });

    }

    public void displayNewMsg(){
        //create a customizedPanel with message text and add it to the message display area
        panels.get(2).add(new customizedPanel(new Dimension(500,60), new BorderLayout(), Main.received.text, Main.received.sender.username));
        //recalculate subtract, which subtracts p3's placeholder's size by the height of the latest message to make room for new message
        int subtract = panels.get(2).getComponent(panels.get(2).getComponentCount() - 1).getSize().height;
        panels.get(3).setPreferredSize(new Dimension(panels.get(2).getWidth(), panels.get(2).getSize().height - subtract));
        panels.get(2).validate();
    }


}
