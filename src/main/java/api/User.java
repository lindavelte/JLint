package api;

import java.io.IOException;

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
}
