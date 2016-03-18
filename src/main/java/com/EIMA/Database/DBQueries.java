package com.EIMA.Database;

import com.EIMA.auth.AuthUtils;
import com.EIMA.models.*;

//Make a list of our queries and add calls to them here returning types from "com.EIMA.models" when relevant.

public class DBQueries {

	////////////////////////////////////////////////////////
	//////// UTILITY FUNCTIONS//////////////////////////////
	////////////////////////////////////////////////////////

	// Checks the active tokens list and sees if the provided is active
	// This must also use a prepared statement. This should be called by other
	// statements to verify
	public static boolean tokenIsValid(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	// Gets the Privlege level of a user in their current incident
	public static AuthUtils.Privlege getUserCurrIncidentPrivLevel(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	// gets the incident Id for the current incident a user is in
	public static int getUserCurrentIncident(String token) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Takes username/password. returns a true false value. This SHOULD use a
	 * Prepared statement to Prevent SQL injection. This is important. Other
	 * queries don't require this as non of their input makes it to the actual
	 * SQL statement exception of token validation (I think).
	 * 
	 * http://www.theserverside.com/news/1365244/Why-Prepared-Statements-are-
	 * important-and-how-to-use-them-properly
	 * http://stackoverflow.com/questions/24692296/how-to-use-prepared-statement
	 * -for-select-query-in-java
	 * http://www.java2novice.com/jdbc/prepared-statement/
	 */
	public static boolean validateLogin(String uname, String pwd) {
		return true;
	}

	// Verifys and incident ID is valid return true/false.
	public static boolean isValidIncident(int incidentID) {
		// TODO Auto-generated method stub
		return false;
	}
	////////////////////////////////////////////////////////
	//////// Core Data Modifications Functions /////////////
	////////////////////////////////////////////////////////

	// Sets a user's active token, for a login action.
	public static void setUserToken(String uname, String theirToken) {
		// TODO Auto-generated method stub

	}

	// Removes the active token from a user for a logout action.
	public static void removeUserToken(String theirToken) {
		// TODO Auto-generated method stub

	}

	// Should add a user to an incident; Their privlege should be set at No
	// access
	public static void addUserToIncident(int incidentID, String token) {
		// TODO Auto-generated method stub

	}

	// Creates a new incident with blank data, returns a integer of the created
	// Incident ID; this can be a sequence or random doesn't matter.
	public static int createNewIncident(String token) {
		// TODO Auto-generated method stub
		return 0;
	}

	// Creates a new incident with STANDALONE Data, returns a integer of the
	// created
	// Incident ID; this can be a sequence or random doesn't matter, check for
	// conflicts if its random.
	public static int createIncidentFromData(String token, EIMAAsset[] assets, EIMACircle[] circles,
			EIMAPolygon[] polygons) {
		// TODO Auto-generated method stub
		return 0;
	}

	// Returns true if a user is in an incident, false if not.
	public static boolean userIsInIncident(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	// Removes a user from incident. Called when a user 'leaves'
	public static void removeUserFromIncident(String token) {
		// TODO Auto-generated method stub

	}

	// Gets a user profile from a user's token, see EIMA PROFILE for formatting
	public static EIMAProfile getUserProfile(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	// Sets a user profile based on data sent from user.
	public static EIMAProfile setUserProfile(String token, EIMAProfile profile) {
		// TODO Auto-generated method stub
		return null;
	}

	// Adds a user to the userlist. Returns true if succesful, false if name is
	// taken.
	public static boolean registerUser(String uname, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	// Gets Assets of an incident
	public static EIMAAsset[] getAssets(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	// Gets Circles of an incident
	public static EIMACircle[] getCircles(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	// Gets Polygons of an incident
	public static EIMAPolygon[] getPolygons(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	// Updates the user's location within the incident
	public static void updateUserLocation(String token, GPSPosition location) {
		// TODO Auto-generated method stub

	}

	// Gets user list from incident.
	public static EIMAUser[] getUserList(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	// Adds a message to a user. Uses other persons username.
	public static void sendMessageTo(String token, String user, String message) {
		// TODO Auto-generated method stub

	}

	// Modifies a user's privlege -- takes username and privLevel -- ("noAccess"
	// "user" "mapEditor" "admin"), you can't edit your own privlege level
	public static boolean modifyPrivlege(String token, String username, String privLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	// Gets a list of all alerts a person has recieved.
	public static EIMAAlert[] getAlerts(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
