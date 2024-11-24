package com.projeto.tudoemais.model;

public class ProdutoDTO {

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int categoriaId;

    public ProdutoDTO(){
        
    }

    public ProdutoDTO(int id, String nome, String descricao, double preco, int categoriaId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaId = categoriaId;

    }

    public ProdutoDTO(String nome, String descricao, double preco, int categoriaId) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaId = categoriaId;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }   

}
