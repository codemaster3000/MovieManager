package blackmediamanager.util;

public class ApplicationServices {
	public static ApplicationServices instance = new ApplicationServices();
	
	private ResourcePathResolver resourcePathResolver;
	public ResourcePathResolver getResourcePathResolver() {
		if(resourcePathResolver == null) {
			resourcePathResolver = new ResourcePathResolver();
		}
		
		return resourcePathResolver;
	}
}
