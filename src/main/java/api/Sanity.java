package api;

import java.io.IOException;

import model.Environment;

import org.apache.http.ParseException;

import exceptions.JLintException;

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
	public model.SanityObject sanityCheck() throws JLintException, ParseException, IOException;

	/**
	 * Retrieves your Environment settings
	 * 
	 * @return Environment
	 */
	public Environment environmentSettings() throws JLintException, ParseException, IOException;
}
