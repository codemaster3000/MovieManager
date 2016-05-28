/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import static java.lang.Math.toIntExact;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.omertron.themoviedbapi.model.movie.MovieInfo;

import database.domain.Audiolinepos;
import database.domain.Genrepos;
import database.domain.Ownerpos;
import database.domain.Videoline;
import database.persistance.DBFacade;
import services.mediainfo.MediaInfoGetter;

/**
 *
 * @author fabian
 */
public class MediaHandler {

	public MediaHandler() {
		// empty
	}

	private Integer id;
	private String audioLanguage;
	private int audioBitrate;
	private String audioFormat;
	private String audioChannels;
	private boolean dtsMod;

	public Set<Audiolinepos> setAudiolines(MediaInfoGetter inf) {

		Set<Audiolinepos> lines = new HashSet();

		for (int i = 0; i < inf.getAudioStreamCount(); i++) {
			Audiolinepos line = new Audiolinepos();
			line.setAudioLanguage(inf.getAudioLanguages().get(i));
			line.setAudioFormat(inf.getAudioFormats().get(i));
			line.setAudioBitrate(toIntExact(inf.getAudioBitratesKbps().get(i)));
			line.setAudioChannels(setAudioChannels(inf.getAudioChannels().get(i), line.getAudioFormat()));
			line.setDtsMod(false);

			lines.add(line);
		}

		return lines;
	}

	public String setAudioChannels(int channels, String format) {
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

	public Set<Genrepos> setGenres(MovieInfo movieInfo) {

		List<Genrepos> allGenres = DBFacade.instance.getAllGenrePoses();

		Set<Genrepos> genres = new HashSet();

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

	public Set<Ownerpos> setOwner() {

		Set<Ownerpos> owner = new HashSet();

		Ownerpos pos = new Ownerpos();
		pos.setOwnerName("Ladurner");

		owner.add(pos);

		return owner;
	}

	public String setTagline(MovieInfo movieInfo) {
		if (movieInfo.getTagline() != null) {
			return movieInfo.getTagline();
		} else {
			return "";
		}
	}

	public Videoline setVideoline(MediaInfoGetter inf) {

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
