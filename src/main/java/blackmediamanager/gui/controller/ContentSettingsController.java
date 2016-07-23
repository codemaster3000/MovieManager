/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackmediamanager.gui.controller;

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
public class ContentSettingsController implements Initializable {

    @FXML
    private TextArea textInfo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textInfo.setEditable(false);
        textInfo.setText("Settings");
    }    
    
}
