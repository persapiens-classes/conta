package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Dono;

@Component
public class DonoFactory {

	public final static String PAPAI = "Papai";
	public final static String MAMAE = "Mamae";
	public final static String TITIO = "Titio";

	@Autowired
	private DonoRepository donoRepository;

	public Dono dono(String descricao) {
		Dono dono = this.donoRepository.findByDescricao(descricao);
		if (dono == null) {
			dono = Dono.builder().descricao(descricao).build();
			this.donoRepository.save(dono);
		}
		return dono;
	}

	public Dono papai() {
		return dono(PAPAI);
	}

	public Dono mamae() {
		return dono(MAMAE);
	}
}
