package com.boot_Literalura.spring_Boot_Literalura.principal;

import com.boot_Literalura.spring_Boot_Literalura.model.DataAutor;
import com.boot_Literalura.spring_Boot_Literalura.model.DataLibros;
import com.boot_Literalura.spring_Boot_Literalura.model.record.Autor;
import com.boot_Literalura.spring_Boot_Literalura.model.record.FirtsDoor;
import com.boot_Literalura.spring_Boot_Literalura.model.record.Libros;
import com.boot_Literalura.spring_Boot_Literalura.repository.AutoresRepository;
import com.boot_Literalura.spring_Boot_Literalura.repository.LibrosRepository;
import com.boot_Literalura.spring_Boot_Literalura.service.ConsumoAPI;
import com.boot_Literalura.spring_Boot_Literalura.service.ConvierteDatos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI conAPI = new ConsumoAPI();
    private ConvierteDatos conve =  new ConvierteDatos();

    private Scanner teclado = new Scanner(System.in);
    private  List<DataLibros> dataLibros;
    private List<Libros> libros = new ArrayList<>();
    private final LibrosRepository librosRepository;
    private final AutoresRepository autoresRepository;

    @Autowired
    public Principal(LibrosRepository librosRepository, AutoresRepository autoresRepository){
        this.librosRepository = librosRepository;
        this.autoresRepository = autoresRepository;
    }

    public void menu(){
        int opcion = -1;

        while(opcion != 0){
            String menu = """
                    1 ->> Buscar libro.
                    2 ->> Mostrar Lista de los Libros registrados.
                    3 ->> Mostrar Lista de autores registrados.
                    4 ->> listar autores vivos en un determinado año
                    5 ->> Listar libros por Idioma.
                    6 ->> Top Libros más Descargados.
                    0 ->> Salir.
                    """;

            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion ){
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        listaDeLibros();
                        break;
                    case 3:
                        listaDeAutores();
                        break;
                    case 4:
                        determminadoAno();
                        break;
                    case 5:
                        idiomas();
                        break;
                    case 6:
                        top10Libros();
                            break;
                    case 0:
                        System.out.println("Cerrando la application . . .");
                        break;
                    default:
                        System.out.println("Digite bien el numero!");
                }
            }catch (InputMismatchException e) {
                System.out.println("Numero no encontrado " + e);
                opcion = 0;
            }
        }


//        var json = conAPI.obtenerDatos(URL_BASE);
//        var datos = conve.obtenerDatos(json, FirtsDoor.class);
//        System.out.println("Top 10 libros mas descargados");
//        //Top 10 Libros mas descargados
//        System.out.println("\n--> >> Top 10 MÁS DESCARGADOOOOSSSS << <-- \n");
//
//        datos.libros().stream()
//                .sorted(Comparator.comparing(Libros::getNumeroDeDescargas).reversed())
//                .limit(10)
//                .map(l -> l.titulo().toUpperCase())
//                .forEach(System.out::println);

        //Busqueda de libros por nombre
