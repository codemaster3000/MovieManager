package gui.util;

import java.net.URL;

//TODO: evaluate if a singleton is better suited for this

public class ResourcePathResolver {
	
	private static final ClassLoader classLoader = ResourcePathResolver.class.getClassLoader();
	
	public static URL resolveResource(String prefix, String fileName, String fileEnding) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		sb.append(fileName);
		sb.append(fileEnding);
		
		return classLoader.getResource(sb.toString());
	}
	
	private static final String fxmlPrefix = "view/fxml/";
	private static final String fxmlFilePostfix = ".fxml";
	
	public static URL resolveFxml(String fileName) {
		return resolveResource(fxmlPrefix, fileName, fxmlFilePostfix);
	}
	
	private static final String cssPrefix = "view/style/";
	private static final String cssFilePostfix = ".css";
	
	public static URL resolveCssStyle(String fileName) {
		return resolveResource(cssPrefix, fileName, cssFilePostfix);
	}
	
	public enum ImageType {
	    PNG(".png");
	    

	    private final String text;

	    private ImageType(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}
	
	private static final String imagePrefix = "view/icon/";
	
	public static URL resovleIconPath(String fileName, ImageType imageType) {
		return resolveResource(imagePrefix, fileName, imageType.toString());
	}
}
