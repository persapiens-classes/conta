package br.edu.ifrn.conta.dominio;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = "valorInicial")
public class ContaPatrimonio extends Conta {

    @Builder
    public ContaPatrimonio(BigDecimal valorInicial, String descricao, Categoria categoria) {
        super(descricao, categoria);
        this.valorInicial = valorInicial;
    }    
    
    private BigDecimal valorInicial;
    
}
