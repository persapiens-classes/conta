package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;
import br.edu.ifrn.conta.service.ContaPatrimonioService;
import br.edu.ifrn.conta.service.DonoService;
import br.edu.ifrn.conta.service.ValorInicialDoDonoNaContaPatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service of valorInicialDoDonoNaContaPatrimonio.
 */
@RestController
@RequestMapping("/valorInicialDoDonoNaContaPatrimonio")
public class ValorInicialDoDonoNaContaPatrimonioController extends CrudController<ValorInicialDoDonoNaContaPatrimonio, Long> {

    @Autowired
    private ValorInicialDoDonoNaContaPatrimonioService valorInicialDoDonoNaContaPatrimonioService;
    
    @Autowired
    private DonoService donoService;
    
    @Autowired
    private ContaPatrimonioService contaPatrimonioService;
    
    @GetMapping("findByDonoAndContaPatrimonio")
    public ValorInicialDoDonoNaContaPatrimonio findByDonoAndContaPatrimonio(@RequestParam String dono, @RequestParam String contaPatrimonio) {
        return valorInicialDoDonoNaContaPatrimonioService.findByDonoAndContaPatrimonio(
        donoService.findByDescricao(dono), 
contaPatrimonioService.findByDescricao(dono));
    }
}
