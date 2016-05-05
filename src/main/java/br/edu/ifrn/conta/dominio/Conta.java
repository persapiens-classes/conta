package br.edu.ifrn.conta.dominio;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public abstract class Conta {
    
    private String descricao;

    @NonNull
    private Categoria categoria;
    
}
