package com.EIMA.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class EIMAPolygon extends MapAsset{
	private String uid;
	private GPSPosition[] points;
	private String type;
	private String note;

	public EIMAPolygon(String uid, GPSPosition[] points, String type, String note) {
		super();
		this.setUid(uid);
		this.points = points;
		this.type = type;
		this.note = note;
	}

	public EIMAPolygon() {
		super();
	}

	public GPSPosition[] getPoints() {
		return points;
	}

	public void setPoints(GPSPosition[] points) {
		this.points = points;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
