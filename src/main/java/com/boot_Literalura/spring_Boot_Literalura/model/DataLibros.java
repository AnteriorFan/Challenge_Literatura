package com.boot_Literalura.spring_Boot_Literalura.model;


import com.boot_Literalura.spring_Boot_Literalura.model.record.Libros;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "libros"
)
public class DataLibros {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;
     private Integer id_book;
    @Column(
            unique = true
    )
     private String titulo;

    @ManyToMany
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<DataAutor> autores;
    @ElementCollection(fetch = FetchType.EAGER)
     private List<String> idiomas;
    @ElementCollection(fetch = FetchType.EAGER)
     private List<String> genero;
     private Double numeroDeDescargas;

     public DataLibros(){}
    public DataLibros(Libros libros) {
        this.id_book = libros.id_book();
        this.titulo = libros.titulo();;
        this.idiomas = libros.idiomas();
        this.genero = libros.genero();
        this.numeroDeDescargas = libros.numeroDeDescargas();
    }
    public void Autores(List<DataAutor> autores){
            this.autores = autores;
    }
    public List<DataAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<DataAutor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------\n"+
                "ID del Libro=" + id_book +
                ",\nTitulo='" + titulo + '\'' +
                ",\nAutor=" +  autores+
                ",\nIdiomas=" + idiomas +
                ",\nGenero=" + genero +
                ",\nNumero De Descarga=" + numeroDeDescargas +
                "\n--------------------------------------\n";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getId_book() {
        return id_book;
    }

    public void setId_book(Integer id_book) {
        this.id_book = id_book;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public List<String> getGenero() {
        return genero;
    }

    public void setGenero(List<String> genero) {
        this.genero = genero;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
