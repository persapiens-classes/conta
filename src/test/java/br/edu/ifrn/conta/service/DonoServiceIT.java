package br.edu.ifrn.conta.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.persistence.DonoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DonoServiceIT {

	@Autowired
	private DonoService donoServico;

	@Autowired
	private DonoFactory donoFactory;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.donoServico)
			.isNotNull();
	}

	@Test
	public void salvarUm() {
		// cria o ambiente de teste
		Dono dono = this.donoFactory.mamae();

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.donoServico.findById(dono.getId()).get())
			.isEqualTo(dono);
	}

	@Test
	public void deletarUm() {
		// cria o ambiente de teste
		Dono dono = this.donoFactory.dono("DONO UNICO DO DONO SERVIÇO IT");

		// executa a operacao a ser testada
		this.donoServico.delete(dono);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.donoServico.findById(dono.getId()).isPresent())
			.isFalse();
	}

}
