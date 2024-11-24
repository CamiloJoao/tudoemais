package com.projeto.tudoemais.model;

public class Categoria {
    private Integer id;
    private String nomeCategoria;

    public Categoria() {
        
    }

    public Categoria(Integer id, String nomeCategoria){
        this.id = id;
        this.nomeCategoria = nomeCategoria;
    }

    public Categoria(String nomeCategoria){
        this.nomeCategoria = nomeCategoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

}


