package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaDebito;
import java.util.Optional;

@Component
public class ContaDebitoFactory {

    public final static String GASOLINA = "gasolina";

    @Autowired
    private ContaDebitoRepository contaDebitoRepository;

    @Autowired
    private CategoriaFactory categoriaFactory;

    private ContaDebito contaDebito(String descricao, Categoria categoria) {
        Optional<ContaDebito> findByDescricao = this.contaDebitoRepository.findByDescricao(descricao);
        if (findByDescricao.isEmpty()) {
            ContaDebito contaDebito = ContaDebito.builder()
                    .descricao(descricao)
                    .categoria(categoria)
                    .build();
            return this.contaDebitoRepository.save(contaDebito);
        } else {
            return findByDescricao.get();
        }
    }

    public ContaDebito gasolina() {
        return contaDebito(GASOLINA, this.categoriaFactory.transporte());
    }
}
