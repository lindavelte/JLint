package core;

import model.Environment;
import model.User;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author linda.velte
 * 
 */
public class JLintImpl implements JLint {

	private Configuration conf;

	public JLintImpl(Configuration conf) {
		this.conf = conf;
	}

	public model.SanityObject sanityCheck() {

		HttpResponse response = HttpClientImpl.getInstance().getRequest("/sanitycheck", conf);
		HttpEntity entity = response.getEntity();

		ObjectMapper mapper = new ObjectMapper();
		String sanityresponse;
		try {
			sanityresponse = EntityUtils.toString(entity);
			model.SanityObject sanity = mapper.readValue(sanityresponse, model.SanityObject.class);
			return sanity;
		} catch (Exception e) {
			return null;
		}
	}

	public Environment environmentSettings() {

		HttpResponse response = HttpClientImpl.getInstance().getRequest("/", conf);
		HttpEntity entity = response.getEntity();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String sanityresponse = EntityUtils.toString(entity);

			Environment env = mapper.readValue(sanityresponse, Environment.class);
			return env;

		} catch (Exception e) {
			return null;
		}
	}

	public model.User createUser(String name, String login, String email) {

		String json = "{name:" + name + ",login:" + login + ",email:" + email + "}";

		HttpResponse response = HttpClientImpl.getInstance().postRequest("/users", conf, json);
		HttpEntity entity = response.getEntity();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String sanityresponse = EntityUtils.toString(entity);
			model.User user = mapper.readValue(sanityresponse, model.User.class);
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User registerUser(String token, String password) {
		String json = "{token:" + token + ",password:" + password + "}";

		HttpResponse response = HttpClientImpl.getInstance().postRequest("/users/register", conf, json);
		HttpEntity entity = response.getEntity();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String sanityresponse = EntityUtils.toString(entity);
			model.User user = mapper.readValue(sanityresponse, model.User.class);
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User login(String login, String password) {
		String json = "{login:" + login + ",password:" + password + "}";

		HttpResponse response = HttpClientImpl.getInstance().postRequest("/users/login", conf, json);
		HttpEntity entity = response.getEntity();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String sanityresponse = EntityUtils.toString(entity);
			model.User user = mapper.readValue(sanityresponse, model.User.class);
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean logout(Long id) {
		HttpResponse response = HttpClientImpl.getInstance().getRequest("/users/" + id + "/logout", conf);
		HttpEntity entity = response.getEntity();

		try {
			if (EntityUtils.toString(entity).contains("true")) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
