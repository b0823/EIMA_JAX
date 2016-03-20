package com.EIMA.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.EIMA.Database.DBQueries;
import com.EIMA.models.EIMAAsset;
import com.EIMA.models.EIMACircle;
import com.EIMA.models.EIMAPolygon;
import com.EIMA.outputs.CreateIncidentResult;
import com.EIMA.outputs.ResultBase;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/incidentaccess")
public class IncidentAccessRouting {

	@POST
	@Path("/join")
	public ResultBase joinIncident(JSONObject json) {
		try {
			String token = json.getString("token");
			int incidentID = json.getInt("incident");

			if (DBQueries.tokenIsValid(token) && DBQueries.isValidIncident(incidentID)) {
				DBQueries.addUserToIncident(incidentID, token);
				return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Path("/create")
	public ResultBase create(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && true) {// !DBQueries.userIsInIncident(token))
														// {
				int incident = DBQueries.createNewIncident(token);
				return new CreateIncidentResult(true, incident);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Path("/createstandalone")
	public ResultBase createStandalone(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && true) {// &&
														// !DBQueries.userIsInIncident(token))
														// {
				EIMAAsset[] assets = null; // TODO parse this data out
				EIMACircle[] circles = null;
				EIMAPolygon[] polygons = null;
				int incident = DBQueries.createIncidentFromData(token, assets, circles, polygons);
				return new CreateIncidentResult(true, incident);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Path("/leave")
	public ResultBase leave(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)) {
				DBQueries.removeUserFromIncident(token);
				return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}
}
