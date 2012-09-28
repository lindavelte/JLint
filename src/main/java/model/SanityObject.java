package model;

/**
 * @author linda.velte
 * 
 */
public class SanityObject {

	private boolean sanity;
	private String message;

	public boolean isSanity() {
		return sanity;
	}

	public void setSanity(boolean sanity) {
		this.sanity = sanity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
