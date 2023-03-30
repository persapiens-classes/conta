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
import java.util.Date;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import br.edu.ifrn.conta.dominio.Conta;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.Lancamento;

@Named
public class LancamentoFabrica {

	@Inject
	private LancamentoRepository lancamentoRepository;

	public Lancamento lancamento(Dono dono, Conta contaEntrada, Conta contaSaida, BigDecimal valor) {
		Lancamento lancamento = Lancamento.builder()
			.contaEntrada(contaEntrada)
			.contaSaida(contaSaida)
			.data(new Date())
			.dono(dono)
			.valor(valor.setScale(2))
			.build();
		this.lancamentoRepository.save(lancamento);
		return lancamento;
	}
}
