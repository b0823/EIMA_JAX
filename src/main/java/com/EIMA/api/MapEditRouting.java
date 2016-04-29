package com.EIMA.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.EIMA.Database.DBQueries;
import com.EIMA.auth.AuthUtils;
import com.EIMA.models.EIMAAsset;
import com.EIMA.models.EIMACircle;
import com.EIMA.models.EIMAPolygon;
import com.EIMA.models.GPSPosition;
import com.EIMA.outputs.*;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/mapedit")
public class MapEditRouting {
	@POST
	@Path("/addassetinfo")
	public ResultBase addAssetInfo(JSONObject json) {
		try {
			String token = json.getString("token");
			System.out.println(json.toString());
			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)
					&& AuthUtils.hasPrivlege(token, AuthUtils.Privlege.mapEditor)) {

				EIMAAsset[] assets = DBQueries.getAssets(token);
				EIMACircle[] circ = DBQueries.getCircles(token);
				EIMAPolygon[] poly = DBQueries.getPolygons(token);

				EIMAAsset[] jsonAssets = convertToAssetsArray(json.getJSONArray("assets"));
				EIMACircle[] jsonCircles = convertToCirclesArray(json.getJSONArray("circles"));
				EIMAPolygon[] jsonPolygons = convertToPolygonsArray(json.getJSONArray("polygons"));

				// CHECK IF EXISTS IN DB NOT IN JSON -- DELETE
				for (int i = 0; i < poly.length; i++) {
					EIMAPolygon eimaPolygon = poly[i];
					if (!idExistsInPoly(eimaPolygon.getUid(), jsonPolygons)) {
						DBQueries.deleteMapAsset(eimaPolygon, token);
					}
				}
				for (int i = 0; i < circ.length; i++) {
					EIMACircle eimaCircle = circ[i];
					if (!idExistsInCirc(eimaCircle.getUid(), jsonCircles)) {
						DBQueries.deleteMapAsset(eimaCircle, token);
					}
				}
				for (int i = 0; i < assets.length; i++) {
					EIMAAsset eimaAsset = assets[i];
					if (!idExistsInAsset(eimaAsset.getUsername(), jsonAssets)) {
						DBQueries.deleteMapAsset(eimaAsset, token);
					}
				}
				// CHECK IF EXISTS IN JSON NOT IN DB -- ADD
				for (int i = 0; i < jsonPolygons.length; i++) {
					EIMAPolygon eimaPolygon = jsonPolygons[i];
					if (!idExistsInPoly(eimaPolygon.getUid(), poly)) {
						DBQueries.addMapAsset(eimaPolygon, token);
					} else {
						DBQueries.updateMapAsset(eimaPolygon, token);
					}
				}
				for (int i = 0; i < jsonCircles.length; i++) {
					EIMACircle eimaCircle = jsonCircles[i];
					if (!idExistsInCirc(eimaCircle.getUid(), circ)) {
						DBQueries.addMapAsset(eimaCircle, token);
					} else {
						DBQueries.updateMapAsset(eimaCircle, token);
					}
				}
				for (int i = 0; i < jsonAssets.length; i++) {
					EIMAAsset eimaAsset = jsonAssets[i];
					if (!idExistsInAsset(eimaAsset.getUsername(), assets)) {
						DBQueries.addMapAsset(eimaAsset, token);
					} else {
						DBQueries.updateMapAsset(eimaAsset, token);
					}
				}

				// DBQueries.updateMapAsset(null, token);
				// DBQueries.deleteMapAsset(null, token);
				// DBQueries.addMapAsset(null, token);

				return new ResultBase(true);

			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);

	}

	private boolean idExistsInPoly(String uid, EIMAPolygon[] poly) {
		for (int i = 0; i < poly.length; i++) {
			EIMAPolygon eimaPolygon = poly[i];
			if (eimaPolygon.getUid().equals(uid))
				return true;
		}
		return false;
	}

	private boolean idExistsInCirc(String uid, EIMACircle[] circ) {
		for (int i = 0; i < circ.length; i++) {
			EIMACircle eimaPolygon = circ[i];
			if (eimaPolygon.getUid().equals(uid))
				return true;
		}
		return false;
	}

	private boolean idExistsInAsset(String uid, EIMAAsset[] jsonAssets) {
		for (int i = 0; i < jsonAssets.length; i++) {
			EIMAAsset eimaPolygon = jsonAssets[i];
			if (eimaPolygon.getUsername().equals(uid))
				return true;
		}
		return false;
	}

	private EIMAPolygon[] convertToPolygonsArray(JSONArray jsonArray) {
		EIMAPolygon[] toReturn = new EIMAPolygon[jsonArray.length()];

		for (int i = 0; i < jsonArray.length(); i++) {
			EIMAPolygon toAdd = new EIMAPolygon();
			try {
				JSONObject object = jsonArray.getJSONObject(i);
				toAdd.setNote(object.getString("note"));
				toAdd.setType(object.getString("type"));
				toAdd.setUid(object.getString("uid"));

				JSONArray points = object.getJSONArray("coords");
				GPSPosition[] toAddPoints = new GPSPosition[points.length()];

				for (int j = 0; j < points.length(); j++) {
					JSONObject point = points.getJSONObject(i);
					toAddPoints[j] = new GPSPosition(point.getDouble("lat"), point.getDouble("long"));

				}
				toAdd.setPoints(toAddPoints);
				toReturn[i] = toAdd;

			} catch (JSONException e) {

			}

		}

		return toReturn;
	}

	private EIMAAsset[] convertToAssetsArray(JSONArray jsonArray) {
		EIMAAsset[] toReturn = new EIMAAsset[jsonArray.length()];

		for (int i = 0; i < jsonArray.length(); i++) {
			EIMAAsset toAdd = new EIMAAsset();
			try {
				JSONObject object = jsonArray.getJSONObject(i);

				toAdd.setName(object.getString("name"));
				toAdd.setUsername(object.getString("uid"));
				toAdd.setStatus(object.getString("status"));
				toAdd.setUnitType(object.getString("type"));
				toAdd.setUnit(object.getString("unit"));
				toAdd.setUser(false);
				toAdd.setOrganization(object.getString("organization"));

				JSONObject loc = object.getJSONObject("location");
				toAdd.setPosition(new GPSPosition(loc.getDouble("lat"), loc.getDouble("long")));

				toReturn[i] = toAdd;

			} catch (JSONException e) {

			}

		}

		return toReturn;
	}

	private EIMACircle[] convertToCirclesArray(JSONArray jsonArray) {
		EIMACircle[] toReturn = new EIMACircle[jsonArray.length()];

		for (int i = 0; i < jsonArray.length(); i++) {
			EIMACircle toAdd = new EIMACircle();
			try {
				JSONObject object = jsonArray.getJSONObject(i);

				toAdd.setNote(object.getString("note"));
				toAdd.setRadius(object.getDouble("radius"));
				toAdd.setType(object.getString("type"));
				toAdd.setUid(object.getString("uid"));

				JSONObject loc = object.getJSONObject("location");
				toAdd.setCenter(new GPSPosition(loc.getDouble("lat"), loc.getDouble("long")));

				toReturn[i] = toAdd;

			} catch (JSONException e) {

			}

		}

		return toReturn;
	}
}
