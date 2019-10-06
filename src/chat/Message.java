//
// (c) danielle drouin 2019 - github.com/dndrouin
//

package chat;

import javax.swing.*;
import java.awt.*;

public class Message{

    User sender;
    String text;
    static int id;

    Message(){
        sender = new User();
        text = "";
    }

    Message(User user, String msg){
        sender = user;
        text = msg;
}

    //TODO: prevent blank messages

    public static Message sendMessage(User sendr, String message){

        //increase message id by one when a new message is sent
        id++;
        Message sending = new Message(sendr, message);
        System.out.println("Message id " + id + " sent!");
        return sending;
    }

    //TODO: maybe create a refresh method?

}
