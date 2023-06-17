package br.edu.ifrn.conta.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DTO interface.
 */
@SuperBuilder
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class TransferenciaDTO implements Serializable {
    private BigDecimal valor;
    private DonoDTO donoDebito;
    private ContaPatrimonioDTO contaDebito;
    private DonoDTO donoCredito;
    private ContaPatrimonioDTO contaCredito;
}
