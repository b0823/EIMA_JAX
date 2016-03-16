package com.EIMA.outputs;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class ResultBase {
	private boolean result;

	public ResultBase(boolean result) {
		super();
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
