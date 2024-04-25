package br.edu.ifrn.conta.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.persistence.ContaPatrimonioFactory;
import br.edu.ifrn.conta.persistence.DonoFactory;
import static org.assertj.core.api.Assertions.assertThat;
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
    private LancamentoService lancamentoService;

    @Test
    public void transferenciaDe10DePapaiParaMamae() {
        this.transferenciaService.transferir(BigDecimal.TEN, 
    this.donoFactory.papai(), this.contaPatrimonioFactory.contaCorrente(),
   this.donoFactory.mamae(), this.contaPatrimonioFactory.poupanca());
        
        assertThat(lancamentoService.debitosSum(this.donoFactory.papai(), 
    this.contaPatrimonioFactory.contaCorrente()))
            .isEqualTo(BigDecimal.TEN.setScale(2));

        assertThat(lancamentoService.creditosSum(this.donoFactory.mamae(), 
    this.contaPatrimonioFactory.poupanca()))
            .isEqualTo(BigDecimal.TEN.setScale(2));
    }
}
