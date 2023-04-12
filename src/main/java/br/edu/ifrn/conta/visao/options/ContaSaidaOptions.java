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

package br.edu.ifrn.conta.visao.options;

import java.util.ArrayList;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import br.edu.ifrn.conta.dominio.Conta;
import br.edu.ifrn.conta.servico.ContaCreditoServico;

/**
 * Options de ContaSaida.
 * @author Marcelo Fernandes
 */
@ViewScoped
@Named
public class ContaSaidaOptions extends AbstractContaOptions {

	private static final long serialVersionUID = 1L;

	private transient ContaCreditoServico contaCreditoServico;

	@Inject
	public void setContaCreditoServico(ContaCreditoServico contaCreditoServico) {
		this.contaCreditoServico = contaCreditoServico;
	}

	@Override
	protected List<Conta> fillList() {
		List<Conta> result = new ArrayList<>();

		result.addAll(toList(this.contaCreditoServico.findAll()));
		result.addAll(toList(getContaPatrimonioServico().findAll()));

		return result;
	}

}
