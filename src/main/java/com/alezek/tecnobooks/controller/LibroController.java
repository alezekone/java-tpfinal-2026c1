package com.alezek.tecnobooks.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alezek.tecnobooks.model.Libro;
import com.alezek.tecnobooks.service.LibroService;



@RestController // Spring - Indica que esta clase es un controlador REST.
@RequestMapping("/libros")  // Spring - Define la ruta "base" para las solicitudes HTTP relacionadas con libros.
@CrossOrigin(origins={"http://localhost:5500","http://127.0.0.1:5500"}) // Spring - Permite solicitudes desde el front-end. 
                                                                        // Así es como el servidor indica que sus recursos
                                                                        // pueden ser compartidos con el front-end.
public class LibroController { 
    
    private final LibroService service;

    // Inyección de dependencias por constructor.
    // Spring se encargará de inyectar la instancia de
    // LibroService.
    public LibroController(LibroService service) {
        this.service = service;
    }

    // ResponseEntity representa toda la respuesta HTTP:
    // código de estado, cabeceras y cuerpo.
    @GetMapping // Spring - Indica que manejará solicitudes HTTP GET a la ruta "/libros".
    public ResponseEntity<List<Libro>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}") // Spring - Indica que manejará solicitudes HTTP GET a la ruta "/libros/{id}".
    public ResponseEntity<Libro> obtenerPorId(@PathVariable int id) {
    /*
        try {
            return ResponseEntity.ok(service.obtenerPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); 
            // Responde 404 sin el cuerpo de la respuesta.
        }
    */
       // Luego de implementar el manejo de excepciones centralizado,
       // con @ControllerAdvice, lo anterior se transforma en 
       // lo siguiente: 
       return ResponseEntity.ok(service.obtenerPorId(id));
       // Es decir, si el LibroService arroja una excepción, la manejará
       // centralizadamente el @ControllerAdvice con el @ExceptionHandler 
       // que corresponda.
    }

    // @RequestBody: Annotation indicating a method parameter should be
    // bound to the body of the web request. 
    @PostMapping
    public ResponseEntity<Libro> guardar(@RequestBody Libro libro) {
    /* 
        try {
            Libro libroGuardado = service.guardar(libro);
            return ResponseEntity.status(HttpStatus.CREATED).body(libroGuardado);
            // HttpStatus es un enum con todos los códigos de estado HTTP.
            // 201 Created: La solicitud se ha cumplido y ha dado lugar a la
            // creación de un nuevo recurso.
        } catch (TituloInvalidoException | PrecioInvalidoException e) {
            // Lo de arriba se llama "multi-catch".
            return ResponseEntity.badRequest().build(); 
            // Responde 400 sin el cuerpo de la respuesta.
        }
    */
        // Luego de implementar el manejo de excepciones centralizado,
        // con @ControllerAdvice, lo anterior se transforma en 
        // lo siguiente: 
        Libro libroGuardado = service.guardar(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroGuardado);
    }

    // Al hacer el PUT desde Postman, hay que enviar el JSON completo del libro,
    // incluyendo el id.
    // Destaco que el código de error (400, 200, 404, etc.) es fundamental, el front-end
    // lo debe usar para determinar qué pasó con su solicitud. Es importante devolver el 
    // código de error correcto.
    @PutMapping("/{id}")    // 200 OK si existe, 404 si no existe, 400 si los datos son inválidos.
    public ResponseEntity<Libro> actualizar(@PathVariable int id, @RequestBody Libro libro) {
    /*
        try {
            Libro libroExistente = service.obtenerPorId(id);
            libroExistente.setTitulo(libro.getTitulo());
            libroExistente.setPrecio(libro.getPrecio());
            return ResponseEntity.ok(libroExistente);
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        } catch (TituloInvalidoException | PrecioInvalidoException e) {
            return ResponseEntity.badRequest().build();
        }
    */
        // Luego de implementar el manejo de excepciones centralizado,
        // con @ControllerAdvice, lo anterior se transforma en:
        // Libro libroExistente = service.obtenerPorId(id);
        //     libroExistente.setTitulo(libro.getTitulo());
        //     libroExistente.setPrecio(libro.getPrecio());
        //     return ResponseEntity.ok(libroExistente);
        // Pasé la lógica de actualización (las 4 líneas
        // anteriores que aún quedabanacá) al servicio,
        // para que el controlador quede más limpio y simple.
        // La lógica de negocio debe estar en el servicio,
        // no en el controlador.
        // El controller, sólo debe recibir la solicitud HTTP,
        // enventualmente validar (cuando pongamos el hibernate validator),
        // llamar al servicio y devolver la respuesta HTTP.
        Libro libroActualizado = service.actualizar(id, libro);
        return ResponseEntity.ok(libroActualizado); 

    }

    @DeleteMapping("/{id}") // 200 OK si existe, 404 si no existe.
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
    /*    
        try {
            service.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    */
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }

}
