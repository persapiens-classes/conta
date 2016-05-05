package br.edu.ifrn.conta.dominio;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ContaDebito extends Conta {

    @Builder
    public ContaDebito(String descricao, Categoria categoria) {
        super(descricao, categoria);
    }
    
}
