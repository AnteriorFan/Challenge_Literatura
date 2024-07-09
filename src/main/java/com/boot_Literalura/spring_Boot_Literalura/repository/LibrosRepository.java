package com.boot_Literalura.spring_Boot_Literalura.repository;

import com.boot_Literalura.spring_Boot_Literalura.model.DataLibros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibrosRepository extends JpaRepository<DataLibros, Long> {



    @Query("SELECT l FROM DataLibros l LEFT JOIN FETCH l.genero")
    List<DataLibros> findAllWithGenero();

    @Query("SELECT l FROM DataLibros l LEFT JOIN FETCH l.autores")
    List<DataLibros> findAllWithAutores();

    List<DataLibros> findByIdiomas(String idioma);

    List<DataLibros> findTop10ByOrderByNumeroDeDescargasDesc();

//    @EntityGraph(attributePaths = "genero") // Forzar la recuperación de la colección de géneros
//    @Query("SELECT l FROM DataLibros l")
//    List<DataLibros> findAllWithGenero();


}
