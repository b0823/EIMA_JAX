package com.EIMA.Database;

//Make a list of our queries and add calls to them here returning types from "com.EIMA.models" when relevant.

public class DBQueries {

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
		return false;
	}

	public static String getUserCurrIncidentPrivLevel(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getUserCurrentIncident(String token) {
		// TODO Auto-generated method stub
		return 0;
	}

}
