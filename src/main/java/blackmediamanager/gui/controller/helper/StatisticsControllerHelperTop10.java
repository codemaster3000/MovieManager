/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackmediamanager.gui.controller.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fabian Ladurner
 */
public class StatisticsControllerHelperTop10 {
    
    public StatisticsControllerHelperTop10() {

    }

    
    //  Lists for Comboboxes
    
    public ObservableList<String> createListComboBoxMovies() {
        ObservableList<String> top10Movies = FXCollections.observableArrayList(
                "Top 10 - Biggest Size",
                "Top 10 - Most Genres",
                "Top 10 - Newest Movies",
                "Top 10 - Oldest Movies",
                "Top 10 - ..."
        );
        return top10Movies;
    }
    
    public ObservableList<String> createListComboBoxDocumentaries() {
        ObservableList<String> top10Documentaries = FXCollections.observableArrayList(
                "Top 10 - Biggest Size",
                "Top 10 - Newest Documentaries",
                "Top 10 - Oldest Documentaries",
                "Top 10 - ..."
        );
        return top10Documentaries;
    }
    
    public ObservableList<String> createListComboBoxTVshows() {
        ObservableList<String> top10TVshows = FXCollections.observableArrayList(
                "Top 10 - Biggest Size",
                "Top 10 - Most Genres",
                "Top 10 - Newest TV-Shows",
                "Top 10 - Oldest TV-Shows",
                "Top 10 - ..."
        );
        return top10TVshows;
    }

    
    //  
    
}
