package teste;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import model.Environment;
import model.Profile;
import model.SanityObject;
import model.User;
import core.Configuration;
import core.JLint;
import core.JLintFactory;

/**
 * @author linda.velte
 * 
 */
public class Main {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {

		// Configuration
		Configuration conf = new Configuration("a956D0QPsmEFpmK", "app", "application/json", "https://org.lagoon-ci.olimpo:9090/api/v1");

		JLint jlint = new JLintFactory(conf).getInstance();

		// Test Sanity
		SanityObject sanity = jlint.sanityCheck();
		System.out.println("/***** SanityCheck *****/");
		System.out.println("Sanity: " + sanity.isSanity());
		System.out.println("Message: " + sanity.getMessage());

		Environment env = jlint.environmentSettings();
		System.out.println("\n/***** My Environment *****/");
		System.out.println("Name: " + env.getName());
		System.out.println("Description: " + env.getDescription());
		System.out.println("From application: " + env.getApplication().getName());

		System.out.println("***** Create User *****");
		User user = jlint.createUser("linda", "linda", "linda.velte@xlm.pt");
		System.out.println("Created user: " + user.getName());

		System.out.println("\n*** Register Created User");
		user = jlint.registerUser(user.getToken(), "linda");
		System.out.println("User registered");

		System.out.println("\n\n Now we can Login!!");
		user = jlint.login("linda", "linda");
		System.out.println("User logged in! ");

		for (Profile p : user.getProfiles()) {
			for (String a : p.getActions().keySet()) {
				System.out.println("action: " + a);
				for (String ap : p.getActions().get(a)) {
					System.out.println("ap: " + ap);
				}
			}
		}

		System.out.println("\nAnd logout ");
		jlint.logout(user.getId());

		System.out.println("User logged out successfully!");
	}
}
