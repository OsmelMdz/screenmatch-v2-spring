# Curso Java: Trabajar con lambdas, streams y Spring Framework

Este repositorio contiene el proyecto **Screenmatch** versión Spring, desarrollado como parte del curso **"Java: Trabajar con lambdas, streams y Spring Framework"** impartido por **Génesys Rondón** a través de **Alura Latam**.

En este curso, profundicé en el uso del framework Spring en proyectos de línea de comandos, el procesamiento funcional de colecciones con Java Streams, la deserialización de datos utilizando Jackson, y la implementación de buenas prácticas de programación funcional con expresiones lambda.

## Descripción
Durante el curso, aprendí a aplicar los siguientes conceptos clave:

- Uso de Streams en Java: Aplicación de operaciones como `map`, `filter`, `sorted`, `collect`, `peek` y `limit` para manipular colecciones de datos de forma eficiente y funcional.
- Funciones lambda: Uso de funciones anónimas para escribir código más conciso y expresivo.
- Deserialización de JSON: Uso de la biblioteca Jackson para convertir respuestas en formato JSON en objetos Java.
- Manejo de fechas: Conversión de cadenas de texto a objetos `LocalDate` y formateo de fechas para su presentación.
- Buenas prácticas de programación funcional: Uso de operaciones inmutables, estadísticas agregadas y transformaciones limpias.
- Uso de colecciones avanzadas: Agrupación de datos con `Collectors.groupingBy`, cálculo de promedios y uso de `DoubleSummaryStatistics`.

Como parte del proyecto, desarrollé una aplicación que permite buscar información de series a través de la API de OMDB, consultar sus temporadas, analizar sus episodios y calcular estadísticas como los episodios mejor evaluados por temporada o desde una fecha determinada.

## Tecnologías utilizadas
- **Java**: Lenguaje de programación principal utilizado.
- **JDK 17.0.6**: Versión del Java Development Kit empleada.
- **IntelliJ IDEA**: IDE utilizado para el desarrollo.
- **Spring Framework (CLI)**: Utilizado para estructurar el proyecto en un contexto real.
- **Jackson**: Biblioteca utilizada para deserializar datos JSON.
- **API OMDB**: Servicio utilizado para obtener datos de series y episodios.

## Requisitos
- Tener instalado [Java JDK 17.0.6](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- IntelliJ IDEA u otro IDE compatible con Java.
- Conexión a internet para consumir la API de OMDB.
- Obtener una clave de API de OMDB en https://www.omdbapi.com/apikey.aspx.

## Cómo ejecutar el proyecto
1. Clona este repositorio en tu máquina local.
2. Abre el proyecto con IntelliJ IDEA.
3. Asegúrate de tener añadida la biblioteca Jackson en el classpath.
4. Reemplaza la clave de la API en el código por una válida: `&apikey=[tu_api_key]`.
5. Ejecuta la clase `Principal.java` dentro del paquete `com.aluracursos.screenmatch.principal`.
6. Sigue las instrucciones de la consola para ingresar el nombre de una serie.

## Lo aprendido en este curso
- Manipular flujos de datos de forma funcional con Java Streams.
- Aplicar expresiones lambda para simplificar operaciones.
- Obtener, procesar y analizar datos provenientes de APIs REST.
- Diseñar menús interactivos para aplicaciones de consola.
- Aplicar estadísticas descriptivas sobre colecciones de datos.
- Integrar Spring en proyectos de línea de comandos como base para aplicaciones más robustas.

## Instructor
**Génesys Rondón**  
Ingeniera de Sistemas, especializada en desarrollo web Back End.  
Con experiencia en Java, C#, C++, JavaScript, Node.js, Spring y ASP.NET Core.  
Amante de los gatos, los videojuegos y la literatura clásica.  
LinkedIn: https://www.linkedin.com/in/genesysrondon914762182/  
GitHub: https://github.com/genesysrm
