package br.edu.ifrn.conta.dominio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Conta {
    
    private String descricao;

    private Categoria categoria;
    
}
