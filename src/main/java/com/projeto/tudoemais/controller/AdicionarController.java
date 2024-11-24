package com.projeto.tudoemais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.projeto.tudoemais.model.CategoriaService;
import com.projeto.tudoemais.model.Produto;
import com.projeto.tudoemais.model.ProdutoDTO;
import com.projeto.tudoemais.model.ProdutoService;
// import com.projeto.tudoemais.model.Produtotool;


@Controller
public class AdicionarController {

    @Autowired
    private ApplicationContext context;
    

    @GetMapping("/")
    public String Principal(){
        return "principal";
    }


    @GetMapping("/adicionar")
    public String adicionar(Model model){
        CategoriaService cs = context.getBean(CategoriaService.class);
        model.addAttribute("produto", new ProdutoDTO());
        model.addAttribute("categorias", cs.listarTodasCategorias());
        return "adicionar";
    }

    @PostMapping("/adicionar")
    public String adicionar(Model model, @ModelAttribute ProdutoDTO produtoDTO){
        CategoriaService cs = context.getBean(CategoriaService.class);
        ProdutoService ps = context.getBean(ProdutoService.class);
        Produto produto = ps.converterDTOParaProduto(produtoDTO);

        ps.inserir(produto);
        model.addAttribute("produto", new ProdutoDTO());
        model.addAttribute("categorias", cs.listarTodasCategorias());
        model.addAttribute("mensagem", "Produto adicionado com sucesso!");
        return "adicionar";
    }

    @GetMapping("/listagem")
    public String listagem(Model model){
        ProdutoService ps = context.getBean(ProdutoService.class);
        List<Produto> listarProdutos = ps.listarTodosProdutos();
        model.addAttribute("produtos", listarProdutos);
        return "listagem";
    }

    @PostMapping("/deletarproduto/{id}")
    public String deletarproduto(@PathVariable int id){
        ProdutoService ps = context.getBean(ProdutoService.class);
        ps.deletarProduto(id);
        return "redirect:/listagem";

    }

    @GetMapping("/atualizar/{id}")
    public String atualizar(Model model, @PathVariable int id){
        ProdutoService ps = context.getBean(ProdutoService.class);
        CategoriaService cs = context.getBean(CategoriaService.class);
        Produto pro = ps.obterProduto(id);
        model.addAttribute("id", id);
        model.addAttribute("produto", pro);
        model.addAttribute("categorias", cs.listarTodasCategorias());
        return "atualizar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable int id, @ModelAttribute Produto pro){
        ProdutoService ps = context.getBean(ProdutoService.class);
        ps.atualizarProduto(id, pro);
        return "redirect:/listagem";
    }

    

    @GetMapping("/roupas")
    public String listarRoupas(Model model){
        ProdutoService ps = context.getBean(ProdutoService.class);
        List<Produto> produtosEletronicos = ps.listarProdutosPorCategoria("Roupas");
        model.addAttribute("produtos", produtosEletronicos);
        return "roupas";
    }

    @GetMapping("/papelaria")
    public String listarPapelaria(Model model){
        ProdutoService ps = context.getBean(ProdutoService.class);
        List<Produto> produtosEletronicos = ps.listarProdutosPorCategoria("Papelaria");
        model.addAttribute("produtos", produtosEletronicos);
        return "papelaria";
    }

    @GetMapping("/eletronicos")
    public String listarEletronicos(Model model){
        ProdutoService ps = context.getBean(ProdutoService.class);
        List<Produto> produtosEletronicos = ps.listarProdutosPorCategoria("Eletrônicos");
        model.addAttribute("produtos", produtosEletronicos);
        return "eletronicos";

    }

}
