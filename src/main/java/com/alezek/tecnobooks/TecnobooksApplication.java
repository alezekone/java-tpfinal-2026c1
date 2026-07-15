package com.alezek.tecnobooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alezek.tecnobooks.model.Libro;
import com.alezek.tecnobooks.service.LibroService;

@SpringBootApplication
public class TecnobooksApplication {

	// Spring - @Value nos permite injectar valores
	// en variables, parametros de constructores o 
	// argumentos de métodos desde fuentes externas
	// tales como property files, system variables o
	// environment configs.
	// Con ":" puedo especificar un valor por defecto,
	// a modo de fallback.
	@Value("${spring.datasource.password:SinContrasena}")
	private String dbPasswordInUse;

	// Crear la instancia del logger SLF4J
    private static final Logger logger = LoggerFactory.getLogger(TecnobooksApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(TecnobooksApplication.class, args);
	}

	// Cargamos algunos libros de ejemplo al iniciar la aplicación.
	// Para esto usamos CommandLineRunner, una interface funcional que 
	// permite ejecutar código automáticamente justo luego de que 
	// la aplicación arranca y el context de Spring está completamente 
	// inicializado.
	// CommandLineRunner es utilizado para tareas de configuración inicial.
	// Ver: https://www.youtube.com/watch?v=WHkkeMr5muI en donde nos muestra
	// que, siendo una interface funcional, tengo dos formas de invocarla:
	// (1) sobrecargando el método run, o
	// (2) como expresión lambda, como hicimos acá. Muy interesante.
	@Bean
	public CommandLineRunner cargarDatosIniciales(LibroService libroService) {
		return args -> {
			logger.debug("Toma como contraseña de la DB: {}", dbPasswordInUse);
			// System.out.println("***************************************");
			// System.out.println("******* Viendo si esto funciona *******");
			// System.out.println("***************************************");
			if(libroService.listarTodos().isEmpty()) {
				// Cargamos unos libros solo si la tabla de libros
				// está vacía en la BD.
				libroService.guardar(Libro.builder().titulo("Java para principiantes").precio(29.99).build());
				libroService.guardar(Libro.builder().titulo("Spring Boot en acción").precio(39.99).build());
				libroService.guardar(Libro.builder().titulo("Patrones de diseño en Java").precio(49.99).build());
				libroService.guardar(Libro.builder().titulo("Nos vemos en Disney").precio(1500).build());
				libroService.guardar(Libro.builder().titulo("Menos mal que me fui").precio(2500).build());
				libroService.guardar(Libro.builder().titulo("Buena idea").precio(3500).build());
				libroService.guardar(Libro.builder().titulo("Y sí, campeón").precio(2000).build());
				// Nota: El ID no se pasa porque es autogenerado por la BD. Por eso usamos el patrón Builder de Lombok.
				// No obstante, el profe usó la forma tradicional de crear objetos, con new pero usando un constructor
				// sin el ID. No sé en qué momento agregó un constructor sin el ID.
				// Yo intenté usar el constructor full y pasarle un ID cualquiera,
				// sabiendo que tenía la tabla vacía, pero pinchaba. 
			}
		};
	}
}
