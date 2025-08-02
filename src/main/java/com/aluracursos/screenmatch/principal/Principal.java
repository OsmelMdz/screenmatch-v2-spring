package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.*;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi;
    private ConvierteDatos conversor = new ConvierteDatos();
    private SerieRepository repositorio;
    private List<Serie> series;
    private Optional<Serie> serieBuscada;

    public Principal(SerieRepository repository, ConsumoAPI consumoApi) {
        this.repositorio = repository;
        this.consumoApi = consumoApi;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
            Selecciona una opción del menú:
            1 - Buscar series desde la web (OMDb)
            2 - Mostrar todas las series guardadas (BD)
            3 - Buscar una serie por título (BD)
            4 - Buscar series por categoría (BD)
            5 - Filtrar series por temporadas y evaluación (BD)
            6 - Mostrar Top 5 series mejor evaluadas (BD)
            7 - Ver episodios de una serie específica (BD + OMDb)
            8 - Buscar episodios por título (BD)
            9 - Mostrar Top 5 episodios de una serie (BD)
            0 - Salir de la aplicación
            """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    mostrarSeriesBuscadas();
                    break;
                case 3:
                    buscarSeriesPorTitulo();
                    break;
                case 4:
                    buscarSeriesPorCategoria();
                    break;
                case 5:
                    filtrarSeriesPorTemporadaYEvaluacion();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscarEpisodioPorSerie();
                    break;
                case 8:
                    buscarEpisodioPorTitulo();
                    break;
                case 9:
                    buscarTop5Episodios();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }


    private void buscarSerieWeb() {
        try {
        DatosSerie datos = getDatosSerie();
        Serie serie = new Serie(datos);
        repositorio.save(serie);
        System.out.println(datos);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("La serie no fue guardada debido a una categoría no registrada.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private void mostrarSeriesBuscadas() {
        series = repositorio.findAll();
        if (!cargarSeriesDesdeBD()) return;
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void  buscarSeriesPorTitulo(){
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
         serieBuscada = repositorio.findByTituloContainsIgnoreCase(nombreSerie);
        if (serieBuscada.isPresent()){
            System.out.println("La serie buscada es: "+ serieBuscada.get());
        } else {
            System.out.println("Serie no encontrada");
        }
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Escriba el género/categoría de la serie que desea buscar ");
        var genero = teclado.nextLine();

        Categoria categoria;
        try {
            categoria = Categoria.fromEspanol(genero);
        } catch (IllegalArgumentException e) {
            System.out.println("Categoría no válida.");
            mostrarCategoriasDisponiblesEnBD();
            return;
        }

        List<Serie> seriesPorCategoria = repositorio.findByGenero(categoria);

        if (seriesPorCategoria.isEmpty()) {
            System.out.println("No se encontraron series guardadas en la categoría \"" + genero + "\".");
            mostrarCategoriasDisponiblesEnBD();
        } else {
            System.out.println("Las series de la categoría \"" + genero + "\" son:");
            seriesPorCategoria.forEach(System.out::println);
        }
    }

    private void mostrarCategoriasDisponiblesEnBD() {
        System.out.println("Categorías actualmente disponibles en la base de datos:");
        List<Serie> seriesEnBD = repositorio.findAll();
        Set<String> categoriasDisponibles = seriesEnBD.stream()
                .map(s -> s.getGenero().getCategoriaEspanol())
                .collect(Collectors.toSet());
        categoriasDisponibles.forEach(cat -> System.out.println("- " + cat));
    }

    private void filtrarSeriesPorTemporadaYEvaluacion(){
        System.out.println("¿Con cuántas temporadas mínimo debe contar la serie?");
        var totalDeTemporadas = teclado.nextInt();
        teclado.nextLine();
        System.out.println("¿Cuál es la evaluación mínima deseada?");
        var evaluacion = teclado.nextDouble();
        teclado.nextLine();
        List<Serie> filtroSeries = repositorio.seriesPorTemporadasYEvaluacion(totalDeTemporadas, evaluacion);
        if (filtroSeries.isEmpty()) {
            System.out.println("No se encontraron series con esos criterios.");
        } else {
            System.out.println("*** Series que cumplen con los filtros ***");
            filtroSeries.forEach(s ->
                    System.out.printf("- %s | Evaluación: %.1f%n", s.getTitulo(), s.getEvaluacion()));
        }
    }

    private void buscarTop5Series(){
        List<Serie> topSeries = repositorio.findTop5ByOrderByEvaluacionDesc();
        if (topSeries.isEmpty()) {
            System.out.println("No hay series guardadas para mostrar el top.");
        } else {
            System.out.println("Top 5 de las series mejor evaluadas:");
            topSeries.forEach(s ->
                    System.out.println("- " + s.getTitulo() + " | Evaluación: " + s.getEvaluacion()));
        }
    }

    private void buscarEpisodioPorSerie() {
        mostrarSeriesBuscadas();
        System.out.println("Escribe el nombre la serie de la cual quieres ver los episodios");
        var nombreSerie = teclado.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();
        if(serie.isPresent()){
            var serieEncontrada = serie.get();
            if (!serieEncontrada.getEpisodios().isEmpty()) {
                System.out.println("Esta serie ya tiene episodios guardados. ¿Deseas volver a guardarlos? (s/n)");
                String respuesta = teclado.nextLine();
                if (!respuesta.equalsIgnoreCase("s")) return;
            }
            List<DatosTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalDeTemporadas(); i++) {
                var json = consumoApi.obtenerDatosPorTituloYTemporada(serieEncontrada.getTitulo(), i);
                DatosTemporadas datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporada);
            }
            temporadas.forEach(System.out::println);
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }

    }

    private void buscarEpisodioPorTitulo() {
        System.out.println("Escribe el título del episodio que deseas buscar:");
        var nombreEpisodio = teclado.nextLine();

        List<Episodio> episodiosExactos = repositorio.episodiosPorNombreExacto(nombreEpisodio);
        if (!episodiosExactos.isEmpty()) {
            if (episodiosExactos.size() > 1) {
                System.out.println("El episodio \"" + nombreEpisodio + "\" se encuentra exactamente en " + episodiosExactos.size() + " series:");
            }

            episodiosExactos.forEach(e -> System.out.printf(
                    "Serie: %s | Temporada: %d | Episodio: %d | Evaluación: %.1f%n",
                    e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getEvaluacion()));
            return;
        }

        List<Episodio> episodiosParciales = repositorio.episodiosPorNombre(nombreEpisodio);
        if (episodiosParciales.isEmpty()) {
            System.out.println("No se encontraron episodios con ese nombre.");
            return;
        }
        System.out.println("No se encontraron episodios con título "+nombreEpisodio+", pero estas son algunas coincidencias parciales:");
        episodiosParciales.forEach(e -> System.out.printf(
                "Serie: %s | Temporada: %d | Episodio: %d | Título: %s | Evaluación: %.1f%n",
                e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo(), e.getEvaluacion()));
    }

    private void buscarTop5Episodios(){
        buscarSeriesPorTitulo();
        if (serieBuscada.isPresent()){
            Serie serie = serieBuscada.get();
            List<Episodio> topEpisodios = repositorio.top5Episodios(serie);
            if (topEpisodios.isEmpty()) {
                System.out.println("No hay episodios guardados para esta serie.");
            } else {
                System.out.println("Top 5 de episodios mejor evaluados de la serie "+ serie.getTitulo() +":");
                topEpisodios.forEach(e -> System.out.printf("Serie: %s | Temporada: %d | Episodio: %s | Evaluación: %.1f%n",
                        e.getSerie().getTitulo(), e.getTemporada(), e.getTitulo(), e.getEvaluacion()));
            }
        }
    }

    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar en línea:");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatosPorTitulo(nombreSerie);
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        return datos;
    }

    private boolean cargarSeriesDesdeBD() {
        series = repositorio.findAll();
        if (series.isEmpty()) {
            System.out.println("No hay series registradas en la base de datos.");
            return false;
        }
        return true;
    }
}
