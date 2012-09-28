package model;

import java.util.List;
import java.util.Map;

/**
 * @author linda.velte
 * 
 */
public class Profile {
	private Long id;
	private String name;
	private String description;
	private Map<String, List<String>> actions;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, List<String>> getActions() {
		return actions;
	}

	public void setActions(Map<String, List<String>> actions) {
		this.actions = actions;
	}
}
