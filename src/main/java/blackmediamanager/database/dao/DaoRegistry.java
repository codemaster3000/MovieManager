package blackmediamanager.database.dao;

import blackmediamanager.database.persistance.DatabaseAccessFacade;

public class DaoRegistry {

	private GenreposDao genreposDao;
	private MovieDao movieDao;
	private OwnerposDao ownerposDao;
	private TMDBinfoDao tmdBinfoDao;
	private Videoline videoline;

	public DaoRegistry(DatabaseAccessFacade databaseAccessFacade) {
		genreposDao = new GenreposDao(databaseAccessFacade);
		movieDao = new MovieDao(databaseAccessFacade);
		ownerposDao = new OwnerposDao(databaseAccessFacade);
		tmdBinfoDao = new TMDBinfoDao(databaseAccessFacade);
		videoline = new Videoline(databaseAccessFacade);
	}

	public GenreposDao getGenreposDao() {
		return genreposDao;
	}

	public MovieDao getMovieDao() {
		return movieDao;
	}

	public OwnerposDao getOwnerposDao() {
		return ownerposDao;
	}

	public TMDBinfoDao getTmdBinfoDao() {
		return tmdBinfoDao;
	}

	public Videoline getVideoline() {
		return videoline;
	}

}
