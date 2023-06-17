package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.CategoriaDTO;
import br.edu.ifrn.conta.controller.ContaCreditoDTO;
import java.util.Optional;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuperBuilder
@Data
public class ContaCreditoRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;
    
    private CategoriaRestClientFactory categoriaRestClientFactory;

    public ContaCreditoRestClient contaCreditoRestClient() {
        return ContaCreditoRestClient.builder()
                .entityRestHelper(RestClientHelper.<ContaCreditoDTO>builder()
                    .endpoint("contaCredito")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }
    
    public ContaCreditoDTO contaCredito(String descricao, String categoriaDescricao) {
        Optional<ContaCreditoDTO> findByDescricao = contaCreditoRestClient().findByDescricao(descricao);
        if (findByDescricao.isEmpty()) {
            CategoriaDTO categoria = categoriaRestClientFactory.categoria(categoriaDescricao);
            
            ContaCreditoDTO result = ContaCreditoDTO.builder().descricao(descricao)
                    .categoria(categoria)
                    .build();
            return contaCreditoRestClient().save(result);
        } else {        
            return findByDescricao.get();
        }
    }

}
