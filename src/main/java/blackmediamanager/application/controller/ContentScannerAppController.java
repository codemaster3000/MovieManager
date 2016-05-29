package blackmediamanager.application.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Set;

import com.omertron.themoviedbapi.MovieDbException;

import blackmediamanager.application.converter.MediaInfoToMovieConverter;
import blackmediamanager.database.dao.MovieDao;
import blackmediamanager.database.domain.Movie;
import blackmediamanager.medialibrary.FileScanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContentScannerAppController {

	private ObservableList<String> _scannedFiles;
	private ObservableList<String> _tmdbNotFoundFiles;

	public ContentScannerAppController() {
		_scannedFiles = FXCollections.observableList(new LinkedList<>());
		_tmdbNotFoundFiles = FXCollections.observableList(new LinkedList<>());
	}

	public void scanDirectory(Path dir, Set<String> extensions) throws IOException {
		_scannedFiles.clear();
		_scannedFiles.addAll(FileScanner.scanForMovieFiles(dir, extensions));
	}

	public interface UpdateProgressCallback {
		void invoke(int workDone, int maxWork);
	}

	public void addFilesToDatabase(UpdateProgressCallback callback) throws MovieDbException, IOException {
		MovieDao movieDao = new MovieDao();
		_tmdbNotFoundFiles.clear();

		if (_scannedFiles.size() > 0) {
			int i = 0;
			for (String filename : _scannedFiles) {
				File file = new File(filename);

				Movie foundMovie = MediaInfoToMovieConverter.convert(file);

				if (foundMovie == null) {
					_tmdbNotFoundFiles.add(file.getName());
				} else {
					movieDao.save(foundMovie);
				}

				callback.invoke(i++, _scannedFiles.size() - 1);
			}
		}
	}

	public ObservableList<String> getScannedFiles() {
		return _scannedFiles;
	}

	public ObservableList<String> getTmdbNotFoundFiles() {
		return _tmdbNotFoundFiles;
	}

}
