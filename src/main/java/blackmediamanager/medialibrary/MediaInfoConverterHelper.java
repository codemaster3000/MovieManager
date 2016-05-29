/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackmediamanager.medialibrary;

import static java.lang.Math.toIntExact;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.omertron.themoviedbapi.model.movie.MovieInfo;

import blackmediamanager.database.domain.Audiolinepos;
import blackmediamanager.database.domain.Genrepos;
import blackmediamanager.database.domain.Ownerpos;
import blackmediamanager.database.domain.Videoline;
import blackmediamanager.database.persistance.DBFacade;
import blackmediamanager.medialibrary.mediainfo.MediaInfoFile;

/**
 *
 * @author fabian
 */
public final class MediaInfoConverterHelper {

	private MediaInfoConverterHelper() {

	}

	public static Set<Audiolinepos> getAudiolines(MediaInfoFile inf) {

		Set<Audiolinepos> lines = new HashSet<>();

		for (int i = 0; i < inf.getAudioStreamCount(); i++) {
			Audiolinepos line = new Audiolinepos();
			line.setAudioLanguage(inf.getAudioLanguages().get(i));
			line.setAudioFormat(inf.getAudioFormats().get(i));
			line.setAudioBitrate(toIntExact(inf.getAudioBitratesKbps().get(i)));
			line.setAudioChannels(getAudioChannels(inf.getAudioChannels().get(i), line.getAudioFormat()));
			line.setDtsMod(false);

			lines.add(line);
		}

		return lines;
	}

	private static String getAudioChannels(int channels, String format) {
		if (channels == 2) {
			return "2.0";
		} else if (channels == 3) {
			return "2.1";
		} else if (channels == 6) {
			return "5.1";
		} else if (channels == 8) {
			if (format.equals("Dolby Atmos") || format.equals("DTS X")) {
				return "5.1.2";
			} else {
				return "7.1";
			}
		} else if (channels == 10) {
			return "7.1.2";
		} else {
			return null;
		}
	}

	public static Set<Genrepos> getGenres(MovieInfo movieInfo) {
		List<Genrepos> allGenres = DBFacade.instance.getAllGenrePoses();
		Set<Genrepos> genres = new HashSet<>();

		for (int i = 0; i < movieInfo.getGenreIds().size(); i++) {
			for (Genrepos pos : allGenres) {
				if (movieInfo.getGenreIds().get(i).equals(pos.getId())) {
					genres.add(pos);
					System.out.println(pos.getType());
				}
			}
		}

		return genres;
	}

	public static Set<Ownerpos> getOwner() {
		// TODO: read from some sort of property or current user
		Set<Ownerpos> owner = new HashSet<>();

		Ownerpos pos = new Ownerpos();
		pos.setOwnerName("Ladurner");

		owner.add(pos);

		return owner;
	}

	public static String getTagline(MovieInfo movieInfo) {
		if (movieInfo.getTagline() != null) {
			return movieInfo.getTagline();
		} else {
			return "";
		}
	}

	public static Videoline getVideoline(MediaInfoFile inf) {

		Videoline line = new Videoline();
		line.setAspectRatio(inf.getDisplayAspectRatio());
		line.setResolutionHeight(inf.getVideoResolutionHeight());
		line.setResolutionWidth(inf.getVideoResolutionWidth());
		line.setVideoBitrate((int) inf.getVideoOverallBitrateKbps());
		line.setVideoBitrateMode(inf.getVideoBitrateMode());
		line.setFramerate(inf.getVideoFramerate());

		return line;
	}

}
