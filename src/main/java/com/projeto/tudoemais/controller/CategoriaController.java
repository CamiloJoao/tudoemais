package com.projeto.tudoemais.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.ui.Model;

import com.projeto.tudoemais.model.Categoria;
import com.projeto.tudoemais.model.CategoriaService;


@Controller
public class CategoriaController {

    @Autowired
    private ApplicationContext context;

    

    @GetMapping("/principalCategoria")
    public String PrincipalC(){
        return "principalCategoria";
    }

    @GetMapping("/adicionarcategoria")
    public String adicionarCategoria(Model model) {
        model.addAttribute("categoria", new Categoria(0, ""));
        return "adicionarcategoria";
    }

    @PostMapping("/adicionarcategoria")
    public String adicionarCategoria(Model model, @ModelAttribute Categoria categoria) {
        CategoriaService cs = context.getBean(CategoriaService.class);
        cs.inserirCategoria(categoria);
        model.addAttribute("categoria", new Categoria(0, ""));
        model.addAttribute("mensagem", "Categoria adicionada com sucesso!");
        return "adicionarcategoria"; 
    }

    @PostMapping("/deletarcategoria/{id}")
    public String deletarCategoria(@PathVariable int id, RedirectAttributes redirectAttributes) {
    try {
        CategoriaService cs = context.getBean(CategoriaService.class);
        cs.deletarCategoria(id); // Tenta excluir a categoria
        redirectAttributes.addFlashAttribute("success", "Categoria excluída com sucesso!");
    } catch (DataIntegrityViolationException ex) {
        // Se a exclusão falhar por restrição de chave estrangeira
        redirectAttributes.addFlashAttribute("error", "Categoria não pode ser excluída, pois está vinculada a produtos cadastrados.");
    }
        return "redirect:/listagemCategoria";
    }


    @GetMapping("/listagemCategoria")
    public String listagemcategoria(Model model) {
        CategoriaService cs = context.getBean(CategoriaService.class);
        List<Categoria> listaCategoria = cs.listarTodasCategorias();
        model.addAttribute("categorias", listaCategoria);
        return "listagemCategoria";
    }
        

}

