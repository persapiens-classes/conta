package br.edu.ifrn.conta.servico;

import br.edu.ifrn.conta.dominio.ContaDebito;
import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.Lancamento;
import br.edu.ifrn.conta.persistencia.LancamentoRepository;
import java.math.BigDecimal;
import java.util.Date;
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

    @Override
    public void save(Lancamento objeto) {
        objeto.verificarAtributos();
        
        super.save(objeto);
    }
    
    public void transferir(BigDecimal valor
        , Dono donoDebito, ContaDebito contaDebito, ContaPatrimonio contaPatrimonioADebitar
        , Dono donoCredito, ContaPatrimonio contaPatrimonioACreditar, ContaCredito contaCredito) {
        
        if (donoCredito.equals(donoDebito)) {
            throw new IllegalArgumentException("Donos das contas devem ser diferentes: "
                + donoDebito + " = " + donoCredito);
        }
        
        Date data = new Date();
        
        Lancamento lancamentoComDespesa = Lancamento.builder()
            .contaEntrada(contaDebito)
            .contaSaida(contaPatrimonioADebitar)
            .dono(donoDebito)
            .valor(valor)
            .data(data)
            .descricao("Lançamento de débito de uma transferência")
            .build();
        save(lancamentoComDespesa);
        
        Lancamento lancamentoComReceita = Lancamento.builder()
            .contaEntrada(contaPatrimonioACreditar)
            .contaSaida(contaCredito)
            .dono(donoCredito)
            .valor(valor)
            .data(data)
            .descricao("Lançamento de crédito de uma transferência")
            .build();
        save(lancamentoComReceita);    
    }
}
