package com.EIMA.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.EIMA.Database.DBQueries;
import com.EIMA.auth.AuthUtils;
import com.EIMA.models.*;
import com.EIMA.outputs.*;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/incidentdata")
public class IncidentDataRouting {
	@POST
	@Path("/mapdata")
	public ResultBase getMapData(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)
					&& AuthUtils.hasPrivlege(token, AuthUtils.Privlege.user)) {
				EIMAAsset[] assets = DBQueries.getAssets(token);
				EIMACircle[] circ = DBQueries.getCircles(token);
				EIMAPolygon[] poly = DBQueries.getPolygons(token);
				return new MapDataResult(true, assets, circ, poly);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Path("/alerts")
	public ResultBase getAlerts(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)
					&& AuthUtils.hasPrivlege(token, AuthUtils.Privlege.user)) {
				EIMAAlert[] alertLst = DBQueries.getAlerts(token);
				return new AlertsResult(true, alertLst);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Path("/accesslevel")
	public ResultBase getAccessLevel(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)) {
				AuthUtils.Privlege priv = DBQueries.getUserCurrIncidentPrivLevel(token);
				return new AccessResult(true, priv.name());
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Path("/location")
	public ResultBase location(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)) {
				
				double latit = json.getDouble("latit");
				double longit = json.getDouble("longit");

				
				GPSPosition location = new GPSPosition(latit, longit);
				DBQueries.updateUserLocation(token, location);
				return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}
}
