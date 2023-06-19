package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import static br.edu.ifrn.conta.util.ContaPatrimonioConstants.CARTEIRA;
import static br.edu.ifrn.conta.util.ContaPatrimonioConstants.CONTA_CORRENTE;
import static br.edu.ifrn.conta.util.ContaPatrimonioConstants.CONTA_INVESTIMENTO;
import static br.edu.ifrn.conta.util.ContaPatrimonioConstants.POUPANCA;
import java.util.Optional;

@Component
public class ContaPatrimonioFactory {

    @Autowired
    private ContaPatrimonioRepository contaPatrimonioRepository;

    @Autowired
    private CategoriaFactory categoriaFactory;

    private ContaPatrimonio contaPatrimonio(String descricao, Categoria categoria) {
        Optional<ContaPatrimonio> findByDescricao = this.contaPatrimonioRepository.findByDescricao(descricao);
        if (findByDescricao.isEmpty()) {
            ContaPatrimonio contaPatrimonio = ContaPatrimonio.builder()
                    .descricao(descricao)
                    .categoria(categoria)
                    .build();
            return this.contaPatrimonioRepository.save(contaPatrimonio);
        } else {
            return findByDescricao.get();
        }
    }

    public ContaPatrimonio poupanca() {
        return contaPatrimonio(POUPANCA, this.categoriaFactory.banco());
    }

    public ContaPatrimonio contaCorrente() {
        return contaPatrimonio(CONTA_CORRENTE, this.categoriaFactory.banco());
    }

    public ContaPatrimonio contaInvestimento() {
        return contaPatrimonio(CONTA_INVESTIMENTO, this.categoriaFactory.banco());
    }

    public ContaPatrimonio carteira() {
        return contaPatrimonio(CARTEIRA, this.categoriaFactory.especie());
    }
}
