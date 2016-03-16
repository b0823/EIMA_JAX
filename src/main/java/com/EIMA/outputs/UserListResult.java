package com.EIMA.outputs;

import javax.xml.bind.annotation.XmlRootElement;

import com.EIMA.models.EIMAUser;

@XmlRootElement

public class UserListResult extends ResultBase {
	private EIMAUser[] userList;

	public UserListResult(boolean result, EIMAUser[] userList) {
		super(result);
		this.userList = userList;
	}

	public EIMAUser[] getUserList() {
		return userList;
	}

	public void setUserList(EIMAUser[] userList) {
		this.userList = userList;
	}

}
