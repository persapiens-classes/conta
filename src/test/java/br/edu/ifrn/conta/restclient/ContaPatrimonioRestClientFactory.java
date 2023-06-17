package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.CategoriaDTO;
import br.edu.ifrn.conta.controller.ContaPatrimonioDTO;
import java.util.Optional;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuperBuilder
@Data
public class ContaPatrimonioRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;
    
    private CategoriaRestClientFactory categoriaRestClientFactory;

    public ContaPatrimonioRestClient contaPatrimonioRestClient() {
        return ContaPatrimonioRestClient.builder()
                .entityRestHelper(RestClientHelper.<ContaPatrimonioDTO>builder()
                    .endpoint("contaPatrimonio")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }
    
    public ContaPatrimonioDTO contaPatrimonio(String descricao, String categoriaDescricao) {
        Optional<ContaPatrimonioDTO> findByDescricao = contaPatrimonioRestClient().findByDescricao(descricao);
        if (findByDescricao.isEmpty()) {
            CategoriaDTO categoria = categoriaRestClientFactory.categoria(categoriaDescricao);
            
            ContaPatrimonioDTO result = ContaPatrimonioDTO.builder().descricao(descricao)
                    .categoria(categoria)
                    .build();
            return contaPatrimonioRestClient().save(result);
        } else {        
            return findByDescricao.get();
        }
    }

}
