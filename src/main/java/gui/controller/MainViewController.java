package gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.ResourcePathResolver;
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
            contentPane.getChildren().addAll((SplitPane) FXMLLoader.load(ResourcePathResolver.resolveFxml("ContentMovie")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
    public void showMoviesPane(){
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll((SplitPane) FXMLLoader.load(ResourcePathResolver.resolveFxml("ContentMovie")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showTVPane(){
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll((SplitPane) FXMLLoader.load(ResourcePathResolver.resolveFxml("ContentTV")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    public void showDocuPane(){
        System.out.println("docu");
    }
    
    public void showReleasesPane(){
        System.out.println("Releases");
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll((AnchorPane) FXMLLoader.load(ResourcePathResolver.resolveFxml("ContentReleases")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showStatisticsPane(){
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll((AnchorPane) FXMLLoader.load(ResourcePathResolver.resolveFxml("ContentStatistics")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showSettingsPane(){
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll((AnchorPane) FXMLLoader.load(ResourcePathResolver.resolveFxml("ContentSettings")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
