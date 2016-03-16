package com.EIMA.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class EIMAProfile {
	private String name;
	private String unit;
	private String organization;
	private String status;
	private String unitType;

	public EIMAProfile(String name, String unit, String organization, String status, String unitType) {
		super();
		this.name = name;
		this.unit = unit;
		this.organization = organization;
		this.status = status;
		this.unitType = unitType;
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

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

}
