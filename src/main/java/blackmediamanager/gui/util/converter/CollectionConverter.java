package blackmediamanager.gui.util.converter;

import java.util.Collection;

import blackmediamanager.database.domain.Genre;
import blackmediamanager.database.domain.Owner;

public class CollectionConverter {

	public static String getGenresToString(Collection<Genre> genres) {    
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		int genreCount = genres.size();

		for (Genre g : genres) {
			sb.append(g.getType());
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
			sb.append(o.getOwnerName());
			if (ownersCount > counter) {
				sb.append(", ");
			}
			counter++;
		}
		return sb.toString();
	}
}
