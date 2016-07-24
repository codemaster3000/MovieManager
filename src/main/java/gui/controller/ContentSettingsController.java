/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import application.helpers.AppConfig;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author fabian
 */
public class ContentSettingsController implements Initializable {

    @FXML
    private TextArea textInfo;
    
    @FXML
    private CheckBox checkWindowMaximized;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textInfo.setEditable(false);
        textInfo.setWrapText(true);
        textInfo.setText(AppConfig.getInstance().getPropertiesList());
        
        //checkWindowMaximized.setSelected(true);
    }    
    
    public void setupCheckWindowMaximized(){
        System.out.println(Boolean.toString(checkWindowMaximized.isSelected()));
        AppConfig.getInstance().setAppConfiguration("startup_windowmaximized", "true");
    }
}
