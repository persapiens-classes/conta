package br.edu.ifrn.conta.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Lancamento;
import br.edu.ifrn.conta.persistence.ContaCreditoFactory;
import br.edu.ifrn.conta.persistence.ContaDebitoFactory;
import br.edu.ifrn.conta.persistence.ContaPatrimonioFactory;
import br.edu.ifrn.conta.persistence.DonoFactory;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LancamentoServiceIT {

    @Autowired
    private DonoFactory donoFactory;

    @Autowired
    private LancamentoService lancamentoServico;

    @Autowired
    private ContaPatrimonioFactory contaPatrimonioFactory;

    @Autowired
    private ContaCreditoFactory contaCreditoFactory;

    @Autowired
    private ContaDebitoFactory contaDebitoFactory;

    @BeforeEach
    public void deletarTodos() {
        this.lancamentoServico.deleteAll();
        assertThat(this.lancamentoServico.findAll())
                .isEmpty();
    }

    @Test
    public void repositorioNaoEhNulo() {
        assertThat(this.lancamentoServico)
                .isNotNull();
    }

    @Test
    public void lancamentoComContaEntradaInvalida() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Lancamento lancamento = Lancamento.builder()
                    .contaEntrada(this.contaCreditoFactory.estagio())
                    .contaSaida(this.contaPatrimonioFactory.poupanca())
                    .valor(BigDecimal.TEN)
                    .data(LocalDateTime.now())
                    .dono(this.donoFactory.papai())
                    .build();

            this.lancamentoServico.save(lancamento);
        });
        assertThat(thrown)
                .isNotNull();
    }

    @Test
    public void lancamentoComContaSaidaInvalida() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Lancamento lancamento = Lancamento.builder()
                    .contaEntrada(this.contaPatrimonioFactory.poupanca())
                    .contaSaida(this.contaDebitoFactory.gasolina())
                    .valor(BigDecimal.TEN)
                    .data(LocalDateTime.now())
                    .dono(this.donoFactory.papai())
                    .build();

            this.lancamentoServico.save(lancamento);
        });
        assertThat(thrown)
                .isNotNull();
    }
}
