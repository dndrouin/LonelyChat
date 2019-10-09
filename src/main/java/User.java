//
// (c) danielle drouin 2019 - github.com/dndrouin
//

import javafx.scene.image.Image;

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
        icon = "icons/default.jpg";
        location = "Unknown";
    }

    public class Icon {

         Image smallImg, largeImg;
         String smallPath, largePath;

         Icon(){
             //TODO: finish this
             smallPath = pathPurifier(cl.getResource("icons/current/50.jpg").toString());
             largePath = pathPurifier(cl.getResource("icons/current/150.jpg").toString());

             smallImg = new Image("file:/" + smallPath);
             largeImg = new Image("file:/" + largePath);

         }


    }

}
