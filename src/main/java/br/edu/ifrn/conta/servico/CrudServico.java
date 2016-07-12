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

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacao padrao de servico crud delegando a implementacao para o
 * repositorio.
 * @param <T> tipo da entidade
 * @param <ID> tipo do id da entidade
 * @author Marcelo Fernandes
 */
@Transactional(readOnly = true)
public class CrudServico<T extends Object, ID extends Serializable> {

	private CrudRepository<T, ID> repository;

	@Inject
	public void setRepositorio(CrudRepository<T, ID> repository) {
		this.repository = repository;
	}

	@Transactional
	public <S extends T> Iterable<S> save(Iterable<S> objetos) {
		return this.repository.save(objetos);
	}

	@Transactional
	public <S extends T> S save(S objeto) {
		return this.repository.save(objeto);
	}

	@Transactional
	public void delete(Iterable<? extends T> objetos) {
		this.repository.delete(objetos);
	}

	@Transactional
	public void delete(ID id) {
		this.repository.delete(id);
	}

	@Transactional
	public void delete(T objeto) {
		this.repository.delete(objeto);
	}

	@Transactional
	public void deleteAll() {
		this.repository.deleteAll();
	}

	public T findOne(ID id) {
		return this.repository.findOne(id);
	}

	public Iterable<T> findAll() {
		return this.repository.findAll();
	}

	public Iterable<T> findAll(Iterable<ID> ids) {
		return this.repository.findAll(ids);
	}

	public long count() {
		return this.repository.count();
	}

	public boolean exists(ID id) {
		return this.repository.exists(id);
	}

}
