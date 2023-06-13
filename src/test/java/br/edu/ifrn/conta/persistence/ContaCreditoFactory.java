package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaCredito;

@Component
public class ContaCreditoFactory {

	public final static String ESTAGIO = "estágio";
	public final static String RECEITA_CONJUGE = "receita com cônjuge";

	@Autowired
	private ContaCreditoRepository contaCreditoRepository;

	@Autowired
	private CategoriaFactory categoriaFactory;

	public ContaCredito contaCredito(String descricao, Categoria categoria) {
		ContaCredito contaCredito = this.contaCreditoRepository.findByDescricao(descricao);
		if (contaCredito == null) {
			contaCredito = ContaCredito.builder()
				.descricao(descricao)
				.categoria(categoria)
				.build();
			this.contaCreditoRepository.save(contaCredito);
		}
		return contaCredito;
	}

	public ContaCredito estagio() {
		return contaCredito(ESTAGIO, this.categoriaFactory.salario());
	}

	public ContaCredito receitaComConjuge() {
		return contaCredito(RECEITA_CONJUGE, this.categoriaFactory.categoriaReceitaConjuge());
	}
}
