package blackmediamanager.medialibrary;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FilenameUtils;

import blackmediamanager.application.helpers.AppConfig;
import blackmediamanager.medialibrary.util.StringHelper;

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

	public static ArrayList<String> getScannedFileList(ArrayList<String> fileNames, Path dir) throws IOException {
		Collection<String> videoExtensions = StringHelper.stringTokenize(AppConfig.instance.EXTENSIONS_VIDEO, ",");

		String ext = "";
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path path : stream) {
				if (path.toFile().isDirectory()) {
					getScannedFileList(fileNames, path);
				} else {
					ext = FilenameUtils.getExtension(path.getFileName().toString());
					if (videoExtensions.contains(ext)) {
						fileNames.add(path.toAbsolutePath().toString());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileNames;
	}
}
