package br.edu.ifrn.dominio;

import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "contas")
public class Categoria {

    private String descricao;
    
    private Set<Conta> contas;
    
}
