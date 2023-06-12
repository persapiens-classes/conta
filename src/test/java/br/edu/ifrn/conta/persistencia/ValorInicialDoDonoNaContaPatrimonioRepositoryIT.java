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

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.ValorInicialDoDonoNaContaPatrimonio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ValorInicialDoDonoNaContaPatrimonioRepositoryIT {

	@Autowired
	private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;

	@Autowired
	private DonoFabrica donoFabrica;

	@Autowired
	private ValorInicialDoDonoNaContaPatrimonioFabrica valorInicialDoDonoNaContaPatrimonioFabrica;

	@Autowired
	private ContaPatrimonioFabrica contaPatrimonioFabrica;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.valorInicialDoDonoNaContaPatrimonioRepository)
			.isNotNull();
	}

	@Test
	public void findByDonoAndContaPatrimonio() {
		// cria o ambiente de teste
		Dono papai = this.donoFabrica.papai();

		ContaPatrimonio contaPatrimonio = this.contaPatrimonioFabrica.poupanca();

		ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio
			= this.valorInicialDoDonoNaContaPatrimonioFabrica.valorInicialDoDonoNaContaPatrimonio(papai, contaPatrimonio, new BigDecimal(100));

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.valorInicialDoDonoNaContaPatrimonioRepository.findByDonoAndContaPatrimonio(papai, contaPatrimonio))
			.isEqualTo(valorInicialDoDonoNaContaPatrimonio);
	}
}
