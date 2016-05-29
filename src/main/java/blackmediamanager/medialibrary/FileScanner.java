package blackmediamanager.medialibrary;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class FileScanner {

	/*
	 * TODO:
	 * 
	 * - setaudioline methode prüfen obs passt --> DTS X etc - DTS-mod aus
	 * dateiname auslesen - TMDB Jahr isch zur zit vo da file.. eventl ändern uf
	 * TMDB-info - Genre ist immer null, warum?? genreID gibt es - tagline ist
	 * null?? - alle SET auf null überprüfen, sunsch "" --> sus gits
	 * nullpointer-exepitions - setOwner() wer es hinzufügt muss iirgndwo
	 * festglegt si als "eingeloggt" odr so - auf duplikat prüfen!!! neuen
	 * active(1) --> alte auf active(2) setzen
	 * 
	 * 
	 */

	private FileScanner() {

	}

	public static List<String> scanForMovieFiles(Path dir, Collection<String> videoExtensions) throws IOException {
		return scanForMovieFilesRec(new LinkedList<>(), dir, videoExtensions);
	}

	private static List<String> scanForMovieFilesRec(List<String> fileNames, Path dir,
			Collection<String> videoExtensions) throws IOException {
		String ext = "";
		DirectoryStream<Path> stream = null;
		try {
			stream = Files.newDirectoryStream(dir);
			for (Path path : stream) {
				if (path.toFile().isDirectory()) {
					scanForMovieFilesRec(fileNames, path, videoExtensions);
				} else {
					ext = FilenameUtils.getExtension(path.getFileName().toString());
					if (videoExtensions.contains(ext)) {
						fileNames.add(path.toAbsolutePath().toString());
					}
				}
			}
		} finally {
			stream.close();
		}

		return fileNames;
	}
}
