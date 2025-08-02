# Curso Java: Persistencia de datos y consultas con Spring Data JPA

Este repositorio contiene el proyecto **Screenmatch** versión Spring v2, que incluye nuevas funcionalidades, desarrollado como parte del curso **"Java: persistencia de datos y consultas con Spring Data JPA"** impartido por **Génesys Rondón** a través de **Alura Latam**.

En este curso, profundicé en el uso del framework Spring Data JPA para la persistencia de datos, modelado de entidades relacionales, integración con APIs externas y buenas prácticas de seguridad utilizando variables de entorno. La aplicación permite buscar, almacenar y analizar series y episodios consultando la API de OMDb, y almacenar los resultados en una base de datos PostgreSQL.

## Descripción
Durante el curso, aprendí a aplicar los siguientes conceptos clave:

- Consultar series por título usando la API de OMDb.
- Traducir automáticamente la sinopsis de una serie (vía ChatGPT o Gemini).
- Guardar series y episodios en una base de datos relacional.
- Consultar series por categoría, evaluación o número de temporadas.
- Ver el top 5 de series y episodios mejor evaluados.
- Buscar episodios por nombre exacto o parcial.

## Tecnologías utilizadas
- **Java**: Lenguaje de programación principal utilizado.
- **JDK 17.0.6**: Versión del Java Development Kit empleada.
- **IntelliJ IDEA**: IDE utilizado para el desarrollo.
- **Spring Framework (CLI), Spring Boot & Spring Data JPA**: Utilizado para estructurar el proyecto en un contexto real.
- **PostgreSQL 17**: como base de datos.
- **Jackson**: Biblioteca utilizada para deserializar datos JSON.
- **OpenAI API / Gemini**: para la traducción de sinopsis.
- **API OMDB**: Servicio utilizado para obtener datos de series y episodios.
- **Variables de entorno gestionadas en `resources/application.properties`**: para proteger claves y configuraciones.

## Requisitos
- Tener instalado [Java JDK 17.0.6](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- Tener instalado [PostgreSQL 17](https://www.postgresql.org/download/).
- IntelliJ IDEA u otro IDE compatible con Java.
- Conexión a internet para consumir la API de OMDB.
- Obtener una clave de API de OMDB en https://www.omdbapi.com/apikey.aspx.

## Cómo ejecutar el proyecto
1. Clona este repositorio en tu máquina local.
2. Abre el proyecto con IntelliJ IDEA.
3. Asegúrate de tener añadida la biblioteca Jackson en el classpath.
4. Reemplaza la clave de la API en tu variable de entorno local por una válida, usando: `&apikey=[tu_api_key]`.
5. Ejecuta la clase `Principal.java` dentro del paquete `com.aluracursos.screenmatchv2.principal`.
6. Sigue las instrucciones de la consola para ingresar el nombre de una serie.

## Lo aprendido en este curso
- Crear y modelar entidades JPA.
- Mapear relaciones 1:N entre entidades (Serie y Episodio).
- Persistir datos en una base de datos PostgreSQL usando Spring Data.
- Construir consultas derivadas y personalizadas con JPQL.
- Proteger información sensible con variables de entorno.
- Consumir e integrar múltiples APIs externas.
- Desarrollar aplicaciones en consola con menús interactivos y búsqueda avanzada.

## Instructor
**Génesys Rondón**  
Ingeniera de Sistemas, especializada en desarrollo web Back End.  
Con experiencia en Java, C#, C++, JavaScript, Node.js, Spring y ASP.NET Core.  
Amante de los gatos, los videojuegos y la literatura clásica.  
LinkedIn: https://www.linkedin.com/in/genesysrondon914762182/  
GitHub: https://github.com/genesysrm
