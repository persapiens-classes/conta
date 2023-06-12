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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Autowired
	public void setRepositorio(CrudRepository<T, ID> repository) {
		this.repository = repository;
	}

	@Transactional
	public <S extends T> Iterable<S> saveAll(Iterable<S> objetos) {
		return this.repository.saveAll(objetos);
	}

	@Transactional
	public <S extends T> S save(S objeto) {
		return this.repository.save(objeto);
	}

	@Transactional
	public void deleteAll(Iterable<? extends T> objetos) {
		this.repository.deleteAll(objetos);
	}

	@Transactional
	public void deleteById(ID id) {
		this.repository.deleteById(id);
	}

	@Transactional
	public void delete(T objeto) {
		this.repository.delete(objeto);
	}

	@Transactional
	public void deleteAll() {
		this.repository.deleteAll();
	}

	public Optional<T> findById(ID id) {
		return this.repository.findById(id);
	}

	public Iterable<T> findAll() {
		return this.repository.findAll();
	}

	public Iterable<T> findAllById(Iterable<ID> ids) {
		return this.repository.findAllById(ids);
	}

	public long count() {
		return this.repository.count();
	}

	public boolean existsById(ID id) {
		return this.repository.existsById(id);
	}

}
