package com.iem.tfm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Clase principal de arranque de la aplicación Gestión de Vacaciones.
 * 
 * <p>
 * Esta clase contiene el método {@code main}, punto de entrada de la
 * aplicación, desde donde se lanza todo el ecosistema Spring.
 * </p>
 * 
 * <ul>
 * <li>{@link SpringBootApplication} activa la configuración automática de
 * Spring Boot.</li>
 * <li>{@link ComponentScan} busca componentes en el paquete base y
 * subpaquetes.</li>
 * <li>{@link EnableMongoRepositories} habilita el uso de repositorios
 * MongoDB.</li>
 * </ul>
 * 
 * @author Inigo
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan
@EnableMongoRepositories
public class GestionVacacionesApplication {

	/**
	 * Punto de entrada principal para lanzar la aplicación.
	 * 
	 * @param args argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(GestionVacacionesApplication.class, args);
	}
}