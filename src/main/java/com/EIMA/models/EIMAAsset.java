package com.EIMA.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EIMAAsset extends MapAsset {
	private String uid; //THIS is either their username or their randomly generated id.
	private String name;
	private GPSPosition position;
	private String unit;
	private String organization;
	private String status;
	private String unitType;
	private boolean isUser;

	public EIMAAsset() {
		super();
	}

	public EIMAAsset(String username, String name, String unit, GPS position, String organization, String status, String unitType,
			boolean isUser) {
		super();
		this.uid = username;
		this.position = position;
		this.name = name;
		this.unit = unit;
		this.organization = organization;
		this.status = status;
		this.unitType = unitType;
		this.isUser = isUser;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public String getUsername() {
		return uid;
	}

	public void setUsername(String username) {
		this.uid = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public GPSPosition getPosition() {
		return position;
	}

	public void setPosition(GPS position) {
		this.position = position;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

}
