package com.EIMA.outputs;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreatedIncidentResult extends ResultBase {
	private int incidentID;

	public CreatedIncidentResult(boolean result, int incidentID) {
		super(result);
		this.incidentID = incidentID;
	}

	public int getIncidentID() {
		return incidentID;
	}

	public void setIncidentID(int incidentID) {
		this.incidentID = incidentID;
	}

}
