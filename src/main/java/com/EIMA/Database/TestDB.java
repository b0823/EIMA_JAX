package com.EIMA.Database;

import java.sql.Connection;
import java.sql.SQLException;

import com.EIMA.models.EIMAProfile;
import com.EIMA.models.EIMAUser;

public class TestDB {
	String[] users = {"mark", "robert", "brad", "marie"};
	String[] passwords = {"a", "b", "c", "d"};
	String[] tokens = {"abc", "def", "ghi", "jkl"};
	
	public static void main(String[] args) throws Exception{
		TestDB test = new TestDB();
		test.run();
	}
	
	public void reset() {
		Connection db = DBConnection.getConnection();
		String[] tables = {
				"in_out_events","message_events","permission_events",
				"request_events","map_events","map_objects",
				"events","members","incidents","users"
			};
		String sql = "";
		for (int i = 0; i < tables.length; i++) {
			sql += "delete from " + tables[i] + ";";
		}
		try{
			db.createStatement().execute(sql);
		} catch (SQLException e) {
			System.out.println("Unable to delete table entries.");
		}
	}

	public void run() {
		reset();
		boolean success = addUsers();
		System.out.println("added users: " + success);
		setTokens();
		System.out.println("tokens set");
		addProfiles();
		System.out.println("profiles added");
		int incident1 = startIncident(tokens[0]);
		System.out.println("Started incident 1");
		joinIncident(incident1, tokens[1]);
		System.out.println("Members in mark's incident:");
		displayIncidentMembers(tokens[0]);
	}
	
	public boolean addUsers() {
		boolean success = true;
		for (int i = 0; i < users.length && success; i++) {
			success = DBQueries.registerUser(users[i], passwords[i]);
		}
		return success;
	}
	public void setTokens() {
		for (int i = 0; i < tokens.length; i++) {
			DBQueries.setUserToken(users[i], tokens[i]);
		}
	}
	public void addProfiles(){
		EIMAProfile[] profiles = {
				new EIMAProfile(users[0], "Dayton", "DF", "", "Fire"),
				new EIMAProfile(users[1], "Dayton", "DP", "", "Police"),
				new EIMAProfile(users[2], "North", "QZ", "", "EMT"),
				new EIMAProfile(users[3], "South", "DOC", "", "Doctor")
		};
		for (int i = 0; i < users.length; i++) {
			DBQueries.setUserProfile(tokens[i], profiles[i]);
		}
	}
	public int startIncident(String token) {
		return DBQueries.createNewIncident(token);
	}
	public void joinIncident(int incidentId, String token) {
		for (int i = 1; i < tokens.length; i++) {
			DBQueries.addUserToIncident(incidentId, tokens[i]);
		}
	}
	public void displayIncidentMembers(String token){
		EIMAUser[] members = DBQueries.getUserList(token);
		for (EIMAUser m : members) {
			System.out.println("Name:" + m.getName());
			System.out.println("Org:" + m.getOrganization());
			System.out.println("Privilege:" + m.getPrivLevel());
		}
	}
}