//
// (c) danielle drouin 2019 - github.com/dndrouin
//

package chat;

import javax.swing.*;
import java.awt.*;

public class customizedPanel extends JPanel {

    customizedPanel(Dimension size, Dimension max, BorderLayout layout, String text){
        //for testing purposes, delete later
        if((Message.id)%2 == 0) {
            customizedPanel.super.setBackground(Color.LIGHT_GRAY);
        }
        else{
            customizedPanel.super.setBackground(Color.DARK_GRAY);
        }

        customizedPanel.super.setPreferredSize(size);
        customizedPanel.super.setMaximumSize(max);
        customizedPanel.super.setLayout(layout);
        customizedPanel.super.add(new JLabel(text));
    }

}
