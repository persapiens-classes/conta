package br.edu.ifrn.conta.service;

import br.edu.ifrn.conta.domain.ContaCredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service of ContaCredito.
 */
@Service
public class ContaCreditoService extends ContaService<ContaCredito, Long> {
    @Autowired
    private CategoriaService categoriaService;
    
    public ContaCredito receitaTransferencia() {
        ContaCredito result = findByDescricao(ContaCredito.RECEITA_DE_TRANSFERENCIA);
        if (result == null) {
            result = ContaCredito.builder()
                    .descricao(ContaCredito.RECEITA_DE_TRANSFERENCIA)
                    .categoria(categoriaService.despesaTransferencia())
                    .build();
            result = save(result);
        }
        return result;
    }
}
