package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ContaPatrimonioFabrica {
    
    public final static String POUPANCA = "poupança";

    @Inject
    private ContaPatrimonioRepository contaPatrimonioRepository;

    @Inject
    private CategoriaFabrica categoriaFabrica;

    private ContaPatrimonio contaPatrimonio(String descricao, Categoria categoria) {
        ContaPatrimonio contaPatrimonio = contaPatrimonioRepository.findByDescricao(descricao);
        if (contaPatrimonio == null) {
            contaPatrimonio = ContaPatrimonio.builder()
                .descricao(descricao)
                .categoria(categoria)
                .build();
            contaPatrimonioRepository.save(contaPatrimonio);
        }
        return contaPatrimonio;
    }

    public ContaPatrimonio poupanca () {
        return contaPatrimonio(POUPANCA, categoriaFabrica.banco());
    } 
}
