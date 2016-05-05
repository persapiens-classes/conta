package br.edu.ifrn.conta.dominio;

import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "lancamentos")
@Builder
public class Dono {
    
    private String descricao;

    @Singular
    private Set<Lancamento> lancamentos;
    
}
