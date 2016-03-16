package com.EIMA.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.EIM.auth.AuthUtils;
import com.EIMA.Database.DBQueries;
import com.EIMA.outputs.AccessResult;
import com.EIMA.outputs.CreateIncidentResult;
import com.EIMA.outputs.LoginResult;
import com.EIMA.outputs.MapDataResult;
import com.EIMA.outputs.ResultBase;
import com.EIMA.outputs.UserListResult;
import com.EIMA.outputs.UserProfileResult;

@Path("/EIMA")
@Produces(MediaType.APPLICATION_JSON)
public class EIMARouting {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/auth/login")
	public ResultBase login(JSONObject json) {
		try {
			String uname = json.getString("username");
			if (AuthUtils.login(uname, json.getString("password"))) {
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

		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/auth/validatetoken")
	public ResultBase validateToken(JSONObject json) {
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/auth/validateprivilege")
	public ResultBase validatePrivilege(JSONObject json) {
		return new ResultBase(false);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentaccess/join")
	public ResultBase joinIncident(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentaccess/create")
	public ResultBase create(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentaccess/createstandalone")
	public ResultBase createStandalone(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentaccess/leave")
	public ResultBase leave(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/users/getprofile")
	public ResultBase getProfile(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/users/setprofile")
	public ResultBase setProfile(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/users/registeruser")
	public ResultBase registerUser(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentdata/mapdata")
	public ResultBase getMapData(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentdata/accesslevel")
	public ResultBase getAccessLevel(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/incidentdata/location")
	public ResultBase location(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/mapedit/addassetinfo")
	public ResultBase addAssetInfo(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/admin/userlist")
	public ResultBase userList(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/admin/sendmessage")
	public ResultBase sendMessage(JSONObject json) {
		return new ResultBase(false);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/admin/editprivilege")
	public ResultBase editPrivilege(JSONObject json) {
		return new ResultBase(false);

	}
}
