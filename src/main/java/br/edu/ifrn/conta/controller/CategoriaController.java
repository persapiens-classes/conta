package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service of Categoria.
 */
@RestController
@RequestMapping("/categoria")
public class CategoriaController extends CrudController<Categoria, Long> {

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/findByDescricao")
    public Categoria findByDescricao(@RequestParam String descricao) {
        return categoriaService.findByDescricao(descricao);
    }
}
