package api;

import java.io.IOException;
import java.util.List;

import model.Profile;

import org.apache.http.ParseException;

import exceptions.JLintException;

/**
 * User Management
 * 
 * @author linda.velte
 * 
 */
public interface User {

	/**
	 * Creates a new application user
	 * 
	 * @param name
	 * @param login
	 * @param email
	 * @return created user
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public model.User createUser(String name, String login, String email) throws JLintException, ParseException, IOException;

	/**
	 * Registers a previously created user
	 * 
	 * @param token
	 *            - returned on creation of the user
	 * @param password
	 * @return registered user
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public model.User registerUser(String token, String password) throws JLintException, ParseException, IOException;

	/**
	 * User login
	 * 
	 * @param login
	 * @param password
	 * @return user and list of his permissions
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public model.User login(String login, String password) throws JLintException, ParseException, IOException;

	/**
	 * User logout
	 * 
	 * @param id
	 * @return true/false
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public boolean logout(Long id) throws JLintException, ParseException, IOException;

	/**
	 * Deletes an application user
	 * 
	 * @param id
	 * @return true/false
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public boolean deleteUser(Long id) throws JLintException, ParseException, IOException;

	/**
	 * Updates an application user
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param login
	 * @return updated user
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public model.User updateUser(Long id, String name, String email, String login) throws JLintException, ParseException, IOException;

	/**
	 * Returns a user by its id
	 * 
	 * @param id
	 * @return user
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public model.User showUser(Long id) throws JLintException, ParseException, IOException;

	/**
	 * Activates an deactivated application user
	 * 
	 * @param id
	 * @return user
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public model.User activateUser(Long id) throws JLintException, ParseException, IOException;

	/**
	 * Deactivates an active application user
	 * 
	 * @param id
	 * @return user
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public model.User deactivateUser(Long id) throws JLintException, ParseException, IOException;

	/**
	 * Reactivates a never registered application user
	 * 
	 * @param id
	 * @return user
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public model.User reactivateUser(Long id) throws JLintException, ParseException, IOException;

	/**
	 * Lists all application users
	 * 
	 * @return List<User> of application users
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public List<model.User> listUsers() throws JLintException, ParseException, IOException;

	/**
	 * Lists users permissions (profile/action/actionpoint)
	 * 
	 * @param id
	 * @return List<Profile> of users profiles
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public List<Profile> listUserPermissions(Long id) throws JLintException, ParseException, IOException;

	/**
	 * Lists users profiles
	 * 
	 * @param id
	 * @return List<Profiles> of users profiles
	 * @throws JLintException
	 * @throws ParseException
	 * @throws IOException
	 */
	public List<Profile> listUserProfiles(Long id) throws JLintException, ParseException, IOException;
}
