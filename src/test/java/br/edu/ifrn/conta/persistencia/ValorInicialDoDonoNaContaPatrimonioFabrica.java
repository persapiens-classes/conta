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

package br.edu.ifrn.conta.persistencia;

import java.math.BigDecimal;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.ValorInicialDoDonoNaContaPatrimonio;

@Named
public class ValorInicialDoDonoNaContaPatrimonioFabrica {

	@Inject
	private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;

	public ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(
		Dono dono, ContaPatrimonio contaPatrimonio, BigDecimal valor) {
		ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio = this.valorInicialDoDonoNaContaPatrimonioRepository.findByDonoAndContaPatrimonio(dono, contaPatrimonio);

		if (valorInicialDoDonoNaContaPatrimonio == null) {
			valorInicialDoDonoNaContaPatrimonio
				= ValorInicialDoDonoNaContaPatrimonio.builder()
				.dono(dono)
				.contaPatrimonio(contaPatrimonio)
				.build();
		}
		valorInicialDoDonoNaContaPatrimonio.setValorInicial(valor.setScale(2));

		this.valorInicialDoDonoNaContaPatrimonioRepository.save(valorInicialDoDonoNaContaPatrimonio);

		return valorInicialDoDonoNaContaPatrimonio;
	}
}
