package com.projeto.tudoemais.model;

import java.math.BigDecimal;
import java.util.Map;

public class Tool {

    public static Produto converterProduto(Map<String,Object> registro){

        BigDecimal precoBigDecimal = (BigDecimal) registro.get("preco");
        Double preco = precoBigDecimal != null ? precoBigDecimal.doubleValue() : null;  // Transforma double em bigdecimal

        Categoria categoria = new Categoria(
            (Integer) registro.get("categoria_id"),
            (String) registro.get("categoria_nome")
        );

        return new Produto(
            (Integer) registro.get("id"),
            (String) registro.get("nome"),
            (String) registro.get("descricao"),
            preco,
            categoria
        );
                          
    }
}


