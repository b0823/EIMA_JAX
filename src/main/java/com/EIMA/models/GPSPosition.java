package com.EIMA.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GPSPosition {
	private double Latitude;
	private double Longitude;

	public GPSPosition() {
		super();
	}

	public GPSPosition(double latitude, double longitude) {
		super();
		Latitude = latitude;
		Longitude = longitude;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

}
