package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.service.ContaPatrimonioService;
import br.edu.ifrn.conta.service.DonoService;
import br.edu.ifrn.conta.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Saldo controller
 */
@RestController
@RequestMapping("/")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;
    @Autowired
    private DonoService donoService;
    @Autowired
    private ContaPatrimonioService contaPatrimonioService;

    @PostMapping("/transferir")
    public void transferir(@RequestBody TransferenciaDTO transferenciaDTO) {
        transferenciaService.transferir(transferenciaDTO.getValor(),
            donoService.findByDescricao(transferenciaDTO.getDonoDebito().getDescricao()).get(),
            contaPatrimonioService.findByDescricao(transferenciaDTO.getContaDebito().getDescricao()).get(),
            donoService.findByDescricao(transferenciaDTO.getDonoCredito().getDescricao()).get(),
            contaPatrimonioService.findByDescricao(transferenciaDTO.getContaCredito().getDescricao()).get());
    }
}
