package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.ValorInicialDoDonoNaContaPatrimonio;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ValorInicialDoDonoNaContaPatrimonioFabrica {
    
    @Inject
    private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;

    public ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(
                Dono dono, ContaPatrimonio contaPatrimonio, BigDecimal valor) {        
        ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio
            = ValorInicialDoDonoNaContaPatrimonio.builder()
                .dono(dono)
                .contaPatrimonio(contaPatrimonio)
                .valorInicial(valor.setScale(2))
              .build();
        valorInicialDoDonoNaContaPatrimonioRepository.save(valorInicialDoDonoNaContaPatrimonio);
        valorInicialDoDonoNaContaPatrimonioRepository.save(valorInicialDoDonoNaContaPatrimonio);
        return valorInicialDoDonoNaContaPatrimonio;
    }
}
