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

import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;

@Named
public class ContaPatrimonioFabrica {

	public final static String POUPANCA = "poupança";

	@Inject
	private ContaPatrimonioRepository contaPatrimonioRepository;

	@Inject
	private CategoriaFabrica categoriaFabrica;

	private ContaPatrimonio contaPatrimonio(String descricao, Categoria categoria) {
		ContaPatrimonio contaPatrimonio = this.contaPatrimonioRepository.findByDescricao(descricao);
		if (contaPatrimonio == null) {
			contaPatrimonio = ContaPatrimonio.builder()
				.descricao(descricao)
				.categoria(categoria)
				.build();
			this.contaPatrimonioRepository.save(contaPatrimonio);
		}
		return contaPatrimonio;
	}

	public ContaPatrimonio poupanca() {
		return contaPatrimonio(POUPANCA, this.categoriaFabrica.banco());
	}
}
