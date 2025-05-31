package com.iem.tfm.domain.util;

import java.security.SecureRandom;

/**
 * Código cogido del usuario de GitHub: OM-VIT
 * https://gist.github.com/OM-VIT/8224354e61ade0fbc854cc167d0d1b47
 */
public class PasswordGenerator {

	private static final SecureRandom random = new SecureRandom();

	private PasswordGenerator() {
		// Constructor privado
	}

	public static String generatePassword(int length) {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;

		char[] password = new char[length];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < length; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}

		return new String(password);
	}
}