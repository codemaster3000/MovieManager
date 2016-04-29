
package view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Fabian Ladurner
 */


public class MainViewController implements Initializable {


    @FXML
    public AnchorPane anchorPaneContent;
    @FXML
    public ImageView imageCover;
    
    
    // MENUs
    @FXML
    public AnchorPane paneMovieMenu;
    
    @FXML
    public BorderPane borderPaneForContent;
    
    @FXML
    public MovieContentController movieContentController;
    
    
    
    @FXML
    public void setPaneSelected(Event e) {
        ((Pane) e.getSource()).getStyleClass().add("paneSelected");
        ((Pane) e.getSource()).getChildren().get(0).getStyleClass().add("labelSelected");
        paneMovieMenu.setVisible(true);
    }
    
    public void setPaneUnselected(Event e) {
        ((Pane) e.getSource()).getStyleClass().remove("paneSelected");
        ((Pane) e.getSource()).getChildren().get(0).getStyleClass().remove("labelSelected");
        paneMovieMenu.setVisible(false);
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
    
    
}
