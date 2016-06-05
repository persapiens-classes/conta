package br.edu.ifrn.conta.dominio;

import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContaCredito extends Conta {
    @Builder
    public ContaCredito(Long id, String descricao, Categoria categoria) {
        super(id, descricao, categoria);
    }    
}
