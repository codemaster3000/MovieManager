/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author fabian
 */
public class MainViewTestsController implements Initializable {

    @FXML private ToolBar topbar;
    @FXML private AnchorPane splitLeftSide;
    @FXML private AnchorPane splitRightSide;
    @FXML private SplitPane splitter;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //topbar.setPadding(new Insets(10, 10, 10, 0));
        // add queuefxml to mainwindow
        try {
            splitLeftSide.getChildren().addAll((VBox) FXMLLoader.load(this.getClass().getResource("fxml/ContentList.fxml")));
            splitRightSide.getChildren().addAll((VBox) FXMLLoader.load(this.getClass().getResource("fxml/ContentData.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
}
