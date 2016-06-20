package blackmediamanager.database.persistance.hibernate;

public class DatabaseConnectionProperties {
	private String url;
	private String user;
	private String password;
	private String driver;
	private String dialect;

	public DatabaseConnectionProperties(String url, String user, String password, String driver, String dialect) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.driver = driver;
		this.dialect = dialect;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getDriver() {
		return driver;
	}

	public String getDialect() {
		return dialect;
	}
}
