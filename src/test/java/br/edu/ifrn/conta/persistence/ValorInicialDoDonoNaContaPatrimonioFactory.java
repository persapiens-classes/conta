package br.edu.ifrn.conta.persistence;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;

@Component
public class ValorInicialDoDonoNaContaPatrimonioFactory {

	@Autowired
	private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;

	public ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(
		Dono dono, ContaPatrimonio contaPatrimonio, BigDecimal valor) {
		ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio = valorInicialDoDonoNaContaPatrimonioRepository.findByDonoAndContaPatrimonio(dono, contaPatrimonio);

		if (valorInicialDoDonoNaContaPatrimonio == null) {
			valorInicialDoDonoNaContaPatrimonio
				= ValorInicialDoDonoNaContaPatrimonio.builder()
				.dono(dono)
				.contaPatrimonio(contaPatrimonio)
				.build();
		}
		valorInicialDoDonoNaContaPatrimonio.setValorInicial(valor.setScale(2));

		this.valorInicialDoDonoNaContaPatrimonioRepository.save(valorInicialDoDonoNaContaPatrimonio);

		return valorInicialDoDonoNaContaPatrimonio;
	}
}
