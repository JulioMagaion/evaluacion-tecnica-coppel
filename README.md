API Middleware TV Maze - Examen Técnico Backend
Este repositorio contiene la solución al examen técnico para crear un API middleware que se 
comunica con los servicios de TV Maze. El proyecto fue desarrollado utilizando buenas prácticas empresariales, 
arquitectura multicapa, y componentes modernos de Spring Boot.   

Tecnologías Utilizadas
Lenguaje: JavaFramework: 
Spring Boot 3.2+Cliente HTTP: 
RestClient (Inyección de dependencias)
Base de Datos: MongoDB Atlas (Instancia gratuita sin restricción de IP)
Logs: Log4j2 (SLF4J)
DTOs: Java Records

Configuración y Ejecución
Para ejecutar este proyecto localmente:
1. Clona el repositorio.
2. Abre el archivo src/main/resources/application.properties y verifica la cadena de conexión a MongoDB Atlas. ( Sino funciona Revisa el archivo MongoConfig).
3. Ejecuta la aplicación desde tu IDE o mediante la terminal con mvn spring-boot:run.
4. El servidor iniciará en el puerto 8080.

Evidencias de Ejecución Paso a Paso 

A continuación, se presentan las capturas de pantalla que demuestran el correcto funcionamiento de cada punto solicitado en el requerimiento, 
garantizando que la solución cumple con los criterios de evaluación.   

A- Endpoint Search (Búsqueda de Shows)
Endpoint que realiza la búsqueda a partir de un criterio y retorna un arreglo con los atributos:
id, name, channel, summary, genres.   
Petición: GET /api/v1/shows/search?search_query=eureka
<img width="1197" height="844" alt="image" src="https://github.com/user-attachments/assets/e977ab57-91be-40b7-b537-e5d7946baa95" />


B- Endpoint Show y Caché en MongoDB
Endpoint que obtiene la información del show por ID, retornando el objeto completo. 
Se valida primero en la caché de Mongo; si no existe, consume el API externa y guarda el resultado.   
Petición: GET /api/v1/shows/16635

Evidencia 1 (Consumo API Externa): 
Captura de consola mostrando el log "Consumiendo API externa..." y guardando en Mongo.
<img width="1164" height="847" alt="image" src="https://github.com/user-attachments/assets/09ac2262-8912-44dd-9790-4ef74e31dcd8" />

Evidencia 2 (Caché MongoDB): Captura de consola mostrando el log "Retornando caché" en la segunda petición.
<img width="1217" height="314" alt="image" src="https://github.com/user-attachments/assets/1b245739-e0d4-496b-ae50-60ec82da07c8" />

Evidencia 3 (Atlas): Captura de MongoDB Atlas mostrando el documento guardado en la colección.
<img width="947" height="643" alt="image" src="https://github.com/user-attachments/assets/9a6a1786-0026-4ba3-8476-b8616c1d9a81" />


C- Endpoint Comments (Guardar Comentario)
Endpoint que permite guardar una calificación (0-5) y comentario en MongoDB ligados al ID del show, retornando el status de la petición.   
Petición: POST /api/v1/comments
Cuerpo: { "show_id": 16635, "comment": "Comentario de prueba del Endpoint C: Id 16635 perfecto", "rating": 5 }
<img width="1217" height="314" alt="image" src="https://github.com/user-attachments/assets/1b245739-e0d4-496b-ae50-60ec82da07c8" />

<img width="933" height="824" alt="image" src="https://github.com/user-attachments/assets/a59d3990-c25d-4278-bf77-0a5608770ecb" />
