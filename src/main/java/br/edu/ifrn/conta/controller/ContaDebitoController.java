package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.ContaDebito;
import br.edu.ifrn.conta.service.ContaDebitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service of conta debito.
 */
@RestController
@RequestMapping("/contaDebito")
public class ContaDebitoController extends CrudController<ContaDebito, Long> {

    @Autowired
    private ContaDebitoService contaDebitoService;
    
    @GetMapping("/findByDescricao")
    public ContaDebito findByDescricao(@RequestParam String descricao) {
        return contaDebitoService.findByDescricao(descricao);
    }
}
