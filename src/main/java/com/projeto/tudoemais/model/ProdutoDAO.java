package com.projeto.tudoemais.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import jakarta.annotation.PostConstruct;

@Repository
public class ProdutoDAO {

    @Autowired
    DataSource dataSource;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaDAO cdao;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Produto pro) {
        String sql = "INSERT INTO produtoA(nome,descricao,preco,categoria) VALUES (?,?,?,?)";
        Object[] parametros = new Object[4];
        parametros[0] = pro.getNome();
        parametros[1] = pro.getDescricao();
        parametros[2] = pro.getPreco();
        parametros[3] = pro.getCategoria().getId();
        jdbc.update(sql,parametros);
    }



    public Produto converterDTOParaProduto(ProdutoDTO produtoDTO) {
        // Verifica se o ID da categoria é válido (não 0)
        if (produtoDTO.getCategoriaId() == 0) {
            throw new IllegalArgumentException("Categoria inválida: " + produtoDTO.getCategoriaId());
        }
    
        // Busca a categoria pelo ID
        Categoria categoria = categoriaService.buscarPorId(produtoDTO.getCategoriaId());
        
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria inválida: " + produtoDTO.getCategoriaId());
        }
    
        // Cria um novo objeto Produto com os dados do DTO e a categoria
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(categoria);
    
        return produto;
    }
    


    public List<Produto> listarTodosProdutos() {
        String sql = """
        SELECT p.id, p.nome, p.descricao, p.preco, c.id AS categoria_id, c.nome AS categoria_nome 
        FROM produtoA p
        JOIN categoria c ON p.categoria = c.id """;
        
        return jdbc.query(sql, (rs, rowNum) -> {
            Categoria categoria = new Categoria(
                rs.getInt("categoria_id"),
                rs.getString("categoria_nome")
            );
            return new Produto(
                
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getDouble("preco"),
                categoria
            );
        });
    }

    public List<Produto> listarProdutosPorCategoria(String nomeCategoria) {
        String sql = """
            SELECT p.id AS produto_id, p.nome AS produto_nome, p.descricao, p.preco, c.id AS categoria_id, c.nome AS categoria_nome
            FROM produtoA p
            JOIN categoria c ON p.categoria = c.id
            WHERE c.nome = ?
        """;
        return jdbc.query(sql, (rs, rowNum) -> {
            Categoria categoria = new Categoria(
                rs.getInt("categoria_id"),
                rs.getString("categoria_nome")
            );
            return new Produto(
                rs.getInt("produto_id"), // Use "produto_id" como definido no alias
                rs.getString("produto_nome"), // Use "produto_nome" como definido no alias
                rs.getString("descricao"),
                rs.getDouble("preco"),
                categoria
            );
        }, nomeCategoria);
    }
    



    public void deletarProduto(int id){
        String sql = "DELETE FROM produtoA where id = ?";
        jdbc.update(sql,id);
    }

    public Produto obterProduto(int id){
        String sql = "Select * FROM produtoA where id = ?";
        return Tool.converterProduto(jdbc.queryForMap(sql,id));
    }

    public void atualizarProduto(int id, Produto pro){
        String sql = "UPDATE produtoA SET nome = ?, descricao = ?, preco = ?, categoria = ? WHERE id = ?";
        jdbc.update(sql, pro.getNome(), pro.getDescricao(), pro.getPreco(), pro.getCategoria().getId(), id);
    }


}


