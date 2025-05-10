package com.iem.tfm.domain.util;

/**
 * Clase utilitaria del dominio para poder validar las contraseñas.
 * 
 * Requiere lo siguiente:
 * - Una longitud mínima de 8 caracteres.
 * - Al menos una letra mayúscula.
 * - Al menos un número.
 * 
 * @author Inigo
 * @version 1.0
 */
public class PasswordRules {
	
	private PasswordRules() {
		// Clase de reserva por si necesitamos utilizarla dentro de la clase.
	}
	
	public static boolean isPasswordValid(String password) {
		
		if (password == null || password.length() < 8) {
			return false;
		}
		
		boolean hasCapitalLetter = false;
		
		if (password.matches(".*[A-Z].*")) {
			hasCapitalLetter = true;
		}
		
		boolean hasNumber = false;
		
		if (password.matches(".*\\d.*")) {
			hasNumber = true;
		}
		
		return hasCapitalLetter && hasNumber;
	}
}