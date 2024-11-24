package com.projeto.tudoemais.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    ProdutoDAO pdao;

    public void inserir(Produto pro){
        pdao.inserir(pro);
    }

    public Produto converterDTOParaProduto(ProdutoDTO produtoDTO){
        return pdao.converterDTOParaProduto(produtoDTO);
    }

    public List<Produto> listarTodosProdutos() {
        return pdao.listarTodosProdutos(); 
    }

    public void deletarProduto(int id){
        pdao.deletarProduto(id);
    }

    public Produto obterProduto(int id){
        return pdao.obterProduto(id);
    }

    public void atualizarProduto(int id, Produto pro){
        pdao.atualizarProduto(id, pro);
    }

    public List<Produto> listarProdutosPorCategoria(String nomeCategoria) {
        return pdao.listarProdutosPorCategoria(nomeCategoria);
    }



}
