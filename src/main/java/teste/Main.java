package teste;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import model.Environment;
import model.SanityObject;
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

		Logger log = Logger.getLogger("main-log");

		// Test Sanity
		SanityObject sanity;
		try {
			sanity = jlint.sanityCheck();
			System.out.println("/***** SanityCheck *****/");
			System.out.println("Sanity: " + sanity.isSanity());
			System.out.println("Message: " + sanity.getMessage());

			Environment env = jlint.environmentSettings();
			System.out.println("\n/***** My Environment *****/");
			System.out.println("Name: " + env.getName());
			System.out.println("Description: " + env.getDescription());
			System.out.println("From application: " + env.getApplication().getName());

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}
