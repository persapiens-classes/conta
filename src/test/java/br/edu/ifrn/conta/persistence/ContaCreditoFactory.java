package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaCredito;
import static br.edu.ifrn.conta.util.ContaCreditoConstants.ESTAGIO;
import java.util.Optional;

@Component
public class ContaCreditoFactory {

	@Autowired
	private ContaCreditoRepository contaCreditoRepository;

	@Autowired
	private CategoriaFactory categoriaFactory;

	public ContaCredito contaCredito(String descricao, Categoria categoria) {
		Optional<ContaCredito> findByDescricao = this.contaCreditoRepository.findByDescricao(descricao);
		if (findByDescricao.isEmpty()) {
			ContaCredito contaCredito = ContaCredito.builder().descricao(descricao).categoria(categoria).build();
			return this.contaCreditoRepository.save(contaCredito);
		}
		else {
			return findByDescricao.get();
		}
	}

	public ContaCredito estagio() {
		return contaCredito(ESTAGIO, this.categoriaFactory.salario());
	}

}
