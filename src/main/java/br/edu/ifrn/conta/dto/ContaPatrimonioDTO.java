package br.edu.ifrn.conta.dto;

import br.edu.ifrn.conta.controller.ContaDTO;
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
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
public class ContaPatrimonioDTO extends ContaDTO {
}
