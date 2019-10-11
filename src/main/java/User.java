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

import java.awt.image.RenderedImage;
import java.io.File;

public class User {
    public String username, realName, icon, location;
    public User.Icon uicon;

    //default constructor, shouldn't ever be used
    User(){
        username = "";
        realName = "";
        icon = "";
        location = "";
        uicon = new Icon();
    }

    User(String enteredUsername){
        username = enteredUsername;
        realName = "Unknown";
        icon = "icons/default.jpg";
        location = "Unknown";
        uicon = new Icon();
    }

    public class Icon {

         Image smallImg, largeImg;
         String smallPath, largePath;

         Icon(){
             smallPath = pathPurifier(Main.cl.getResource("icons/current/50.jpg").toString());
             largePath = pathPurifier(Main.cl.getResource("icons/current/150.jpg").toString());
             //do not set smallImg or largeImg until circlefier is ran
         }


        //update icon based on what was chosen by user and stored in their user object
        //runs both createIcon and circlefier
        public void updateIcon() {

            //must do this first thing or runtimeexception "Internal graphics not initialized yet" happens
            JFXPanel g = new JFXPanel();

            String selectedPath = pathPurifier(Main.cl.getResource(User.this.icon).toString());
            //create 50x50 and 150x150 versions of image and save them for later use
            createIcons(selectedPath);
            circlefier(smallPath, largePath);
            System.out.println("Icon update finished.");
        }

        //mask the current icons small and large to a circle shape and use it to overwrite the non-circle ones
        public void circlefier(String smallPath, String largePath){
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

            //set smallimg and largeimg to be new circlefied images
            smallImg = new Image("file:/" + smallPath);
            largeImg = new Image("file:/" + largePath);

            System.out.println("Circlefied icons successfully!");
        }

        //takes in a path and removes any %20 "space" characters and the first 6 characters which are "file:/" that would cause things to freeze if left in the path
        public String pathPurifier(String path){
            //replace %20 with spaces because my user file has one in it
            path = path.replaceAll("%20", " ");
            //remove first 6 letters in the file path because they are "file:/" and not needed
            path = path.substring(6);
            //return the improved path
            return path;
        }

        //creates the small and large versions of the icon files for use later in the program
        public void createIcons(String p){
            //load user provided path as a javaxt image, which can resize and save more easily than other image types
            javaxt.io.Image icon = new javaxt.io.Image(p);

            System.out.println("Creating and saving icons.");

            //resize image to 150x150 and save it at largePath
            icon.resize(150,150);
            icon.saveAs(new File(largePath));

            //resize image to 50x50 and save it at smallPath
            icon.resize(50,50);
            icon.saveAs(new File(smallPath));

            //now create images to hold the files at the paths so circlefier can use them
            smallImg = new Image("file:/" + smallPath);
            largeImg = new Image("file:/" + largePath);

            System.out.println("Created new icons successfully!");
        }

    }

}
