package br.edu.ifrn.conta.dto;

import java.io.Serializable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DTO interface.
 */
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@SuperBuilder
@Data
@ToString
@NoArgsConstructor
public class ContaDTO implements Serializable {
    private String descricao;
    private CategoriaDTO categoria;
}
