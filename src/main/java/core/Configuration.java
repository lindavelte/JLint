package core;

/**
 * @author linda.velte
 * 
 */
public class Configuration {

	private String username;
	private String password;
	private String accept;
	private String baseURL;

	public Configuration(String username, String password, String accept, String baseURL) {
		this.username = username;
		this.password = password;
		this.accept = accept;
		this.baseURL = baseURL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

}
