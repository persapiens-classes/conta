/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.edu.ifrn.conta.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Lancamento entity.
 * @author Marcelo Fernandes
 */
@SuppressFBWarnings({"EI_EXPOSE_REP2"})	
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"id", "descricao"})
@Builder
@Entity
@SequenceGenerator(sequenceName = "seq_lancamento", name = "ID_SEQUENCE", allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lancamento implements Serializable, Comparable<Lancamento> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
	private Long id;

	@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})	
	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_lancamento_dono"))
	private Dono dono;

	@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})	
	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_lancamento_contaEntrada"))
	private Conta contaEntrada;

	@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})	
	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_lancamento_contaSaida"))
	private Conta contaSaida;

	@Column(nullable = false)
	private BigDecimal valor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime data;

	private String descricao;

	@Override
	public int compareTo(Lancamento o) {
		int result = this.data.compareTo(o.data);
		if (result == 0) {
			result = this.valor.compareTo(o.valor);
		}
		if (result == 0) {
			result = this.dono.compareTo(o.dono);
		}
		return result;
	}

	public void verificarAtributos() {
		if (this.contaEntrada instanceof ContaCredito) {
			throw new IllegalArgumentException("Conta de entrada do lançamento não pode ser do tipo conta de crédito: " + this.contaEntrada);
		}
		if (this.contaSaida instanceof ContaDebito) {
			throw new IllegalArgumentException("Conta de saída do lançamento não pode ser do tipo conta de débito: " + this.contaSaida);
		}
	}
}
