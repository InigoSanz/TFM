package com.iem.tfm.domain.util;

/**
 * Clase utilitaria del dominio para validar el DNI de un empleado.
 * <p>
 * Reglas básicas:
 * <ul>
 *   <li>Debe tener 8 dígitos seguidos de una letra (mayúscula o minúscula).</li>
 *   <li>No se admiten valores nulos.</li>
 * </ul>
 * </p>
 * 
 * @author Inigo
 * @version 1.0.1
 */
public class DniRules {
	
	/**
	 * Constructor privado para evitar que se instancie esta clase utilitaria.
	 */
	private DniRules() {
	}
	
	/**
	 * Varifica si el formato del DNI es válido.
	 * 
	 * @param dni DNI a validar
	 * @return {@code true} si el formato es correcto, {@code false} si no
	 */
	public static boolean isValidEmployeeDni(String dni) {
		
		if (dni == null) {
			return false;
		}
		
		boolean isDniFormatCorrect = false;
		
		// Expresión regular, 8 números seguidos de 1 letra (minúscula o mayúscula)
		if (dni.matches("\\d{8}[A-Za-z]")) {
			isDniFormatCorrect = true;
		}
		
		return isDniFormatCorrect;
	}
}