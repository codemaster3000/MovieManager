package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author fabian
 */
public class ContentAboutController implements Initializable {

    @FXML
    private TextArea textAbout;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textAbout.setEditable(false);
        textAbout.setText("About");
    }    
    
}
