package com.eshop.project.api.shared.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private final Random RANDOM = new Random();

	public String generateRandomUserId(int length) {
		return generateRandomString(length);
	}

	public String generateRandomId(int length) {
		return generateRandomString(length);
	}

	private String generateRandomString(int length) {
		StringBuilder output = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			output.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return output.toString();
	}

}
