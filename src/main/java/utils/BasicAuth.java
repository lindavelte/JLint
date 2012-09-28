package utils;

import org.apache.commons.codec.binary.Base64;

/**
 * @author linda.velte
 * 
 */
public class BasicAuth {

	public static String getAuthorization(String username, String password) {
		String val = username + ":" + password;
		String auth = "Basic " + new String(new Base64().encode(val.getBytes()));
		return auth.trim();
	}
}
