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
package br.edu.ifrn.conta.dominio;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

/**
 * Dono entity.
 *
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ToString(exclude = {"lancamentos", "valoresIniciaisNasContasPatrimonio"})
@EqualsAndHashCode(exclude = {"lancamentos", "valoresIniciaisNasContasPatrimonio"})
@Builder
@Entity
@SequenceGenerator(sequenceName = "seq_dono", name = "ID_SEQUENCE", allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Dono implements Serializable, Comparable<Dono> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;

    @Column(nullable = false, unique = true)
    private String descricao;

    @Singular
    @OneToMany(mappedBy = "dono")
    private Set<Lancamento> lancamentos;

    @Singular("valorInicialNaContaPatrimonio")
    @OneToMany(mappedBy = "dono")
    private Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciaisNasContasPatrimonio;

    @Override
    public int compareTo(Dono o) {
        return this.descricao.compareTo(o.descricao);
    }

}
