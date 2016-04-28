package br.edu.ifrn.dominio;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = "valorInicial")
public class ContaPatrimonio extends Conta {
    
    private BigDecimal valorInicial;
    
}
