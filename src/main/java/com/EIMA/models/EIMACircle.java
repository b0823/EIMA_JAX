package com.EIMA.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EIMACircle {
	private GPSPosition center;
	private double radius; // in miles
	private String note;
	private String type;

	public EIMACircle(GPSPosition center, double radius, String note, String type) {
		super();
		this.center = center;
		this.radius = radius;
		this.note = note;
		this.type = type;
	}

	public EIMACircle() {
		super();
	}

	public GPSPosition getCenter() {
		return center;
	}

	public void setCenter(GPSPosition center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
