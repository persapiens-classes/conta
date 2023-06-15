package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.service.ContaPatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service of conta patrimonio.
 */
@RestController
@RequestMapping("/contaPatrimonio")
public class ContaPatrimonioController extends CrudController<ContaPatrimonio, Long> {

    @Autowired
    private ContaPatrimonioService contaPatrimonioService;
    
    @GetMapping("/findByDescricao")
    public ContaPatrimonio findByDescricao(@RequestParam String descricao) {
        return contaPatrimonioService.findByDescricao(descricao);
    }
}
