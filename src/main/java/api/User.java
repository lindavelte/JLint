package api;

import java.io.IOException;
import java.util.List;

import model.Profile;

import org.apache.http.ParseException;

import exceptions.JLintException;

/**
 * @author linda.velte
 * 
 */
public interface User {

	public model.User createUser(String name, String login, String email) throws JLintException, ParseException, IOException;

	public model.User registerUser(String token, String password) throws JLintException, ParseException, IOException;

	public model.User login(String login, String password) throws JLintException, ParseException, IOException;

	public boolean logout(Long id) throws JLintException, ParseException, IOException;

	public boolean deleteUser(Long id) throws JLintException, ParseException, IOException;

	public model.User updateUser(Long id, String name, String email, String login) throws JLintException, ParseException, IOException;

	public model.User showUser(Long id) throws JLintException, ParseException, IOException;

	public model.User activateUser(Long id) throws JLintException, ParseException, IOException;

	public model.User deactivateUser(Long id) throws JLintException, ParseException, IOException;

	public model.User reactivateUser(Long id) throws JLintException, ParseException, IOException;

	public List<model.User> listUsers() throws JLintException, ParseException, IOException;

	public List<Profile> listUserPermissions(Long id) throws JLintException, ParseException, IOException;

	public List<Profile> listUserProfiles(Long id) throws JLintException, ParseException, IOException;
}
