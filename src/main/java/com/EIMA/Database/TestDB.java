package com.EIMA.Database;

import java.sql.Connection;
import java.sql.SQLException;

import com.EIMA.models.EIMAAlert;
import com.EIMA.models.EIMAAsset;
import com.EIMA.models.EIMACircle;
import com.EIMA.models.EIMAPolygon;
import com.EIMA.models.EIMAProfile;
import com.EIMA.models.EIMAUser;
import com.EIMA.models.GPSPosition;
import com.EIMA.models.MapAsset;

public class TestDB {
	String[] users = {"mark", "robert", "brad", "marie"};
	String[] passwords = {"a", "b", "c", "d"};
	String[] tokens = {"abc", "def", "ghi", "jkl"};
	EIMAAsset asset1;
	EIMAAsset asset2;
	EIMACircle circle1;
	EIMACircle circle2;
	EIMAPolygon poly1;
	EIMAPolygon poly2;
	
	public static void main(String[] args) throws Exception{
		TestDB test = new TestDB();
		test.run();
	}
	
	public void reset() {
		Connection db = DBConnection.getConnection();
		String[] tables = {
				"in_out_events","message_events","permission_events",
				"request_events","map_events","map_objects",
				"events","members","users","incidents"
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
	
	public void cleanup() {
		for (String s : tokens) {
			DBQueries.removeUserFromIncident(s);
			DBQueries.removeUserToken(s);
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
		setPrivilege(tokens[0], users[1], "mapEditor");
		alert(tokens[0], users[1], "Shut up foo");
		alert(tokens[1], users[0], "You shut up!");
		readAlerts(tokens[1]);
		asset1 = new EIMAAsset("one","truck1","unit1",new GPSPosition(100,100),"org1","ok","truck",false);
		asset2 = new EIMAAsset("two","car1","unit2",new GPSPosition(200,200),"org1","on fire", "car", false);
		addAsset(asset1, tokens[0]);
		addAsset(asset2, tokens[0]);
		System.out.println("incident1 assets:");
		seeAssets(tokens[1]);
		asset1.setPosition(new GPSPosition(40,50));
		modifyAsset(asset1, tokens[0]);
		updateUserLocation(tokens[0], 30, 10);
		System.out.println("incident1 assets after movements:");
		seeAssets(tokens[0]);
		deleteAsset(asset1, tokens[0]);
		leaveIncident(tokens[1]);
		System.out.println("incident1 assets after Robert left in his truck:");
		seeAssets(tokens[0]);
		
		int incident2 = startIncident(tokens[2]);
		System.out.println("Started incident 2");
		joinIncident(incident2, tokens[1]);
		joinIncident(incident2, tokens[3]);
		setPrivilege(tokens[2], users[3], "admin");
		setPrivilege(tokens[2], users[1], "user");
		circle1 = new EIMACircle("circ1", new GPSPosition(1000,1000), 30.2, "big, scary","fire");
		circle2 = new EIMACircle("circ2", new GPSPosition(1000,500), 1.3, "","flood");
		GPSPosition[] polyCoords1 = {new GPSPosition(1,1), new GPSPosition(2,5), new GPSPosition(3,7)}; 
		poly1 = new EIMAPolygon("poly1", polyCoords1, "pillow-fight", "triangle");
		GPSPosition[] polyCoords2 = {new GPSPosition(3,3), new GPSPosition(1,10), new GPSPosition(2,30), new GPSPosition(4,1)};
		poly2 = new EIMAPolygon("poly2", polyCoords2, "square-dance", "square");
		addAsset(circle1, tokens[2]);
		addAsset(circle2, tokens[3]);
		addAsset(poly1, tokens[2]);
		addAsset(poly2, tokens[2]);
		circle2.setNote("little");
		circle2.setRadius(.3);
		modifyAsset(circle2, tokens[2]);
		System.out.println("Incident2 assets");
		seeAssets(tokens[1]);
		System.out.println("Incident1 assets");
		seeAssets(tokens[0]);
		alert(tokens[1], "USERS", "I can fly");
		alert(tokens[2], users[3], "Hello");
		alert(tokens[3], "ADMINS", "are you an admin?");
		System.out.println("alerts:");
		System.out.println(users[0]);
		readAlerts(tokens[0]);
		System.out.println(users[1]);
		readAlerts(tokens[1]);
		System.out.println(users[2]);
		readAlerts(tokens[2]);
		System.out.println(users[3]);
		readAlerts(tokens[3]);
		poly1.setNote("It is winding down now");
		modifyAsset(poly1, tokens[2]);
		updateUserLocation(tokens[0], 300, 250);
		
		System.out.println("incident1:");
		seeAssets(tokens[0]);
		displayIncidentMembers(tokens[0]);
		
		System.out.println("incident2:");
		seeAssets(tokens[2]);
		displayIncidentMembers(tokens[2]);
		
		isTokenValid("qwerqwerqwer"); // no
		isTokenValid(tokens[2]); // yes
		isIncidentValid(-1); // no
		isIncidentValid(incident1); // yes
		isInIncident("qwerqwerqwer"); // no
		isInIncident(tokens[3]); // yes
		readPriv(tokens[2]); // admin
		System.out.println("Incident2 is " + incident2);
		readCurrentIncident(tokens[3]); // incident2
		validateLogin(users[0], passwords[3]); // no
		validateLogin(users[0], passwords[0]); // yes
		
		EIMAAsset a = new EIMAAsset("camel", "camel", "unit", new GPSPosition(0,30), "org", "status", "type", false);
		EIMAAsset b = new EIMAAsset("horse", "horse", "unit", new GPSPosition(20,30), "org", "status", "type", false);
		EIMACircle c = new EIMACircle("vocano", new GPSPosition(40, 10), 30, "", "volcano");
		EIMACircle d = new EIMACircle("tornado", new GPSPosition(20, 20), 10, "fast", "tornado");
		GPSPosition[] p1 = {new GPSPosition(10,10), new GPSPosition(20,20), new GPSPosition(30,30)};
		GPSPosition[] p2 = {new GPSPosition(5,5), new GPSPosition(7,7)};
		EIMAPolygon e = new EIMAPolygon("toxic", p1,"spill", "");
		EIMAPolygon f = new EIMAPolygon("flood", p2, "flood", "wet");
		leaveIncident(tokens[3]);
		int incident3 = DBQueries.createIncidentFromData(tokens[3], new EIMAAsset[]{a,b},
				new EIMACircle[]{c,d}, new EIMAPolygon[]{e,f});
		System.out.println("Incident 3 created");
		System.out.println("Incident 3:");
		seeAssets(tokens[3]);
		displayIncidentMembers(tokens[3]);
		
		cleanup();
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
		DBQueries.addUserToIncident(incidentId, token);
	}
	public void displayIncidentMembers(String token){
		EIMAUser[] members = DBQueries.getUserList(token);
		for (EIMAUser m : members) {
			System.out.println("Name:" + m.getName());
			System.out.println("Org:" + m.getOrganization());
			System.out.println("Privilege:" + m.getPrivLevel());
		}
	}
	public void setPrivilege(String token, String user, String priv) {
		
		DBQueries.modifyPrivlege(token, user, priv);
	}
	public void alert(String token, String target, String msg) {
		DBQueries.sendMessageTo(token, target, msg);
	}
	public void readAlerts(String token) {
		EIMAAlert[] alerts = DBQueries.getAlerts(token);
		for (EIMAAlert alert : alerts) {
			System.out.println(
				"Time:" + alert.getTimestamp() + "\n" +
				"Sender:" + alert.getSender() + "\n" +
				"Message:" + alert.getMessage()
			);
		}
	}
	public void addAsset(MapAsset asset, String token) {
		DBQueries.addMapAsset(asset, token);
	}
	public void seeAssets(String token) {
		EIMAAsset[] a = DBQueries.getAssets(token);
		EIMACircle[] c = DBQueries.getCircles(token);
		EIMAPolygon[] p = DBQueries.getPolygons(token);
		System.out.println("Assets:");
		for (EIMAAsset asset : a) {
			System.out.println(
				"uid:" + asset.getUsername() + "\n" +
				"name:" + asset.getName() + "\n" +
				"status:" + asset.getStatus() + "\n" +
				"unit:" + asset.getUnit() + "\n" +
				"unitType:" + asset.getUnitType() + "\n" +
				"pos:" + asset.getPosition().getLatitude() + "," + asset.getPosition().getLongitude()
			);
		}
		System.out.println("Circles:");
		for (EIMACircle asset : c) {
			System.out.println(
				"uid:" + asset.getUid() + "\n" +
				"type:" + asset.getType() + "\n" +
				"radius:" + asset.getRadius() + "\n" +
				"notes:" + asset.getNote() + "\n" +
				"pos:" + asset.getCenter().getLatitude() + "," + asset.getCenter().getLongitude()
			);
		}
		System.out.println("Polygons:");
		for (EIMAPolygon asset : p) {
			System.out.println(
				"uid:" + asset.getUid() + "\n" +
				"type:" + asset.getType() + "\n" +
				"notes:" + asset.getNote()
			);
			GPSPosition[] gps = asset.getPoints();
			String str = "";
			for (GPSPosition pt : gps) {
				str += "[" + pt.getLatitude() + "," + pt.getLongitude() + "]";
			}
			System.out.println("{" + str + "}");
		}
	}
	public void modifyAsset(MapAsset asset, String token) {
		DBQueries.updateMapAsset(asset, token);
	}
	public void updateUserLocation(String token, int lat, int lon) {
		DBQueries.updateUserLocation(token, new GPSPosition(lat,lon));
	}
	public void leaveIncident(String token) {
		DBQueries.removeUserFromIncident(token);
	}
	private void deleteAsset(EIMAAsset asset, String token) {
		DBQueries.deleteMapAsset(asset, token);
	}
	private void isTokenValid(String token) {
		if (DBQueries.tokenIsValid(token)) {
			System.out.println(token + " is valid token.");
		} else {
			System.out.println(token + " is not valid token.");

		}
	}
	private void isIncidentValid(int incident) {
		if (DBQueries.isValidIncident(incident)) {
			System.out.println(incident + " is valid incident.");
		} else {
			System.out.println(incident + " is not valid incident.");

		}
	}
	private void readPriv(String token) {
		System.out.println("Priv for " + token + " is " + DBQueries.getUserCurrIncidentPrivLevel(token));
	}
	private void isInIncident(String token) {
		if (DBQueries.userIsInIncident(token)) {
			System.out.println(token + " is in incident.");
		} else {
			System.out.println(token + " is not in incident.");

		}
	}
	private void readCurrentIncident(String token) {
		System.out.println(token + " is in incident " + DBQueries.getUserCurrentIncident(token));
	}
	private void validateLogin(String user, String pwd) {
		if (DBQueries.validateLogin(user, pwd)) {
			System.out.println("Valid password for " + user);
		} else {
			System.out.println("Invalid password for " + user);

		}
	}
}