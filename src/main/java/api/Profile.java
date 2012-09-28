package api;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;

import exceptions.JLintException;

/**
 * @author linda.velte
 * 
 */
public interface Profile {

	public List<model.Profile> listProfiles() throws JLintException, ParseException, IOException;
}
