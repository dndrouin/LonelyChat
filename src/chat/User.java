//
// (c) danielle drouin 2019 - github.com/dndrouin
//

package chat;

import javax.swing.*;
import java.awt.*;

public class User {
    public String username, realName, icon, location;

    //default constructor
    User(){
        username = "";
        realName = "";
        icon = "";
        location = "";
    }

    User(String enteredUsername){
        username = enteredUsername;
        realName = "Unknown";
        icon = "default.jpg";
        location = "Unknown";
    }

//TODO: prevent blank messages





}
