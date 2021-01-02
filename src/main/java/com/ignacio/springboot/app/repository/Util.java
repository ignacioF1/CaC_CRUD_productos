package com.ignacio.springboot.app.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

public class Util {

	public static Map<String ,Object> makeMap(String key, Object value){ // Map Http response message
	    Map<String , Object> map = new HashMap<>();
	    map.put(key, value);
	    return map;
	}
	public static boolean isGuest(Authentication authentication){ // Check if someone is logged in
	    return authentication == null || authentication instanceof AnonymousAuthenticationToken;
	}
	
	public static boolean isValid(String email){ // Check if email is valid
		String EMAIL_REGEX =
		        "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w])+[\\w]";
		// No valido el . y dominio final, tal como hace bootstrap
		// ej.: mail@localhost no tiene ".com" etc.
		return email.matches(EMAIL_REGEX);
	}
	/*
	"^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"
	
	The first ^[\w-_\.+]. The ^ symbols means check the first character. 
	This the regex processor that the email address should start with a word character 
	formed of alphanumeric value (a-z 0-9) or it can also be an hyphen, underscore, 
	dot or a plus symbol.

	The second part, *[\w-_\.]. The * symbol means match the preceding zero or more times. 
	As the first part, this tell the regex processor to check for another zero or more 
	characters, and it can also contains hyphen, underscore and a dot.

	The third part, \@([\w]+\.)+. This check that email address should contain the @ 
	symbol followed by one or more word separated by the dot symbol.

	The last part is, [\w]+[\w]$, this check that after the last period there should be 
	another word for the domain suffix such as the co.uk or co.id. And the $ ask that the 
	email address should ends by a word character.
*/
}