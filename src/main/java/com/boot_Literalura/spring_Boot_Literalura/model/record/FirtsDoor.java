package com.boot_Literalura.spring_Boot_Literalura.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FirtsDoor(
        @JsonAlias("results") List<Libros> libros
) {
}
