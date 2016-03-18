package com.EIMA.outputs;

import javax.xml.bind.annotation.XmlRootElement;

import com.EIMA.models.EIMAAsset;
import com.EIMA.models.EIMACircle;
import com.EIMA.models.EIMAPolygon;;

@XmlRootElement

public class MapDataResult extends ResultBase {
	private EIMAAsset[] mapAssets;
	private EIMACircle[] mapCircles;
	private EIMAPolygon[] mapPolygons;

	public MapDataResult(boolean result, EIMAAsset[] mapAssets, EIMACircle[] mapCircles, EIMAPolygon[] mapPolygons) {
		super(result);
		this.mapAssets = mapAssets;
		this.mapCircles = mapCircles;
		this.mapPolygons = mapPolygons;
	}

	public EIMAAsset[] getMapAssets() {
		return mapAssets;
	}

	public void setMapAssets(EIMAAsset[] mapAssets) {
		this.mapAssets = mapAssets;
	}

	public EIMACircle[] getMapCircles() {
		return mapCircles;
	}

	public void setMapCircles(EIMACircle[] mapCircles) {
		this.mapCircles = mapCircles;
	}

	public EIMAPolygon[] getMapPolygons() {
		return mapPolygons;
	}

	public void EIMAPolygon(EIMAPolygon[] mapPolygons) {
		this.mapPolygons = mapPolygons;
	}

}
