package com.alezek.tecnobooks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok - Genera getters, setters, toString, equals y hashCode.
@NoArgsConstructor // Lombok - Genera un constructor sin argumentos.
@AllArgsConstructor // Lombok - Genera un constructor con todos los argumentos.

public class Libro {
    private Integer id;
    private String titulo;
    private double precio;
}
