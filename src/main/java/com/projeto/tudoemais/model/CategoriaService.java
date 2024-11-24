package com.projeto.tudoemais.model;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    CategoriaDAO cdao;

    public void inserirCategoria(Categoria cat){
        cdao.inserirCategoria(cat);
    }

    
    public List<Categoria> listarTodasCategorias() {
        return cdao.listarTodasCategorias();  
    }

    public Categoria buscarPorId(int id) {
        return cdao.buscarPorId(id);
    }

    public void deletarCategoria(int id){
        cdao.deletarCategoria(id);
    }

}

    



