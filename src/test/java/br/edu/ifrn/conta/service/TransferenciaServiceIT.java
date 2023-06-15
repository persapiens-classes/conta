package br.edu.ifrn.conta.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.persistence.ContaCreditoFactory;
import br.edu.ifrn.conta.persistence.ContaDebitoFactory;
import br.edu.ifrn.conta.persistence.ContaPatrimonioFactory;
import br.edu.ifrn.conta.persistence.DonoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferenciaServiceIT {

    @Autowired
    private DonoFactory donoFactory;

    @Autowired
    private TransferenciaService transferenciaService;

    @Autowired
    private ContaPatrimonioFactory contaPatrimonioFactory;

    @Autowired
    private ContaCreditoFactory contaCreditoFactory;

    @Autowired
    private ContaDebitoFactory contaDebitoFactory;

    @Test
    public void transferenciaDePapaiParaMamae() {
        this.transferenciaService.transferir(BigDecimal.TEN, this.donoFactory.papai(),
    this.contaDebitoFactory.despesaComConjuge(), this.contaPatrimonioFactory.poupanca(),
    this.donoFactory.mamae(), this.contaCreditoFactory.receitaComConjuge(),
    this.contaPatrimonioFactory.poupanca());
    }
}
