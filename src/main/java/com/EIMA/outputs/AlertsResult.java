package com.EIMA.outputs;

import javax.xml.bind.annotation.XmlRootElement;

import com.EIMA.models.EIMAAlert;

@XmlRootElement
public class AlertsResult extends ResultBase {
	private EIMAAlert[] alerts;

	public AlertsResult(boolean result, EIMAAlert[] alerts) {
		super(result);
		this.alerts = alerts;
	}

	public EIMAAlert[] getAlerts() {
		return alerts;
	}

	public void setAlerts(EIMAAlert[] alerts) {
		this.alerts = alerts;
	}

}
