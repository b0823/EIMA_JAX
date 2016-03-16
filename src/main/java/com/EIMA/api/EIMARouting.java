package com.EIMA.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import com.EIMA.outputs.AccessResult;
import com.EIMA.outputs.CreateIncidentResult;
import com.EIMA.outputs.MapDataResult;
import com.EIMA.outputs.ResultBase;
import com.EIMA.outputs.UserListResult;
import com.EIMA.outputs.UserProfileResult;

import javax.ws.rs.PathParam;
import java.util.TimeZone;

@Path("/EIMA")
@Produces(MediaType.APPLICATION_JSON)
public class EIMARouting {

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/auth/login")
	public ResultBase login(String username, String password) {
		// validate the username and password
		// if valid {
		String token = "random token";
		// store the token in database
		return null;
		// }
		// else {
		// return false;
		// }
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/auth/logout")
	public ResultBase logout(String token) {
		// if token is in database:
		// remove token from database
		return null;
		// if token is not in database:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/auth/validatetoken")
	public ResultBase validateToken(String token) {
		// validate the token
		// if valid:
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/auth/validateprivilege")
	public ResultBase validatePrivilege(String token, String incidentID, String requiredPrivLevel) {
		// validate the privilege of the user
		// if valid:
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/incidentaccess/join")
	public ResultBase joinIncident(String token, String incidentID) {
		// validate the token and incident ID
		// if valid:
		// join the user to the incident
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/incidentaccess/create")
	public CreateIncidentResult create(String token) {
		// validate the token
		// if valid:
		// create a new incident id, set user level to admin, store in
		// database
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/incidentaccess/createstandalone")
	public CreateIncidentResult createStandalone(String token, String incidentData) {
		// validate the token
		// if valid:
		// create a new incident from standalone data
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/incidentaccess/leave")
	public ResultBase leave(String token) {
		// validate the token
		// if valid:
		// validate privilege
		// if valid:
		// add asset info to database
		// remove user from the incident
		return null;
		// if invalid token or privilege:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/users/getprofile")
	public UserProfileResult getProfile(String token) {
		// validate the token
		// if valid:
		// display the profile information
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/users/setprofile")
	public ResultBase setProfile(String token, String profileData) {
		// validate the token
		// if valid:
		// store profileData in database
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/users/registeruser")
	public ResultBase registerUser(String username, String password, String profileData) {
		// store new user and profileData in database
		return null;
		// if error:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/incidentdata/mapdata")
	public MapDataResult getMapData(String token) {
		// validate the token
		// if valid:
		// display the map data
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/incidentdata/accesslevel")
	public AccessResult getAccessLevel(String token) {
		// validate the token
		// if valid:
		// display the access level of user
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/incidentdata/location")
	public ResultBase location(String token, double Latitude, double Longitute) {
		// validate the token
		// if valid:
		// push the location data
		return null;
		// if invalid:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/mapedit/addassetinfo")
	public ResultBase addAssetInfo(String token, String mapData) {
		// validate the token
		// if valid:
		// validate privilege
		// if valid:
		// add asset info to database
		return null;
		// if invalid privilege or token:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/admin/userlist")
	public UserListResult userList(String Token) {
		// validate the token
		// if valid:
		// validate privilege
		// if valid:
		// display list of users and all info associated with them
		return null;
		// if invalid token or privilege:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/admin/sendmessage")
	public ResultBase sendMessage(String Token, String username, String message) {
		// validate the token
		// if valid:
		// validate privilege
		// if valid:
		// send a message to the user
		return null;
		// if invalid token or privilege:
		// return false;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/admin/editprivilege")
	public ResultBase editPrivilege(String Token, String username, String privilegeLevel) {
		// validate the token
		// if valid:
		// validate privilege
		// if valid:
		// change the users privilege level
		return null;
		// if invalid token or privilege level:
		// return false;
	}
}
