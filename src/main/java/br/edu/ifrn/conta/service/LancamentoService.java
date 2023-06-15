package br.edu.ifrn.conta.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.domain.ContaDebito;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.Lancamento;
import br.edu.ifrn.conta.persistence.LancamentoRepository;
import br.edu.ifrn.conta.persistence.ValorInicialDoDonoNaContaPatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Service of Lancamento.
 */
@Service
public class LancamentoService extends CrudService<Lancamento, Long> {

    @Override
    @Transactional
    public Lancamento save(Lancamento objeto) {
        objeto.verificarAtributos();

        return super.save(objeto);
    }
}
