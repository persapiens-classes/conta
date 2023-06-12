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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.dominio.Dono;

@Component
public class DonoFabrica {

	public final static String PAPAI = "Papai";
	public final static String MAMAE = "Mamãe";

	@Autowired
	private DonoRepository donoRepository;

	public Dono dono(String descricao) {
		Dono dono = this.donoRepository.findByDescricao(descricao);
		if (dono == null) {
			dono = Dono.builder().descricao(descricao).build();
			this.donoRepository.save(dono);
		}
		return dono;
	}

	public Dono papai() {
		return dono(PAPAI);
	}

	public Dono mamae() {
		return dono(MAMAE);
	}
}
