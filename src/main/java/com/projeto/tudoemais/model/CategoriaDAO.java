package com.projeto.tudoemais.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository  //informa ao Spring que a classe eh um componente de persistencia e pode ser injetada em outros lugares 
public class CategoriaDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);

    }

    public void inserirCategoria(Categoria cat) {
        String sql = "INSERT INTO categoria(nome) VALUES (?)";
        Object[] parametros = new Object[1];
        parametros[0] = cat.getNomeCategoria(); //atribui o valor do nome da categoria na primeira posicao do array
        jdbc.update(sql, parametros); //executa a consulta
    }

    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";
    
        List<Map<String, Object>> registros = jdbc.queryForList(sql, id); //executa a consulta
    
        if (registros.isEmpty()) {
            return null;  
        }
    
        Map<String, Object> registro = registros.get(0);
        return new Categoria(
            (Integer) registro.get("id"),
            (String) registro.get("nome")
        );
    }
    

    public List<Categoria> listarTodasCategorias() {
        String sql = "SELECT id, nome AS nomeCategoria FROM categoria";
        
        return jdbc.query(sql, (rs, rowNum) -> new Categoria( //retorna o resultado da execucao da consulta e mapeia os resultados
            rs.getInt("id"),
            rs.getString("nomeCategoria") 
        ));
    }
    

    public void deletarCategoria(int id){
        String sql = "DELETE FROM categoria where id = ?";
        jdbc.update(sql,id);
    }
    

}
