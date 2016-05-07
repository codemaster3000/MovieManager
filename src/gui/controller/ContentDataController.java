/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author fabian
 */
public class ContentDataController implements Initializable {

    @FXML
    private ImageView imgCover;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String imageSource = "https://image.tmdb.org/t/p/w396/i68IvNkUvqaKPY0UbadXcQ23aik.jpg";
        imgCover.setImage(new Image(imageSource));
    }    
    
}
