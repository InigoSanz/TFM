package com.iem.tfm.domain.util;

/**
 * Clase utilitaria del dominio para validar contraseñas de usuarios.
 * <p>
 * Reglas mínimas de seguridad:
 * <ul>
 * <li>Longitud mínima de 8 caracteres</li>
 * <li>Al menos una letra mayúscula</li>
 * <li>Al menos un número</li>
 * </ul>
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public class PasswordRules {

	/**
	 * Constructor privado para evitar la instanciación
	 */
	private PasswordRules() {
	}

	/**
	 * Valida si la contraseña cumple con los requisitos mínimos de seguridad.
	 * 
	 * @param password Contraseña a validar
	 * @return {@code true} si la contraseña es válida, {@code false} si no
	 */
	public static boolean isPasswordValid(String password) {

		if (password == null || password.length() < 8) {
			return false;
		}

		boolean hasCapitalLetter = false;

		// Contiene al menos una mayúscula
		if (password.matches(".*[A-Z].*")) {
			hasCapitalLetter = true;
		}

		boolean hasNumber = false;

		// Contiene al menos una minúscula
		if (password.matches(".*\\d.*")) {
			hasNumber = true;
		}

		return hasCapitalLetter && hasNumber;
	}
}