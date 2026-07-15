
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

## Build & Puesta en marcha

### Build con Maven

Desde la raíz del proyecto (donde está el pom.xml):
```bash
mvn clean
```
Lo anterior limpiará el resultado de compilaciones anteriores.

Luego, como resulta necesario pasarle unas variables de ambiente, lo que debemos hacer dependerá de nuestro entorno. Siendo DB_PASSWORD la contraseña de root de nuestra BD MySQL, haremos lo siguiente:

En bash:
```
    DB_PASSWORD="valor" mvn clean package  
```
En PowerShell:
```
    $env:DB_PASSWORD="valor"; mvn clean package
```
En CMD:
```
    set DB_PASSWORD=valor && mvn clean package
```
Lo anterior descargará dependencias, ejecutará mis tests y generará un archivo .jar (o .war, según lo indicado en el pom.xml) dentro del directorio target/  
Nota: en los comandos anteriores, el "clean" no hace falta, pues ya se hizo en el paso anterior. Lo dejo para destacar que ambos comandos -clean y package- se pueden correr juntos. 

### Ejecución con Maven instalado
Posicionado en la raíz del proyecto:
```code
mvn spring-boot:run
```
Observemos que pusimos el valor de variable de entorno (DB_PASSWORD) al hacer el build y ya no la necesitamos al ejecutar el proyecto. Una curiosidad: si no la ponemos al hacer el build, da error (supongo que los test que imagino tiene definidos por default fallan).

### Ejecución sin Maven instalados
Posicionado en la raíz del proyecto:
```code
./mvnw spring-boot:run
```
### Ejecución desde java
Posicionado en el directorio target/:

java -jar tecnobooks-0.0.1-SNAPSHOT.jar

Y Si tuvieramos que pasarle una variable de entorno, resulta importante hacerlo antes del -jar del siguiente modo:

java -DDB_PASSWORD=mi_db_root_password -jar tecnobooks-0.0.1-SNAPSHOT.jar

Nota: la variable de entorno pasada por esta vía, tendrá prioridad por sobre cualquier otra definición previa.

### Seteo de variables en archivo de configuración
En la raíz del proyecto (al mismo nivel del pom.xml) encontramos el archivo ".env.properties.example". Podemos asignar los valores correspondientes a nuestro entorno para las variables que allí se indican, y luego renombrar el archivo como ".env.properties". Hecho esto, ya no necesitaremos pasar las variables por los medios indicados en los comandos precedentes.
Durante el desarrollo, yo trabajo en mi propio .env.properties, pero no lo subo a github (lo incluí en .gitignore) para no difundir mis credenciales.

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

## Pendientes
1.- Hibernate / Jakarta Validator.
1.- Registrarse/loggearse autenticandose con user/password  
2.- Registrarse/loggearse con redes sociales (google/facebook/linkedin/github)  
3.- Autorización y roles. Control de acceso por roles (RBAC) con @PreAuthorize para restringir endpoints según el rol(ADMIN, USER, etc).
4.- Agregar entidades y definir relaciones en JPA/Hibernate  
    (OneToOne, OneToMany, ManyToMany, etc).  
5.- Agregar Swagger (OpenAPI) para documentar y facilitar pruebas con endpoints.  
6.- Agragar opciones de ordemaniento, filtrado y paginación.  
7.- Verificación por mail para el registro de nuevos usuarios.  
8.- Desarrollar un Front-end para esta API.  
9.- Agregar pasarela de pagos (Stripe, MercadoPago, PayPal, etc.)  
10.- Mejorar el readme y, posiblemente, documentar aspectos del desarrollo del proyecto en una wiki dentro GitHub. 
11.- Caché con Redis, agregando @Cacheable para consultas pesadas que no cambien con frecuencia.
12.- Usar APIs de terceros
13.- Permitir el subir imágenes.
14.- Almacenamiento de imágenes en AWS S3, MinIO, etc.
15.- Dockerizar la App.
16.- Darle arquitectura de microservicios.
17.- Integrar Kafka para comunicar dos microservicios.
18.- Testing
19.- Pruebas de Integración con Testcontainers: Levantar una base de datos real (PostgreSQL/MySQL) en un contenedor Docker para pruebas automatizadas.
20.- Hacer pruebas con BDs no relacionales, tales como MongoDB.
21.- Migrar a PostgreSQL, que tiene un poco de todo (relacional, no relacional, vectorial, etc).
22.- Observabilidad con Spring Actuator (Health Check)
23.- Métricas con Prometheus y Grafana. Armar un dashboard.
24.- Trazabilidad con Micrometer Tracing, para seguir una petición desde que entra en la API hasta que pasa por la BD u otros microservicios.
25.- Integrar Flyway para versionar la BD.
26.- Pipeline de CI/CD básico con GitHub Actions.
27.- Auditoría con Spring Data JPA, utilizando @CreatedBy, @LastModifiedDate y @Audited (Hibernate Envers) para registrar automáticamente qué usuario creó o modificó un registro y cuándo lo hizo.
28.- Patrón Circuit Breaker (Resilience4j): si la app consume un servicio externo que falla, evitaremos que nuestra app se congele, dando una respuesta alternativa (fallback).
29.- Limitador de tasa, para proteger los endpoints bloqueando usuarios o IPs que hagan demasiadas peticiones por minuto.
