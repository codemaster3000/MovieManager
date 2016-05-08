package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author fabian
 */
public class ContentStatisticsController implements Initializable {

    @FXML
    private PieChart chart1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // pie chart test
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
             new PieChart.Data("Action", 20),
             new PieChart.Data("Horror", 12),
             new PieChart.Data("Comedy", 25),
             new PieChart.Data("Romance", 22),
             new PieChart.Data("SciFi", 30)
         );
        
        chart1.setData(pieChartData);
    }    
    
}
