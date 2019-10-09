//
// (c) danielle drouin 2019 - github.com/dndrouin
//

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Window {

    //TODO: put login window in here as well

    //defining these so the class can open both login windows and main windows
    private static final int LOGIN = 0, MAIN = 1;

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
        mainFrame = new JFrame("LonelyChat - " + u.username);
        entry = new JTextArea();
        currentUser = u;
    }

    public void loggedIn(User user1){

        //fill panels vector with needed number of jpanels
        for(int i=0;i<3;i++) {
            panels.add(new JPanel());
            }

        //this panel is special and separated for later use
        JPanel placeholder = new JPanel();

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

        //get settings 150x150 icon and msg 50x50 icon ready for use and saved in resources>icons>current folder
        Main.createIcons(user1.icon);

        //placeholder setup
        placeholder.setPreferredSize(panels.get(2).getSize());
        placeholder.setBackground(Color.WHITE);
        panels.get(2).add(placeholder);

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

    }


}
