package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.Lancamento;
import br.edu.ifrn.conta.persistencia.LancamentoRepository;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LancamentoServico extends AbstratoServico<Lancamento, Long> {
    private LancamentoRepository lancamentoRepository;
    
    @Inject
    public LancamentoServico(LancamentoRepository lancamentoRepository) {
        super();
        this.lancamentoRepository = lancamentoRepository;
    }
    
    public BigDecimal saldo(Dono dono, ContaPatrimonio contaPatrimonio) {
        return lancamentoRepository.saldo(dono, contaPatrimonio);
    }    
}
