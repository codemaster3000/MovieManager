package gui.util.converter;

import java.util.Collection;

import database.domain.Genre;
import database.domain.Owner;

public class CollectionConverter {

	public static String getGenresToString(Collection<Genre> genres) {
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		int genreCount = genres.size();

		for (Genre g : genres) {
			sb.append(g.getGenrepos().getType());
			if (genreCount > counter) {
				sb.append(", ");
			}
			counter++;
		}
		return sb.toString();
	}

	public static String getOwnersToString(Collection<Owner> owners) {
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		int ownersCount = owners.size();

		for (Owner o : owners) {
			sb.append(o.getOwnerpos().getOwnerName());
			if (ownersCount > counter) {
				sb.append(", ");
			}
			counter++;
		}
		return sb.toString();
	}
}
