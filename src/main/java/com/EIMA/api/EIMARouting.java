package com.EIMA.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import javax.ws.rs.PathParam;
import java.util.TimeZone;

import com.EIMA.models.Time;
import com.EIMA.models.Validate;

@Path("/EIMA")
@Produces(MediaType.APPLICATION_JSON)
public class EIMARouting {

	@GET
	public Time get() {
		return new Time();
	}

	@GET
	@Path("/{timezone}")
	public Time get(@PathParam("timezone") String timezone) {
		return new Time(TimeZone.getTimeZone(timezone.toUpperCase()));
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/validate")
	public Validate putExample(String json) throws JSONException {				
		return new Validate(json);
	}
        
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/auth/login")
        public boolean login(String username, String password){
            // validate the username and password
            // if valid {
            String token = "random token";
            // store the token in database
            return true;
            // } 
            // else {
            // return false; 
            // }
        }
    
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/auth/logout")
        public boolean logout(String token){
            // if token is in database:
            // remove token from database
            return true;
            // if token is not in database:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/auth/validatetoken")
        public boolean validateToken(String token){
            // validate the token
            // if valid:
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/auth/validateprivilege")
        public boolean validatePrivilege(String token, String incidentID, 
        String requiredPrivLevel){
            // validate the privilege of the user
            // if valid:
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/incidentaccess/join")
        public boolean joinIncident(String token, String incidentID){
            // validate the token and incident ID
            // if valid:
            // join the user to the incident
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/incidentaccess/create")
        public boolean create(String token){
            // validate the token
            // if valid:
            // create a new incident id, set user level to admin, store in 
            // database
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/incidentaccess/createstandalone")
        public boolean createStandalone(String token, String incidentData){
            // validate the token 
            // if valid:
            // create a new incident from standalone data
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/incidentaccess/leave")
        public boolean leave(String token){
            // validate the token 
            // if valid:
            // validate privilege
            // if valid:
            // add asset info to database
            // remove user from the incident
            return true;
            // if invalid token or privilege:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/users/getprofile")
        public boolean getProfile(String token){
            // validate the token 
            // if valid:
            // display the profile information
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/users/setprofile")
        public boolean setProfile(String token, String profileData){
            // validate the token 
            // if valid:
            // store profileData in database
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/users/registeruser")
        public boolean registerUser(String username, String password, String 
                profileData){
            // store new user and profileData in database
            return true;
            // if error:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/incidentdata/mapdata")
        public boolean getMapData(String token){
            // validate the token 
            // if valid:
            // display the map data
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/incidentdata/accesslevel")
        public boolean getAccessLevel(String token){
            // validate the token 
            // if valid:
            // display the access level of user
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/incidentdata/location")
        public boolean location(String token, double Latitude, double 
                Longitute){
            // validate the token 
            // if valid:
            // push the location data
            return true;
            // if invalid:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/mapedit/addassetinfo")
        public boolean addAssetInfo(String token, String mapData){
            // validate the token 
            // if valid:
            // validate privilege
            // if valid:
            // add asset info to database
            return true;
            // if invalid privilege or token:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/admin/userlist")
        public boolean userList(String Token){
            // validate the token 
            // if valid:
            // validate privilege
            // if valid:
            // display list of users and all info associated with them
            return true;
            // if invalid token or privilege:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/admin/sendmessage")
        public boolean sendMessage(String Token, String username, 
                String message){
            // validate the token 
            // if valid:
            // validate privilege
            // if valid:
            // send a message to the user
            return true;
            // if invalid token or privilege:
            // return false;
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Path("/admin/editprivilege")
        public boolean editPrivilege(String Token, String username, 
                String privilegeLevel){
            // validate the token 
            // if valid:
            // validate privilege
            // if valid:
            // change the users privilege level
            return true;
            // if invalid token or privilege level:
            // return false;
        }
}
