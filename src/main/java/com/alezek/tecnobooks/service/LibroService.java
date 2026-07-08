package com.alezek.tecnobooks.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alezek.tecnobooks.exception.LibroNoEncontradoException;
import com.alezek.tecnobooks.exception.PrecioInvalidoException;
import com.alezek.tecnobooks.exception.TituloInvalidoException;
import com.alezek.tecnobooks.model.Libro;

// Acá trabajaremos con un ArrayList. Más tarde haremos la conexión
// con la base de datos.

// NOTA: Lo importante acá es que la clase Service está manejando
// las excepciones, y no el controlador. El controlador solo se encarga de
// recibir la petición, llamar al servicio y devolver la respuesta.
// Luego, cuando "centralicemos el manejo de excepciones", estas
// ya se filtrarán en el contralador y nunca llegarán esas peticiones
// al servicio.

@Service // Spring - Para indicar que esta clase es un servicio.
public class LibroService {
    private final List<Libro> libros = new ArrayList<>();
    // NOTA: "libros" puede ser final, porque no vamos a cambiar
    // la referencia del ArrayList, aún cuando sí podemos modificar
    // su contenido.
    private int contadorId = 1; // Variable para generar IDs automáticos.

    // Constructor.
    public LibroService() {
        libros.add(new Libro(contadorId++, "Java para principiantes", 29.99));
        libros.add(new Libro(contadorId++, "Spring Boot en acción", 39.99));
        libros.add(new Libro(contadorId++, "Patrones de diseño en Java", 49.99));
    }

    public List<Libro> listarTodos() {
        return libros;
    }

    public Libro obtenerPorId(int id) {
        return libros.stream()
                .filter(libro -> libro.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LibroNoEncontradoException("Libro " + id + " no encontrado."));

        // Acá, si no encontrabamos el libro,
        // existían dos posibilidades:
        // (1)  .orElse(null)      // Esto retornaría null.
        // (2)  .orElseThrow(() -> new LibroNoEncontradoException("Libro con ID " + id + " no encontrado.")); // Esto lanzaría una excepción.
        // .orElseThrow() viene de Java 8 y viene de la clase Optional, es decir, java.util.Optional.
        // Optional es una clase que representa un valor que puede existir o no.
    }

    public Libro guardar(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            throw new TituloInvalidoException("El título del libro no puede estar vacío.");
        }

        if (libro.getPrecio() < 0) {
            throw new PrecioInvalidoException("El precio del libro no puede ser negativo.");
        }

        libro.setId(contadorId++);
        libros.add(libro);
        return libro;
    }

    public void eliminar(int id) {
        Libro libro = obtenerPorId(id);
        // La linea anterior puede lanzar una excepción si el libro no existe.
        libros.remove(libro); 
        // La linea anterior, usará el método equals() de la clase Libro,
        // que fue generado por Lombok.
        // Yo creo que usará más bien el método hashCode() de la clase Libro,
        // que también fue generado por Lombok, y luego verificará con
        // equals() que sea el mismo objeto.   
    }

}
