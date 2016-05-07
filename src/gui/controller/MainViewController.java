package gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author fabian
 */
public class MainViewController implements Initializable {

    @FXML private ToolBar topbar;
    @FXML private AnchorPane contentPane;

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            contentPane.getChildren().addAll((SplitPane) FXMLLoader.load(this.getClass().getResource("fxml/ContentMovie.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
    public void showMoviesPane(){
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll((SplitPane) FXMLLoader.load(this.getClass().getResource("fxml/ContentMovie.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showTVPane(){
        System.out.println("tv");
    }
        
    public void showDocuPane(){
        System.out.println("docu");
    }
    
    public void showReleasesPane(){
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll((AnchorPane) FXMLLoader.load(this.getClass().getResource("fxml/ContentReleases.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
