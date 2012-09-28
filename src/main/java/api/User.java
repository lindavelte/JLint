package api;

/**
 * @author linda.velte
 * 
 */
public interface User {

	public model.User createUser(String name, String login, String email);

	public model.User registerUser(String token, String password);

	public model.User login(String login, String password);

	public boolean logout(Long id);
}
