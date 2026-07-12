package com.alezek.tecnobooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alezek.tecnobooks.model.Libro;

// Definimos una *interfaz* que *extiende* de JpaRepository 
// (es decir, hereda de otra interface!!!).
// Nuestra interface hereda entonces save, findById, findAll, etc.
// de JpaRepository, y además nos permite definir nuestros propios
// métodos de consulta.
// El primer parámetro de JpaRepository es la clase de entidad (Libro),
// y el segundo es el tipo de de su PK (Integer).
// Spring genera la implementación de esta interface en tiempo de
// ejecución !!! (no escribiremos SQL), y la registra como
// un *bean* de Spring, para que podamos inyectarla
// en nuestros controladores y servicios.

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    
}
