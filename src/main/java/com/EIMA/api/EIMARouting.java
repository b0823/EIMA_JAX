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

@Path("/EIMA")
@Produces(MediaType.APPLICATION_JSON)
public class EIMARouting {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/auth/login")
	public ResultBase login(JSONObject json) {
		try {
			String uname = json.getString("username");
			if (DBQueries.validateLogin(uname, json.getString("password"))) {
				String theirToken = AuthUtils.generateToken();
				DBQueries.setUserToken(uname, theirToken);
				return new LoginResult(true, theirToken);
			}
		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/auth/logout")
	public ResultBase logout(JSONObject json) {
		try {
			String token = json.getString("token");
			if (DBQueries.tokenIsValid(token)) {
				DBQueries.removeUserToken(token);
				return new ResultBase(true);
			}
		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/auth/validatetoken")
	public ResultBase validateToken(JSONObject json) {
		try {
			String token = json.getString("token");
			if (DBQueries.tokenIsValid(token)) {
				return new ResultBase(true);
			}
		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentaccess/join")
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentaccess/create")
	public ResultBase create(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && !DBQueries.userIsInIncident(token)) {
				int incident = DBQueries.createNewIncident(token);
				return new CreateIncidentResult(true, incident);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentaccess/createstandalone")
	public ResultBase createStandalone(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && !DBQueries.userIsInIncident(token)) {
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentaccess/leave")
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/users/getprofile")
	public ResultBase getProfile(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)) {
				EIMAProfile res = DBQueries.getUserProfile(token);
				return new UserProfileResult(true, res);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/users/setprofile")
	public ResultBase setProfile(JSONObject json) {
		try {
			String token = json.getString("token");

			EIMAProfile profile = null;// TODO add code to parse this.

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)) {
				EIMAProfile res = DBQueries.setUserProfile(token, profile);
				return new UserProfileResult(true, res);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/users/registeruser")
	public ResultBase registerUser(JSONObject json) {
		try {
			String uname = json.getString("username");
			String password = json.getString("username");
			if (DBQueries.registerUser(uname, password)) {
				return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentdata/mapdata")
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentdata/alerts")
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentdata/accesslevel")
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentdata/location")
	public ResultBase location(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)) {
				GPSPosition location = new GPSPosition(25, 25); // TODO parse
																// this out
				DBQueries.updateUserLocation(token, location);
				return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/mapedit/addassetinfo")
	public ResultBase addAssetInfo(JSONObject json) {
		return new ResultBase(false); //TODO

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/admin/userlist")
	public ResultBase userList(JSONObject json) {
		try {
			String token = json.getString("token");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)
					&& AuthUtils.hasPrivlege(token, AuthUtils.Privlege.admin)) {
				EIMAUser[] lst = DBQueries.getUserList(token);
				return new UserListResult(true, lst);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/admin/sendmessage")
	public ResultBase sendMessage(JSONObject json) {
		try {
			String token = json.getString("token");
			String user = json.getString("username");
			String message = json.getString("message");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)
					&& AuthUtils.hasPrivlege(token, AuthUtils.Privlege.admin)) {
				DBQueries.sendMessageTo(token, user, message);
				return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/admin/editprivilege")
	public ResultBase editPrivilege(JSONObject json) {
		try {
			String token = json.getString("token");
			String privLevel = json.getString("privLevel");
			String username = json.getString("username");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)
					&& AuthUtils.hasPrivlege(token, AuthUtils.Privlege.admin)) {
				if (DBQueries.modifyPrivlege(token, username, privLevel))
					return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}
}
