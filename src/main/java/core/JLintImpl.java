package core;

import java.io.IOException;
import java.util.List;

import model.Environment;
import model.Profile;
import model.User;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import exceptions.JLintException;

/**
 * @author linda.velte
 * 
 */
public class JLintImpl implements JLint {

	private Configuration conf;

	public JLintImpl(Configuration conf) {
		this.conf = conf;
	}

	public model.SanityObject sanityCheck() throws JLintException, ParseException, IOException {

		HttpResponse response = HttpClientImpl.getInstance().getRequest("/sanitycheck", conf);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

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

	public Environment environmentSettings() throws JLintException, ParseException, IOException {

		HttpResponse response = HttpClientImpl.getInstance().getRequest("/", conf);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			String sanityresponse = EntityUtils.toString(entity);

			Environment env = mapper.readValue(sanityresponse, Environment.class);
			return env;

		} catch (Exception e) {
			return null;
		}
	}

	public model.User createUser(String name, String login, String email) throws JLintException, ParseException, IOException {

		String json = "{name:" + name + ",login:" + login + ",email:" + email + "}";

		HttpResponse response = HttpClientImpl.getInstance().postRequest("/users", conf, json);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

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

	public User registerUser(String token, String password) throws JLintException, ParseException, IOException {
		String json = "{token:" + token + ",password:" + password + "}";

		HttpResponse response = HttpClientImpl.getInstance().postRequest("/users/register", conf, json);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

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

	public User login(String login, String password) throws JLintException, ParseException, IOException {
		String json = "{login:" + login + ",password:" + password + "}";

		HttpResponse response = HttpClientImpl.getInstance().postRequest("/users/login", conf, json);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

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

	public boolean logout(Long id) throws JLintException, ParseException, IOException {
		HttpResponse response = HttpClientImpl.getInstance().getRequest("/users/" + id + "/logout", conf);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

		try {
			if (EntityUtils.toString(entity).contains("true")) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteUser(Long id) throws JLintException, ParseException, IOException {
		HttpResponse response = HttpClientImpl.getInstance().deleteRequest("/users/" + id, conf);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

		try {
			if (EntityUtils.toString(entity).contains("true")) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public User updateUser(Long id, String name, String email, String login) throws JLintException, ParseException, IOException {
		String json = "{name:" + name + ",login:" + login + ",email:" + email + "}";

		HttpResponse response = HttpClientImpl.getInstance().pustRequest("/users/" + id, conf, json);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

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

	public User showUser(Long id) throws JLintException, ParseException, IOException {
		HttpResponse response = HttpClientImpl.getInstance().getRequest("/users/" + id, conf);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			String sanityresponse = EntityUtils.toString(entity);

			User user = mapper.readValue(sanityresponse, User.class);
			return user;

		} catch (Exception e) {
			return null;
		}
	}

	public List<User> listUsers() throws JLintException, ParseException, IOException {
		HttpResponse response = HttpClientImpl.getInstance().getRequest("/users", conf);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			String sanityresponse = EntityUtils.toString(entity);

			List<User> users = mapper.readValue(sanityresponse, new TypeReference<List<User>>() {
			});
			return users;

		} catch (Exception e) {
			return null;
		}
	}

	public List<Profile> listUserPermissions(Long id) throws JLintException, ParseException, IOException {
		HttpResponse response = HttpClientImpl.getInstance().getRequest("/users/" + id + "/permissions", conf);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			String sanityresponse = EntityUtils.toString(entity);

			List<Profile> profiles = mapper.readValue(sanityresponse, new TypeReference<List<Profile>>() {
			});
			return profiles;

		} catch (Exception e) {
			return null;
		}
	}

	public List<Profile> listProfiles() throws JLintException, ParseException, IOException {
		HttpResponse response = HttpClientImpl.getInstance().getRequest("/profiles", conf);
		HttpEntity entity = response.getEntity();

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			throw new JLintException(EntityUtils.toString(entity));
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			String sanityresponse = EntityUtils.toString(entity);

			List<Profile> profiles = mapper.readValue(sanityresponse, new TypeReference<List<Profile>>() {
			});
			return profiles;

		} catch (Exception e) {
			return null;
		}
	}
}
