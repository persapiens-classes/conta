package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.service.DonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service of Lancamento.
 */
@RestController
@RequestMapping("/dono")
public class DonoController extends CrudController<Dono, Long> {

    @Autowired
    private DonoService donoService;
    
    @GetMapping("/findByDescricao")
    public Dono findByDescricao(@RequestParam String descricao) {
        return donoService.findByDescricao(descricao);
    }
    
    @DeleteMapping("/deleteByDescricao")
    public void deleteByDescricao(@RequestParam String descricao) {
        donoService.deleteByDescricao(descricao);
    }
}
