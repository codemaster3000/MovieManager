/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller.statisticsControllerHelper;

import database.domain.Genre;
import database.domain.Genrepos;
import database.domain.Movie;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Fabian Ladurner
 */
public class StatisticsControllerHelperPieChart {

    public StatisticsControllerHelperPieChart() {
    }

    public ObservableList<PieChart.Data> createPieChartMovieGenres(List<Movie> movies, List<Genrepos> genres) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < genres.size(); i++) {
            String genre = genres.get(i).getType();
            int counter = calcGenresFromAllMovies(genre, movies);
            double percent = calcPercent(movies, counter);
            if (counter > 0) {
                pieChartData.add(new PieChart.Data(genre + " (" + percent + "%)", counter));
            }
        }
        return pieChartData;
    }

    private int calcGenresFromAllMovies(String genre, List<Movie> movies) {
        int counter = 0;
        for (int i = 0; i < movies.size(); i++) {
            Set<Genre> set = movies.get(i).getGenres();
            for (Genre g : set) {
                if (g.getGenrepos().getType().equals(genre)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private double calcPercent(List<Movie> movies, int genreCounter) {

        // Count all
        int counter = 0;
        for (int i = 0; i < movies.size(); i++) {
            Set<Genre> set = movies.get(i).getGenres();
            for (Genre g : set) {
                counter++;
            }
        }

        double percent = 100 / counter * genreCounter;

        return percent;
    }

}