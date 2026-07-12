package com.alezek.tecnobooks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok - Genera getters, setters, toString, equals y hashCode.
@NoArgsConstructor // Lombok - Genera un constructor sin argumentos.
@AllArgsConstructor // Lombok - Genera un constructor con todos los argumentos.
@Entity // JPA - Indica que esta clase es una entidad de la base de datos.
@Table(name = "libros") // JPA - Indica el nombre de la tabla en la base de datos.
public class Libro {
    @Id // JPA - Indica que es la PK.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA - Indica que el valor se generará automáticamente.
    private Integer id; // JPA - El tipo Integer permite que sea null, lo cual es útil para cuando se crea un nuevo libro y aún no tiene ID.
    @Column(name="nombre", nullable = false, length=255) // JPA.
    private String titulo;
    @Column(name="precio", nullable = false) // JPA.
    private double precio;
    // NOTA 1: No es necesario poner @Column si el nombre
    // de la columna es igual al nombre del atributo.
    // NOTA 2: Si no recibe nombre, o su nombre es de más de 255
    // caracteres, la BD rechazará la inserción del registro.
}
