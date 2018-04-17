package com.spring.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPassword {

	public static String cryptPasswordEncoder(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

}
