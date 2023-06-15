package br.edu.ifrn.conta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

/**
 * Conta Patrimonio entity.
 */
@Getter
@Setter
@ToString(callSuper = true, exclude = "valoresIniciaisDosDonos")
@EqualsAndHashCode(callSuper = true, exclude = "valoresIniciaisDosDonos")
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContaPatrimonio extends Conta {

    private static final long serialVersionUID = 1L;

    @Builder
    public ContaPatrimonio(@Singular("valorInicialDoDono") Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciaisDosDonos, Long id, String descricao, Categoria categoria) {
        super(id, descricao, categoria);
        this.valoresIniciaisDosDonos = valoresIniciaisDosDonos;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "contaPatrimonio")
    private Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciaisDosDonos;

}