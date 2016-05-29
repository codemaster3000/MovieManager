/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackmediamanager.application.controller;

import java.util.LinkedList;
import java.util.List;

import blackmediamanager.database.dao.DataHandler;
import blackmediamanager.database.domain.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fabian Ladurner
 */
public class StatisticsCalculator {

	private final int MAX_VALUE = 10; // value of Top10
	private DataHandler dataHandler;

	public StatisticsCalculator() {
	}

	public void setDataHandler(DataHandler dh) {
		dataHandler = dh;
	}

	public ObservableList<String> calcTop10MovieBiggestSize() {
		List<Movie> movies = dataHandler.getAllMoviesOrderByFileSize();
		List top10List = new LinkedList();

		// counter for top 10, if movies smaller 10, use number of moviss
		int count = MAX_VALUE;
		if (movies.size() < count) {
			count = movies.size();
		}
		for (int i = 0; i < count; i++) {
			top10List.add((i + 1) + ".\t" + movies.get(i).getTmdbinfo().getTitle() + " (" + movies.get(i).getFileSize()
					+ ")");
		}
		return FXCollections.observableArrayList(top10List);
	}

	public ObservableList<String> calcTop10MovieOldestMovies() {
		List<Movie> movies = dataHandler.getAllMoviesOrderByOldest();
		List top10List = new LinkedList();

		// counter for top 10, if movies smaller 10, use number of moviss
		int count = MAX_VALUE;
		if (movies.size() < count) {
			count = movies.size();
		}
		for (int i = 0; i < count; i++) {
			top10List.add(
					(i + 1) + ".\t" + movies.get(i).getTmdbinfo().getTitle() + " (" + movies.get(i).getYear() + ")");
		}
		return FXCollections.observableArrayList(top10List);
	}

}
