package com.iem.tfm.domain.util;

/**
 * Clase utilitaria del dominio para validar el correo electrónico de un
 * empleado.
 * <p>
 * Reglas básicas:
 * <ul>
 * <li>Debe tener una parte local antes de la arroba (@).</li>
 * <li>Debe contener un dominio con al menos un punto.</li>
 * <li>No puede contener espacios en blanco.</li>
 * </ul>
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
public class EmailRules {

	/**
	 * Constructor privado para evitar instanciación.
	 */
	private EmailRules() {
	}

	/**
	 * Verifica si el formato del email es válido.
	 * 
	 * @param email Correo electrónico a validar
	 * @return {@code true} si el formato es correcto, {@code false} si no
	 */
	public static boolean isValidEmployeeEmail(String email) {

		if (email == null) {
			return false;
		}

		boolean isEmailFormatCorrect = false;

		// Expresión regular, texto@dominio.extensión (sin espacios)
		if (email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
			isEmailFormatCorrect = true;
		}

		return isEmailFormatCorrect;
	}
}