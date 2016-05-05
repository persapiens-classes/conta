package br.edu.ifrn.conta.dominio;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = "valorInicial")
@Entity
public class ContaPatrimonio extends Conta {

    @Builder
    public ContaPatrimonio(BigDecimal valorInicial, Long id, String descricao, Categoria categoria) {
        super(id, descricao, categoria);
        this.valorInicial = valorInicial;
    }    
    
    @Column(nullable = false)
    private BigDecimal valorInicial;
    
}
