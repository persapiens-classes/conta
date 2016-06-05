package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.QLancamento;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * CrudRepository com definicoes de metodos
 */
public class LancamentoRepositoryImpl implements LancamentoRepositoryCustom {
    
    private final EntityManager entityManager;

    private final ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;
    
    @Inject
    public LancamentoRepositoryImpl(EntityManager entityManager
        , ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository) {
        this.entityManager = entityManager;
        this.valorInicialDoDonoNaContaPatrimonioRepository = valorInicialDoDonoNaContaPatrimonioRepository;
    }
    
    /**
     * Calcula o valor do saldo do dono na conta patrimonio
     * @param dono do saldo
     * @param contaPatrimonio do saldo
     * @return saldo calculado
     */
    @Override
    public BigDecimal saldo(Dono dono, ContaPatrimonio contaPatrimonio) {
        // recupera o valor inicial do dono na conta patrimonio
        BigDecimal result = valorInicialDoDonoNaContaPatrimonioRepository
            .findByDonoAndContaPatrimonio(dono, contaPatrimonio).getValorInicial();
        
        QLancamento qLancamento = QLancamento.lancamento;
        JPQLQueryFactory factory = new JPAQueryFactory(entityManager);
                       
        // soma todos os lancamentos de credito do dono na conta patrimonio
        BigDecimal creditos = factory
            .from(qLancamento)
            .where(qLancamento.dono.eq(dono)
                .and(qLancamento.contaEntrada.eq(contaPatrimonio)))
            .select(qLancamento.valor.sum())
            .fetchOne();
        
        // subtrai todos os lancamentos de debito do dono na conta patrimonio
        BigDecimal debitos = factory
            .from(qLancamento)
            .where(qLancamento.dono.eq(dono)
                .and(qLancamento.contaSaida.eq(contaPatrimonio)))
            .select(qLancamento.valor.sum())
            .fetchOne();
        
        return result.add(creditos).subtract(debitos);
    }
}
