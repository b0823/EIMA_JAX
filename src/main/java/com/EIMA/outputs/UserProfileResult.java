package com.EIMA.outputs;

import javax.xml.bind.annotation.XmlRootElement;

import com.EIMA.models.EIMAProfile;

@XmlRootElement

public class UserProfileResult extends ResultBase {
	private EIMAProfile userProfile;

	public UserProfileResult(boolean result, EIMAProfile userProfile) {
		super(result);
		this.userProfile = userProfile;
	}

	public EIMAProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(EIMAProfile userProfile) {
		this.userProfile = userProfile;
	}

}
