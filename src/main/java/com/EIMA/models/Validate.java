package com.EIMA.models;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Validate {
	private boolean result;
	public Validate(String json){
		if(isValid(json)){
			result = (true);
		} else result = (false);
	}

	private boolean isValid(String json) {
		return false;
	}

	public boolean isResult() {
		return result;
	}

}
