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

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

/**
 * Conta Patrimonio entity.
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ToString(callSuper = true, exclude = "valoresIniciaisDosDonos")
@EqualsAndHashCode(callSuper = true, exclude = "valoresIniciaisDosDonos")
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContaPatrimonio extends Conta {

	private static final long serialVersionUID = 1L;

	@Builder
	public ContaPatrimonio(@Singular("valorInicialDoDono") Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciaisDosDonos, Long id, String descricao, Categoria categoria) {
		super(id, descricao, categoria);
		this.valoresIniciaisDosDonos = valoresIniciaisDosDonos;
	}

	@OneToMany(mappedBy = "contaPatrimonio")
	private Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciaisDosDonos;

}
