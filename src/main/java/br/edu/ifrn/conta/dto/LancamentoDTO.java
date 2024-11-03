package br.edu.ifrn.conta.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class LancamentoDTO implements Serializable {
    private LocalDateTime data;
    private BigDecimal valor;
    private ContaDTO contaEntrada;
    private ContaDTO contaSaida;
    private DonoDTO dono;
    private String descricao;
}
