package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.ContaCredito;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ContaCreditoFabrica {

    public final static String ESTAGIO = "estágio";
    public final static String RECEITA_CONJUGE = "receita com cônjuge";
    
    @Inject
    private ContaCreditoRepository contaCreditoRepository;

    @Inject
    private CategoriaFabrica categoriaFabrica;

    private ContaCredito contaCredito(String descricao, Categoria categoria) {
        ContaCredito contaCredito = contaCreditoRepository.findByDescricao(descricao);
        if (contaCredito == null) {
            contaCredito = ContaCredito.builder()
                .descricao(descricao)
                .categoria(categoria)
                .build();
            contaCreditoRepository.save(contaCredito);
        }
        return contaCredito;
    }

    public ContaCredito estagio() {
        return contaCredito(ESTAGIO, categoriaFabrica.salario());
    }

    public ContaCredito receitaComConjuge() {
        return contaCredito(RECEITA_CONJUGE, categoriaFabrica.categoriaReceitaConjuge());
    }
}
