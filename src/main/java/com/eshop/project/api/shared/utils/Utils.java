package com.eshop.project.api.shared.utils;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

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

	public String normalizeString(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

	public String getStub(String input) {
		String[] arrayString = input.split(" ");
		if (arrayString.length > 1) {
			String firstPass = Arrays.stream(arrayString).map(el -> el.toLowerCase()).map(el -> el + "-")
					.collect(Collectors.joining(""));
			return firstPass.substring(0, firstPass.length() - 1);
		}
		return input.toLowerCase();
	}

}