//
//        System.out.println("\nNombre del Libro\n");
//        var tituloLibro = teclado.nextLine();
//        json = conAPI.obtenerDatos(URL_BASE+"?search="+ tituloLibro.replace(" ", "+"));
//        var datosBusqueda = conve.obtenerDatos(json, FirtsDoor.class);
//        Optional<Libros> librosBuscados = datosBusqueda.libros().stream()
//                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
//                .findFirst();
//        if(librosBuscados.isPresent()){
//            System.out.println("\n*** LIBRO ENCONTRADO ***\n");
//            System.out.println(librosBuscados.get());
//        }else{
//            System.out.println("LIBRO NO ENCONTRADO");
//        }
    }


    private Libros getDatos(){
        System.out.println("Escribe el nombre del libro que deseas Buscar");
        try {
            var tituloLibro = teclado.nextLine();
            var json = conAPI.obtenerDatos(URL_BASE+"?search="+ tituloLibro.replace(" ", "+"));
            FirtsDoor datosBusqueda = conve.obtenerDatos(json, FirtsDoor.class);

            if (datosBusqueda != null && datosBusqueda.libros() != null && !datosBusqueda.libros().isEmpty()){
                return  (Libros) datosBusqueda.libros().get(0);
            }else {
                System.out.println("No se encontraron resultados para el anime: " + datosBusqueda);
                return null;
            }

        }catch (Exception e){
            System.out.println("Ocurrió un error al obtener los datos del libro: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    private void buscarLibro() {
        Libros datosBusqueda = getDatos();
        if (datosBusqueda != null) {

            DataLibros libro = new DataLibros(datosBusqueda);
            List<DataAutor> autores = new ArrayList<>();

            for (Autor autor : datosBusqueda.autores()) {
                DataAutor dataAutor = new DataAutor(autor);
                autores.add(dataAutor);
            }
            autoresRepository.saveAll(autores);

            libro.setAutores(autores);

            librosRepository.save(libro);
            System.out.println(libro);

            }
    }

    @Transactional
    private void listaDeLibros(){
        List<DataLibros> listLibros = librosRepository.findAllWithGenero();
        listLibros = librosRepository.findAllWithAutores();
        if (listLibros != null && !listLibros.isEmpty()) {
            listLibros.forEach(l -> {
                System.out.println(
                        "\n<< -------------------------//LIBRO//------------------------------- >>"
                                +"\n->> Nombre del Libro: "+ l.getTitulo()
                                +"\n->> Genero: "+ l.getGenero());
                l.getAutores().forEach(a -> System.out.println("->> Autor: "+a.getNombre()));
                System.out.println(
                        "->> Numero de Descargas: "+ l.getNumeroDeDescargas()
                                +"\n<< -------------------------//_____//------------------------------- >>\n"
                );
            });
        } else {
            System.out.println("No se encontraron libros registrados.");
        }
    }
    private void listaDeAutores(){
        List<DataAutor> listAutores = autoresRepository.findAll();
        listAutores.forEach(a -> System.out.println("" +
                "\n|[<-------------- Autor ------------->]|\n"+
                "->> Autor: "+ a.getNombre() +
                "\n->> Fecha de nacimiento: "+ a.getVivo() +
                "\n->> Fecha de deceso: " + a.getMuerto() +
                "\n|[<--------------_______------------->]|\n"));
    }

//    private void determminadoAno(){
//        System.out.println("\n||<> DESCUBRE LOS AUTORES QUE VIVIERON POR EL RANGO DE AÑOS <>||\n");
//        System.out.println("\nEn qué año quieres empezar:");
//        int num_1 = teclado.nextInt();
//        System.out.println("\nEn qué año quieres terminar:");
//        int num_2 = teclado.nextInt();
//        teclado.nextLine();
//
//        try {
//            var json = conAPI.obtenerDatos(URL_BASE+"?author_year_start="+num_1+"&author_year_end="+num_2);
//            FirtsDoor datosBusqueda = conve.obtenerDatos(json, FirtsDoor.class);
//
//            if (datosBusqueda != null && datosBusqueda.libros() != null && !datosBusqueda.libros().isEmpty()){
//                System.out.println("\n||<> AUTORES ENCONTRADOS <>||\n");
//                datosBusqueda.libros().forEach(libro -> {
//                    libro.autores().forEach(autor -> {
//
//                        Integer born = autor.vivo();
//                        Integer dead = autor.muerto();
//
//                        if (born != null && born >= num_1 &&
//                                (dead == null || dead <= num_2))  {
//                            System.out.println("||<> ------------------------------<>||\n" +
//                                    "->> Nombre del Autor: " + autor.nombre()+
//                                    "\n->> Libro Hecho: "+ libro.titulo()+
//                                    "\n->> Año de born: " + autor.vivo()+
//                                    "\n->> Año de Muerte: " + (autor.muerto() != null ? autor.muerto() : "N/A")+
//                                    "\n||<> ------------------------------<>||\n");
//                        }
//                    });
//                });
//            } else {
//                System.out.println("No se encontraron resultados para el rango de años: " + num_1 + " - " + num_2);
//            }
//
//        } catch (Exception e){
//            System.out.println("Ocurrió un error al obtener los datos del autor: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    @Transactional
    private void determminadoAno() {
        System.out.println("\n||<> DESCUBRE LOS AUTORES QUE VIVIERON POR EL RANGO DE AÑOS <>||\n");
        System.out.println("\nEn qué año quieres empezar:");
        int startYear = teclado.nextInt();
        System.out.println("\nEn qué año quieres terminar:");
        int endYear = teclado.nextInt();
        teclado.nextLine();

        try {
            List<DataAutor> autores = autoresRepository.findAuthorsByYearRange(startYear, endYear);

            if (autores != null && !autores.isEmpty()) {
                System.out.println("\n||<> AUTORES ENCONTRADOS <>||\n");
                autores.forEach(autor -> {
                    autor.getLibros().forEach(libro -> {
                        System.out.println("->> Nombre del Autor: " + autor.getNombre());
                        System.out.println("->> Libro Hecho: " + libro.getTitulo());
                        System.out.println("->> Año de Nacimiento: " + (autor.getVivo() != null ? autor.getVivo() : "N/A"));
                        System.out.println("->> Año de Muerte: " + (autor.getMuerto() != null ? autor.getMuerto() : "N/A"));
                        System.out.println("||<> ------------------------------<>||\n");
                    });
                });
            } else {
                System.out.println("No se encontraron resultados para el rango de años: " + startYear + " - " + endYear);
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al obtener los datos del autor: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void idiomas(){
        System.out.println("Selecciona el idioma (en, es, pt): ");
        String idioma = teclado.nextLine();
        List<DataLibros> libros = librosRepository.findByIdiomas(idioma);
        if (libros != null && !libros.isEmpty()) {
            libros.forEach(libro -> {
                System.out.println("<< ----------------------------------------------------- >>"
                        + "\nNombre del Libro: " + libro.getTitulo()
                        + "\nIdiomas: " + libro.getIdiomas()
                        + "\nNúmero de Descargas: " + libro.getNumeroDeDescargas()
                        + "\n<< ------------------------------------------------------ >>\n");
            });
        } else {
            System.out.println("\nNo se encontraron libros en el idioma especificado.\n");
        }
    }

    private void top10Libros(){
        List<DataLibros> top10 = librosRepository.findTop10ByOrderByNumeroDeDescargasDesc();
        top10.forEach(s -> System.out.println("|- +++++++++++++++++++++++++++++++ -|" +
                "\n   |-> Titulo del Libro: "+ s.getTitulo() +
                "\n   |-> Total de Descargas: " + s.getNumeroDeDescargas()+
                "\n|- +++++++++++++++++++++++++++++++ -|\n"));
    }
}
