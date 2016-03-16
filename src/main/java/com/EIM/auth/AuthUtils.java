package com.EIM.auth;

public class AuthUtils {

	public static enum Privlege {
		NO_ACCESS, USER, MAP_EDITOR, ADMIN
	}

	public static boolean login() {
		return true;
	}

	public static boolean hasPrivlege(String token, Privlege p) {
		return true;
	}

	public static String generateToken() {
		return null;
	}
	
	public static int usersCurrentIncident(String token) {
		//would call a DB function to get users current incident
		return 1;
	}
	
}
