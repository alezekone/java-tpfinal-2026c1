package com.alezek.tecnobooks.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice   // Spring - Indica que esta clase manejará
                    // excepciones de manera global para todos
                    // los controladores.
public class GlobalExceptionHandler {

    @ExceptionHandler(TituloInvalidoException.class) // Spring - Indica que este método manejará
                                                     // excepciones de tipo TituloInvalidoException.
                                                     // Es decir, se ejecutará cuando se lance dicha 
                                                     // excepción en cualquier parte de la aplicación.
    public ResponseEntity<String> handleTituloInvalidoException(TituloInvalidoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(PrecioInvalidoException.class)
    public ResponseEntity<String> handlePrecioInvalidoException(PrecioInvalidoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(LibroNoEncontradoException.class)
    public ResponseEntity<String> handleLibroNoEncontradoException(LibroNoEncontradoException e) {
        return ResponseEntity.status(404).body(e.getMessage()) ;
    }
    
}
