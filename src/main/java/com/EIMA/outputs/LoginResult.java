package com.EIMA.outputs;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class LoginResult extends ResultBase {
	private String token;

	public LoginResult(boolean result, String token) {
		super(result);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
