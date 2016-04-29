package br.edu.ifrn.conta.dominio;

import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "lancamentos")
public class Dono {
    
    private String descricao;

    private Set<Lancamento> lancamentos;
    
}
