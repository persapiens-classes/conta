package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Conta;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.Lancamento;
import java.math.BigDecimal;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LancamentoFabrica {
    
    @Inject
    private LancamentoRepository lancamentoRepository;

    public Lancamento lancamento(Dono dono, Conta contaEntrada, Conta contaSaida, BigDecimal valor) {        
        Lancamento lancamento = Lancamento.builder()
            .contaEntrada(contaEntrada)
            .contaSaida(contaSaida)
            .data(new Date())
            .dono(dono)
            .valor(valor.setScale(2))
            .build();
        lancamentoRepository.save(lancamento);
        return lancamento;
    }
}
