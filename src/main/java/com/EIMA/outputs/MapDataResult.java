package com.EIMA.outputs;

import javax.xml.bind.annotation.XmlRootElement;

import com.EIMA.models.EIMAAsset;
import com.EIMA.models.EIMACircle;

@XmlRootElement

public class MapDataResult extends ResultBase {
	private EIMAAsset[] mapAssets;
	private EIMACircle[] mapCircles;
	private EIMACircle[] mapPolygons;

	public MapDataResult(boolean result, EIMAAsset[] mapAssets, EIMACircle[] mapCircles, EIMACircle[] mapPolygons) {
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

	public EIMACircle[] getMapPolygons() {
		return mapPolygons;
	}

	public void setMapPolygons(EIMACircle[] mapPolygons) {
		this.mapPolygons = mapPolygons;
	}

}
