package com.boot_Literalura.spring_Boot_Literalura.repository;

import com.boot_Literalura.spring_Boot_Literalura.model.DataAutor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutoresRepository extends JpaRepository<DataAutor, Long> {

    List<DataAutor> findAll();

    @EntityGraph(attributePaths = "libros")
    @Query("SELECT a FROM DataAutor a WHERE (a.vivo >= :startYear AND a.vivo <= :endYear) OR (a.muerto >= :startYear AND a.muerto <= :endYear)")
    List<DataAutor> findAuthorsByYearRange(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);


}
