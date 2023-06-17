package br.edu.ifrn.conta.controller;

import java.io.Serializable;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DTO interface.
 */
@SuperBuilder
@Data
@ToString
@NoArgsConstructor
public class CategoriaDTO implements Serializable {
    private String descricao;
}