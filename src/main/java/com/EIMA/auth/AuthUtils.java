package com.EIMA.auth;

import com.EIMA.Database.DBQueries;

import java.util.UUID;

public class AuthUtils {

	public static enum Privlege {
		noAccess, user, mapEditor, admin
	}

	public static boolean login(String uname, String pwd) {
		return DBQueries.validateLogin(uname, pwd);
	}

	public static boolean hasPrivlege(String token, Privlege requiredPriv) {
		Privlege usersP = usersCurrentPrivLevel(token);

		// Admin
		if (requiredPriv.equals(Privlege.admin)) {
			return usersP.equals(Privlege.admin);
		}
		// MapEditor
		if (requiredPriv.equals(Privlege.mapEditor)) {
			return usersP.equals(Privlege.admin) || usersP.equals(Privlege.mapEditor);
		}
		// User
		if (requiredPriv.equals(Privlege.mapEditor)) {
			return usersP.equals(Privlege.admin) || usersP.equals(Privlege.mapEditor) || usersP.equals(Privlege.user);
		}
		// NoAccess
		return true;
	}


	public static String generateToken() {
		long a = System.currentTimeMillis();
		String uuid = UUID.randomUUID().toString();

		StringBuilder token = new StringBuilder();

		token.append(convertHex(a));
		token.append("-");
		token.append(uuid);

		return token.toString();
	}

	private static Privlege usersCurrentPrivLevel(String token) {
		return DBQueries.getUserCurrIncidentPrivLevel(token);
	}

	public static int usersCurrentIncident(String token) {
		return DBQueries.getUserCurrentIncident(token);
	}

	private static String convertHex(long n) {
		return Long.toHexString(n);
	}
}
