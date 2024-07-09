package com.boot_Literalura.spring_Boot_Literalura.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Libros(
        @JsonAlias("id") Integer id_book,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("bookshelves") List<String> genero,
        @JsonAlias("download_count") Double numeroDeDescargas

) {
}
