package br.edu.ifrn.conta.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DTO interface.
 */
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@SuperBuilder
@Data
@ToString
@NoArgsConstructor
public class ValorInicialDoDonoNaContaPatrimonioDTO implements Serializable {
    private DonoDTO dono;
    private ContaPatrimonioDTO contaPatrimonio;
    private BigDecimal valorInicial;
}
