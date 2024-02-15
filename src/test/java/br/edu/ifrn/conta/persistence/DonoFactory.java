package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Dono;
import static br.edu.ifrn.conta.util.DonoConstants.MAMAE;
import static br.edu.ifrn.conta.util.DonoConstants.PAPAI;
import java.util.Optional;

@Component
public class DonoFactory {

	@Autowired
	private DonoRepository donoRepository;

	public Dono dono(String descricao) {
		Optional<Dono> findByDescricao = this.donoRepository.findByDescricao(descricao);
		if (findByDescricao.isEmpty()) {
			Dono dono = Dono.builder().descricao(descricao).build();
			return this.donoRepository.save(dono);
		}
		else {
			return findByDescricao.get();
		}
	}

	public Dono papai() {
		return dono(PAPAI);
	}

	public Dono mamae() {
		return dono(MAMAE);
	}

}
