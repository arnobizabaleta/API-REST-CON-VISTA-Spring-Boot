package com.proyecto.glosajeweb.Model;

import jakarta.persistence.*;

@Entity
public class Categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long codCategoria;
    @Column
    private String nombre_categoria;

    public long getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(long codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
}
