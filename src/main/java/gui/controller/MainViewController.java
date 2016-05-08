package gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import gui.util.GuiServiceRegistry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
            GuiServiceRegistry.instance.getViewLoader().LoadContentMovieView(contentPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
    public void showMoviesPane() throws IOException{
           GuiServiceRegistry.instance.getViewLoader().LoadContentMovieView(contentPane);
    }
    
    public void showTVPane(){
        System.out.println("tv");
    }
        
    public void showDocuPane(){
        System.out.println("docu");
    }
    
    public void showReleasesPane() throws IOException{
        GuiServiceRegistry.instance.getViewLoader().LoadContentReleaseView(contentPane);
    }
    
}
