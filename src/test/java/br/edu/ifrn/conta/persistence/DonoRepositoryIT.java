package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Dono;
import static br.edu.ifrn.conta.util.DonoConstants.MAMAE;
import static br.edu.ifrn.conta.util.DonoConstants.PAPAI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DonoRepositoryIT {

    @Autowired
    private DonoRepository donoRepository;

    @Autowired
    private DonoFactory donoFactory;

    @Test
    public void repositorioNaoEhNulo() {
        assertThat(this.donoRepository)
                .isNotNull();
    }

    @Test
    public void findByDescricao() {
        // cria o ambiente de teste
        Dono papai = this.donoFactory.papai();
        Dono mamae = this.donoFactory.mamae();

        // executa a operacao a ser testada
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(this.donoRepository.findByDescricao(PAPAI).get())
                .isEqualTo(papai);
        assertThat(this.donoRepository.findByDescricao(MAMAE).get())
                .isEqualTo(mamae);
    }

    @Test
    public void countByDescricao() {
        // cria o ambiente de teste
        this.donoFactory.papai();
        this.donoFactory.mamae();

        // executa a operacao a ser testada
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(this.donoRepository.countByDescricaoContains("a"))
                .isEqualTo(2);
    }

    @Test
    public void deleteByDescricao() {
        String descricaoUnica = "Dono único";

        // cria o ambiente de teste
        this.donoFactory.dono(descricaoUnica);

        // executa a operacao a ser testada
        this.donoRepository.deleteByDescricao(descricaoUnica);

        // verifica o efeito da execucao da operacao a ser testada
        assertThat(this.donoRepository.countByDescricaoContains(descricaoUnica))
                .isEqualTo(0);
    }
}
