package br.edu.ifrn.conta.persistence;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;
import java.util.Optional;

@Component
public class ValorInicialDoDonoNaContaPatrimonioFactory {

	@Autowired
	private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;

	public ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(Dono dono,
			ContaPatrimonio contaPatrimonio, BigDecimal valor) {
		Optional<ValorInicialDoDonoNaContaPatrimonio> findBy = valorInicialDoDonoNaContaPatrimonioRepository
			.findByDonoAndContaPatrimonio(dono, contaPatrimonio);

		ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio;
		if (findBy.isEmpty()) {
			valorInicialDoDonoNaContaPatrimonio = ValorInicialDoDonoNaContaPatrimonio.builder()
				.dono(dono)
				.contaPatrimonio(contaPatrimonio)
				.build();
		}
		else {
			valorInicialDoDonoNaContaPatrimonio = findBy.get();
		}

		valorInicialDoDonoNaContaPatrimonio.setValorInicial(valor.setScale(2));

		return this.valorInicialDoDonoNaContaPatrimonioRepository.save(valorInicialDoDonoNaContaPatrimonio);
	}

}
