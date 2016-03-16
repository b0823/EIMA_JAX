package com.EIMA.models;

public class EIMAUser extends EIMAProfile {
	private String privLevel;

	public EIMAUser(String name, String unit, String organization, String status, String unitType, String privLevel) {
		super(name, unit, organization, status, unitType);
		this.privLevel = privLevel;
	}

	public String getPrivLevel() {
		return privLevel;
	}

	public void setPrivLevel(String privLevel) {
		this.privLevel = privLevel;
	}

}
