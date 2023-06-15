package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.service.ContaCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service of conta credito.
 */
@RestController
@RequestMapping("/contaCredito")
public class ContaCreditoController extends CrudController<ContaCredito, Long> {

    @Autowired
    private ContaCreditoService contaCreditoService;
    
    @GetMapping("/findByDescricao")
    public ContaCredito findByDescricao(@RequestParam String descricao) {
        return contaCreditoService.findByDescricao(descricao);
    }
}
