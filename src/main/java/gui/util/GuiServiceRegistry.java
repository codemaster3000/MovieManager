package gui.util;

/**
 * Registry to access services provided for the gui components
 * 
 * @author Norskan
 *
 */

public class GuiServiceRegistry {
	public static GuiServiceRegistry instance = new GuiServiceRegistry();
	
	private ViewLoader viewLoader;
	public ViewLoader getViewLoader() {
		if(viewLoader == null) {
			viewLoader = new ViewLoader();
		}
		
		return viewLoader;
	}
}
