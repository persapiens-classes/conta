package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaDebito;

@Component
public class ContaDebitoFactory {

    public final static String GASOLINA = "gasolina";

    @Autowired
    private ContaDebitoRepository contaDebitoRepository;

    @Autowired
    private CategoriaFactory categoriaFactory;

    private ContaDebito contaDebito(String descricao, Categoria categoria) {
        ContaDebito contaDebito = this.contaDebitoRepository.findByDescricao(descricao);
        if (contaDebito == null) {
            contaDebito = ContaDebito.builder()
                    .descricao(descricao)
                    .categoria(categoria)
                    .build();
            this.contaDebitoRepository.save(contaDebito);
        }
        return contaDebito;
    }

    public ContaDebito gasolina() {
        return contaDebito(GASOLINA, this.categoriaFactory.transporte());
    }
}
