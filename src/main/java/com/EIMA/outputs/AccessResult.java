package com.EIMA.outputs;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class AccessResult extends ResultBase {
	private String accessLevel;

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public AccessResult(boolean result, String accessLevel) {
		super(result);
		this.accessLevel = accessLevel;
	}

}
