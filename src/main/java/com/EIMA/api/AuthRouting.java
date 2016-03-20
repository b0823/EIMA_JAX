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
import com.EIMA.outputs.LoginResult;
import com.EIMA.outputs.ResultBase;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthRouting {

	@POST
	@Path("/login")
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
	@Path("/logout")
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
	@Path("/validatetoken")
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

}
