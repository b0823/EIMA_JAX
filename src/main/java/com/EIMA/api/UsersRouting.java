package com.EIMA.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.EIMA.Database.DBQueries;
import com.EIMA.models.EIMAProfile;
import com.EIMA.outputs.ResultBase;
import com.EIMA.outputs.UserProfileResult;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
public class UsersRouting {

	@POST
	@Path("/getprofile")
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
	@Path("/setprofile")
	public ResultBase setProfile(JSONObject json) {
		try {
			String token = json.getString("token");

			EIMAProfile profile = null;// TODO add code to parse this.

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)) {
				DBQueries.setUserProfile(token, profile);
				return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Path("/registeruser")
	public ResultBase registerUser(JSONObject json) {
		try {
			String uname = json.getString("username");
			String password = json.getString("password");
			if (DBQueries.registerUser(uname, password)) {
				return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}
}
