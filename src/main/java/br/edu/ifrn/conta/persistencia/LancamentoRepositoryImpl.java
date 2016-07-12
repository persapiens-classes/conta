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

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.QLancamento;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * CrudRepository customizado.
 * @author Marcelo Fernandes
 */
public class LancamentoRepositoryImpl implements LancamentoRepositoryCustom {

	private final EntityManager entityManager;

	private final ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;

	@Inject
	public LancamentoRepositoryImpl(EntityManager entityManager, ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository) {
		this.entityManager = entityManager;
		this.valorInicialDoDonoNaContaPatrimonioRepository = valorInicialDoDonoNaContaPatrimonioRepository;
	}

	/**
	 * Calcula o valor do saldo do dono na conta patrimonio.
	 *
	 * @param dono do saldo
	 * @param contaPatrimonio do saldo
	 * @return saldo calculado
	 */
	@Override
	public BigDecimal saldo(Dono dono, ContaPatrimonio contaPatrimonio) {
		// recupera o valor inicial do dono na conta patrimonio
		BigDecimal result = this.valorInicialDoDonoNaContaPatrimonioRepository
			.findByDonoAndContaPatrimonio(dono, contaPatrimonio).getValorInicial();

		QLancamento qLancamento = QLancamento.lancamento;
		JPQLQueryFactory factory = new JPAQueryFactory(this.entityManager);

		// soma todos os lancamentos de credito do dono na conta patrimonio
		BigDecimal creditos = factory
			.from(qLancamento)
			.where(qLancamento.dono.eq(dono)
				.and(qLancamento.contaEntrada.eq(contaPatrimonio)))
			.select(qLancamento.valor.sum())
			.fetchOne();

		// subtrai todos os lancamentos de debito do dono na conta patrimonio
		BigDecimal debitos = factory
			.from(qLancamento)
			.where(qLancamento.dono.eq(dono)
				.and(qLancamento.contaSaida.eq(contaPatrimonio)))
			.select(qLancamento.valor.sum())
			.fetchOne();

		return result.add(creditos).subtract(debitos);
	}
}
