package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaPatrimonio;

@Component
public class ContaPatrimonioFactory {

	public final static String POUPANCA = "poupanca";

	@Autowired
	private ContaPatrimonioRepository contaPatrimonioRepository;

	@Autowired
	private CategoriaFactory categoriaFactory;

	private ContaPatrimonio contaPatrimonio(String descricao, Categoria categoria) {
		ContaPatrimonio contaPatrimonio = this.contaPatrimonioRepository.findByDescricao(descricao);
		if (contaPatrimonio == null) {
			contaPatrimonio = ContaPatrimonio.builder()
				.descricao(descricao)
				.categoria(categoria)
				.build();
			this.contaPatrimonioRepository.save(contaPatrimonio);
		}
		return contaPatrimonio;
	}

	public ContaPatrimonio poupanca() {
		return contaPatrimonio(POUPANCA, this.categoriaFactory.banco());
	}
}
