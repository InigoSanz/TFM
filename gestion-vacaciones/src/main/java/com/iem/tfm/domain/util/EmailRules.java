package com.iem.tfm.domain.util;

/**
 * Clase utilitaria del dominio para poder validar el correo electrónico.
 * 
 * Requiere lo siguiente:
 * - Parte local antes de la arroba.
 * - Un dominio después de la arroba que contenga un punto.
 * - No permite espacios.
 * 
 * @author Inigo
 * @version 1.0
 */
public class EmailRules {
	
	private EmailRules() {
		// Constructor reserva por si necesitamos utilizarla dentro de la clase.
		// Previene que se pueda instanciar la clase.
	}
	
	public static boolean isValidEmployeeEmail(String email) {
		
		if (email == null) {
			return false;
		}
		
		boolean isEmailFormatCorrect = false;
		
		if (email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
			isEmailFormatCorrect = true;
		}
		
		return isEmailFormatCorrect;
	}
}