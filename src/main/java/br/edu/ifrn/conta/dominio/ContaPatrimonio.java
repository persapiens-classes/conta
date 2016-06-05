package br.edu.ifrn.conta.dominio;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true, exclude = "valoresIniciaisDosDonos")
@EqualsAndHashCode(callSuper = true, exclude = "valoresIniciaisDosDonos")
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContaPatrimonio extends Conta {

    @Builder
    public ContaPatrimonio(@Singular("valorInicialDoDono") Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciaisDosDonos
            , Long id, String descricao, Categoria categoria) {
        super(id, descricao, categoria);
        this.valoresIniciaisDosDonos = valoresIniciaisDosDonos;
    }
    
    @OneToMany(mappedBy = "contaPatrimonio")
    private Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciaisDosDonos;
    
}
