package br.edu.ifrn.conta.service;

import java.io.Serializable;

import br.edu.ifrn.conta.domain.Conta;
import org.springframework.stereotype.Service;

/**
 * Service of Conta.
 * @param <T> account 
 * @param <ID> account id
 */
@Service
public class ContaService<T extends Conta, ID extends Serializable> extends CrudService<T, ID> {
}
