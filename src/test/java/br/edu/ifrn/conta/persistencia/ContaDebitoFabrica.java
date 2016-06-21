package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.ContaDebito;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ContaDebitoFabrica {

    public final static String GASOLINA = "gasolina";
    public final static String DESPESA_CONJUGE = "despesa com cônjuge";

    @Inject
    private ContaDebitoRepository contaDebitoRepository;

    @Inject
    private CategoriaFabrica categoriaFabrica;

    private ContaDebito contaDebito(String descricao, Categoria categoria) {
        ContaDebito contaDebito = contaDebitoRepository.findByDescricao(descricao);
        if (contaDebito == null) {
            contaDebito = ContaDebito.builder()
                .descricao(descricao)
                .categoria(categoria)
                .build();
            contaDebitoRepository.save(contaDebito);
        }
        return contaDebito;
    }

    public ContaDebito gasolina() {
        return contaDebito(GASOLINA, categoriaFabrica.transporte());
    }

    public ContaDebito despesaComConjuge() {
        return contaDebito(DESPESA_CONJUGE, categoriaFabrica.categoriaDespesaConjuge());
    }
}
