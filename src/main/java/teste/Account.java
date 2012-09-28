package teste;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import model.User;
import core.Configuration;
import core.JLint;
import core.JLintFactory;

/**
 * @author linda.velte
 * 
 */
public class Account {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {

		// Configuration
		Configuration conf = new Configuration("a956D0QPsmEFpmK", "app", "application/json", "https://org.lagoon-ci.olimpo:9090/api/v1");

		JLint jlint = new JLintFactory(conf).getInstance();

		Logger log = Logger.getLogger("main-log");

		try {

			// Account Management

			System.out.println("***** Create User *****");
			User user = jlint.createUser("linda", "linda", "linda.velte@xlm.pt");
			System.out.println("Created user: " + user.getName());
			System.out.println("Token " + user.getToken());

			System.out.println("\nReactivate ");
			user = jlint.reactivateUser(user.getId());
			System.out.println("New Token: " + user.getToken());

			System.out.println("\n*** Register Created User");
			user = jlint.registerUser(user.getToken(), "linda");
			System.out.println("User registered");

			System.out.println("\n\nDeactivate ");
			user = jlint.deactivateUser(user.getId());
			System.out.println("User status: " + user.isEnable());

			System.out.println("\n\nActivate ");
			user = jlint.activateUser(user.getId());
			System.out.println("User status: " + user.isEnable());

			jlint.deleteUser(user.getId());

		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}
}
