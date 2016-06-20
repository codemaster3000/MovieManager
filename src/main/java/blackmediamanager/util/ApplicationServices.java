package blackmediamanager.util;

import blackmediamanager.database.dao.DaoRegistry;

//Todo(Norskan): add directory path resolver

public class ApplicationServices {
	public static ApplicationServices instance = new ApplicationServices();

	private final ResourcePathResolver resourcePathResolver = new ResourcePathResolver();

	public ResourcePathResolver getResourcePathResolver() {
		return resourcePathResolver;
	}

	private DaoRegistry remoteDatabaseRegistry;

	public void setRemoteDatabaseRegistry(DaoRegistry remoteDatabaseRegistry) {
		this.remoteDatabaseRegistry = remoteDatabaseRegistry;
	}

	public DaoRegistry getRemoteDatabaseRegistry() {
		return remoteDatabaseRegistry;
	}
}
