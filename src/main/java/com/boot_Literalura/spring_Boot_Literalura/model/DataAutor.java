package com.boot_Literalura.spring_Boot_Literalura.model;

import com.boot_Literalura.spring_Boot_Literalura.model.record.Autor;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class DataAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer vivo;
    private Integer muerto;

    @ManyToMany(mappedBy = "autores")
    private List<DataLibros> libros;

public DataAutor(){
}
    public DataAutor(Autor a) {
        this.nombre = a.nombre();
        this.vivo = a.vivo();
        this.muerto = a.muerto();
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", vivo='" + vivo + '\'' +
                ", muerto='" + muerto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVivo() {
        return vivo;
    }

    public void setVivo(Integer vivo) {
        this.vivo = vivo;
    }

    public Integer getMuerto() {
        return muerto;
    }

    public void setMuerto(Integer muerto) {
        this.muerto = muerto;
    }

    public List<DataLibros> getLibros() {
        return libros;
    }

    public void setLibros(List<DataLibros> libros) {
        this.libros = libros;
    }


}
