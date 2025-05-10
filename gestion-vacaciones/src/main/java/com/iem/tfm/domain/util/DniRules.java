package com.iem.tfm.domain.util;

/**
 * Clase utilitaria del dominio para poder validar los DNI.
 * 
 * Requiere lo siguiente:
 * - Longitud de 8 caracteres.
 * - Contener una letra al final.
 * 
 * @author Inigo
 * @version 1.0
 */
public class DniRules {
	
	private DniRules() {
		// Constructor reserva por si necesitamos utilizarla dentro de la clase.
		// Previene que se pueda instanciar la clase.
	}
	
	public static boolean isValidEmployeeDni(String dni) {
		
		if (dni == null) {
			return false;
		}
		
		boolean isDniFormatCorrect = false;
		
		if (dni.matches("\\d{8}[A-Z]")) {
			isDniFormatCorrect = true;
		}
		
		return isDniFormatCorrect;
	}
}