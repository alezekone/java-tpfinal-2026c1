Profe,  
este es aún un trabajo en progreso, al cual le quiero agregar mucha más funcionalidad.  
Creo que cumple los requisitos mínimos exigidos para aprobar pero , provechando que la fecha de entrega se llevó al 15 de Julio, le agradecería me permita seguir trabajando en este TP hasta esa fecha, antes de corregirlo.  
Desde ya, muchas gracias.

## Observaciones
Debe crearse la base de datos con nombre "tecnobooks" desde el propio MySQL Workbench o equivalente. Se propone utilizar el siguiente comando:  
```sql
CREATE DATABASE tecnobooks DEFAULT CHARACTER SET='utf8mb4';
```

## Características técnicas agregadas en cada commit

+ Commit 01 [...c1f6d]: 
    - Commit inicial.
    - CRUD Básico, en memoria con ArrayList, con solo una entidad.
    - Organización en paquetes según convención: model, controller y service (repository se agregó en commit 3).
    - Excepciones personalizadas.
+ Commit 02 [...f810b]: 
    - Se agrega CORS en LibroController. 
    - Se incorpora manejo centralizado de excepciones agregando clase GlobalExceptionHandler con anotación @ControllerAdvice.
+ Commit 03 [...]: 
    - Se introduce JPA con Hibernate persistiendo la (hasta el momento) única entidad en una BD MySQL. (Por ahora, hardcodeo la contraseña de root en application.properties, razón por la cual la borro antes de hacer el commit, hasta el momento en que incorpore un .env o mecanismo similar).

## Endpoints

Método: GET  
URL:    http://localhost:8080/libros  
Body:  

Método: POST  
URL:    http://localhost:8080/libros  
Body:   {"titulo": "Rayuela", "precio": 1900}  

Método: DELETE  
URL:    http://localhost:8080/libros/3  
Body:  

Método: PUT  
URL:    http://localhost:8080/libros/2  
Body:   {"id": 7, "titulo": "Los mejores del mundo", "precio": 1900}  

