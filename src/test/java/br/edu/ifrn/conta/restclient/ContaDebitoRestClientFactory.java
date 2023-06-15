package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.CategoriaDTO;
import br.edu.ifrn.conta.controller.ContaDebitoDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuperBuilder
@Data
public class ContaDebitoRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;
    
    private CategoriaRestClientFactory categoriaRestClientFactory;

    public ContaDebitoRestClient contaDebitoRestClient() {
        return ContaDebitoRestClient.builder()
                .entityRestHelper(RestClientHelper.<ContaDebitoDTO>builder()
                    .endpoint("contaDebito")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }
    
    public ContaDebitoDTO contaDebito(String descricao, String categoriaDescricao) {
        ContaDebitoDTO result = contaDebitoRestClient().findByDescricao(descricao);
        if (result == null) {
            CategoriaDTO categoria = categoriaRestClientFactory.categoria(categoriaDescricao);
            
            result = ContaDebitoDTO.builder().descricao(descricao)
                    .categoria(categoria)
                    .build();
            result = contaDebitoRestClient().save(result);
        }
        
        return result;
    }

}
