package api;

import model.Environment;

/**
 * @author linda.velte
 * 
 */

public interface Sanity {

	/**
	 * Check sanity of your environment
	 * 
	 * @return
	 */
	public model.SanityObject sanityCheck();

	/**
	 * Retrieves your Environment settings
	 * 
	 * @return Environment
	 */
	public Environment environmentSettings();
}
