package br.edu.ifrn.conta.domain;

import jakarta.persistence.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Conta Debito entity.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContaDebito extends Conta {

    private static final long serialVersionUID = 1L;

    @Builder
    public ContaDebito(Long id, String descricao, Categoria categoria) {
        super(id, descricao, categoria);
    }

}
