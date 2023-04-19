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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

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
 * Valor Inicial do Dono na Conta Patrimonio entity.
 * @author Marcelo Fernandes
 */
@SuppressFBWarnings({"EI_EXPOSE_REP2"})	
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "id")
@Builder
@Entity
@SequenceGenerator(sequenceName = "seq_valorInicial", name = "ID_SEQUENCE", allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValorInicialDoDonoNaContaPatrimonio implements Serializable, Comparable<ValorInicialDoDonoNaContaPatrimonio> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
	private Long id;

	@Column(nullable = false)
	private BigDecimal valorInicial;

	@SuppressFBWarnings({"EI_EXPOSE_REP"})	
	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_valorInicial_dono"))
	private Dono dono;

	@SuppressFBWarnings({"EI_EXPOSE_REP"})	
	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_valorInicial_contaPatrimonio"))
	private ContaPatrimonio contaPatrimonio;

	@Override
	public int compareTo(ValorInicialDoDonoNaContaPatrimonio o) {
		int result = this.valorInicial.compareTo(o.valorInicial);
		if (result == 0) {
			result = this.dono.compareTo(o.dono);
		}
		if (result == 0) {
			result = this.contaPatrimonio.compareTo(o.contaPatrimonio);
		}
		return result;
	}

}
