package com.iem.tfm.domain.util;

import java.security.SecureRandom;

/**
 * Clase utilitaria para generar contraseñas seguras aleatorias.
 * <p>
 * Este generador asegura que cada contraseña contenga al menos una letra
 * minúscula, una mayúscula, un número y un carácter especial.
 * </p>
 * 
 * <p>
 * Código adaptado del usuario de GitHub: <a href=
 * "https://gist.github.com/OM-VIT/8224354e61ade0fbc854cc167d0d1b47">OM-VIT</a>
 * </p>
 * 
 * Esta clase no puede ser instanciada.
 * 
 * @author Inigo
 * @version 1.0
 */
public class PasswordGenerator {

	private static final SecureRandom random = new SecureRandom();

	private PasswordGenerator() {
		// Constructor privado
	}

	/**
	 * Genera una contraseña segura con la longitud especificada.
	 * <p>
	 * La contraseña generada contiene al menos:
	 * <ul>
	 * <li>Una letra minúscula</li>
	 * <li>Una letra mayúscula</li>
	 * <li>Un número</li>
	 * <li>Un carácter especial de los siguientes: {@code !@#$}</li>
	 * </ul>
	 * Los caracteres restantes se rellenan aleatoriamente con letras, números y
	 * símbolos.
	 * </p>
	 *
	 * @param length longitud total de la contraseña a generar (mínimo 4)
	 * @return contraseña aleatoria segura
	 * @throws IllegalArgumentException si la longitud es menor que 4
	 */
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