package com.EIM.auth;

import java.util.concurrent.TimeUnit;

import com.EIMA.Database.DBQueries;

import java.util.UUID;

public class AuthUtils {

	public static enum Privlege {
		NO_ACCESS, USER, MAP_EDITOR, ADMIN
	}

	public static boolean login(String uname, String pwd) {
		return DBQueries.validateLogin(uname, pwd);
	}

	public static boolean hasPrivlege(String token, Privlege requiredPriv) {
		Privlege usersP = getUserPrivleges(token);

		// Admin
		if (requiredPriv.equals(Privlege.ADMIN)) {
			return usersP.equals(Privlege.ADMIN);
		}
		// MapEditor
		if (requiredPriv.equals(Privlege.MAP_EDITOR)) {
			return usersP.equals(Privlege.ADMIN) || usersP.equals(Privlege.MAP_EDITOR);
		}
		// User
		if (requiredPriv.equals(Privlege.MAP_EDITOR)) {
			return usersP.equals(Privlege.ADMIN) || usersP.equals(Privlege.MAP_EDITOR) || usersP.equals(Privlege.USER);
		}
		// NoAccess
		return true;
	}

	public static Privlege getUserPrivleges(String token) {
		String priv = usersCurrentPrivLevel(token);

		if (priv == "Admin") {
			return Privlege.ADMIN;
		} else if (priv == "Map Editor") {
			return Privlege.MAP_EDITOR;
		} else if (priv == "User") {
			return Privlege.USER;
		}
		return Privlege.NO_ACCESS;
	}

	public static String generateToken() {
		long a = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
		String uuid = UUID.randomUUID().toString();

		StringBuilder token = new StringBuilder();

		token.append(convertHex(a));
		token.append("-");
		token.append(uuid);

		return token.toString();
	}

	public static String usersCurrentPrivLevel(String token) {
		return DBQueries.getUserCurrIncidentPrivLevel(token);
	}

	public static int usersCurrentIncident(String token) {
		return DBQueries.getUserCurrentIncident(token);
	}

	private static String convertHex(long n) {
		return Long.toHexString(n);
	}
}
