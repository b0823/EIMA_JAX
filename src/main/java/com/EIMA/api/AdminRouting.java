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
import com.EIMA.models.EIMAUser;
import com.EIMA.outputs.ResultBase;
import com.EIMA.outputs.UserListResult;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/admin")
public class AdminRouting {
	
	@POST
	@Path("/userlist")
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
	@Path("/sendmessage")
	public ResultBase sendMessage(JSONObject json) {
		try {
			String token = json.getString("token");
			String user = json.getString("username");
			String message = json.getString("message");

			if (DBQueries.tokenIsValid(token) && DBQueries.userIsInIncident(token)
					&& AuthUtils.hasPrivlege(token, AuthUtils.Privlege.user)) {
				DBQueries.sendMessageTo(token, user, message);
				return new ResultBase(true);
			}

		} catch (JSONException e) {
			return new ResultBase(false);
		}
		return new ResultBase(false);
	}

	@POST
	@Path("/editprivilege")
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
