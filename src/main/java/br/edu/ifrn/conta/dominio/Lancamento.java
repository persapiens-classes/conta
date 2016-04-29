package br.edu.ifrn.conta.dominio;

import java.math.BigDecimal;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Lancamento {

    private Dono dono;
    
    private Conta contaEntrada;
    
    private Conta contaSaida;
    
    private BigDecimal valor;
    
    private Date data;
    
    private String descricao;
    
}
