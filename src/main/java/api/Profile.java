package api;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;

import exceptions.JLintException;

/**
 * Profile Management
 * 
 * @author linda.velte
 * 
 */
public interface Profile {

	/**
	 * Lists all enabled profiles
	 * 
	 * @return
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public List<model.Profile> listProfiles() throws JLintException, ParseException, IOException;
}
