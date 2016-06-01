package blackmediamanager.gui.util;

/**
 * Registry to access services provided for the gui components
 * 
 * @author Norskan
 *
 */

public class GuiServiceRegistry {
	public static GuiServiceRegistry instance = new GuiServiceRegistry();
	
	private final ViewLoader viewLoader = new ViewLoader();
	public ViewLoader getViewLoader() {		
		return viewLoader;
	}
}
