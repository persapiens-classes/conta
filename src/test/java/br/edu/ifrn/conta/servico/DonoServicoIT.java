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

package br.edu.ifrn.conta.servico;

import jakarta.inject.Inject;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.persistencia.DonoFabrica;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DonoServicoIT {

	@Inject
	private DonoServico donoServico;

	@Inject
	private DonoFabrica donoFabrica;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.donoServico)
			.isNotNull();
	}

	@Test
	public void salvarUm() {
		// cria o ambiente de teste
		Dono dono = this.donoFabrica.mamae();

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.donoServico.findById(dono.getId()))
			.isEqualTo(dono);
	}

	@Test
	public void deletarUm() {
		// cria o ambiente de teste
		Dono dono = this.donoFabrica.dono("DONO UNICO DO DONO SERVIÇO IT");

		// executa a operacao a ser testada
		this.donoServico.delete(dono);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.donoServico.findById(dono.getId()))
			.isNull();
	}

}
