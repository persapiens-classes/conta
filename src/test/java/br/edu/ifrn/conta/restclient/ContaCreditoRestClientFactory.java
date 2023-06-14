package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaCredito;
import lombok.Builder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@Builder
@Data
public class ContaCreditoRestClientFactory {

    private int port;

    private RestTemplate restTemplate;
    
    private CategoriaRestClientFactory categoriaRestClientFactory;

    public ContaCreditoRestClient contaCreditoRestClient() {
        return ContaCreditoRestClient.builder()
                .entityRestHelper(RestClientHelper.<ContaCredito>builder()
                    .endpoint("contasCredito")
                    .protocol("http")
                    .servername("localhost")
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }
    
    public ContaCredito contaCredito(String descricao, String categoriaDescricao) {
        ContaCredito result = contaCreditoRestClient().findByDescricao(descricao);
        if (result == null) {
            Categoria categoria = categoriaRestClientFactory.categoria(categoriaDescricao);
            
            result = ContaCredito.builder().descricao(descricao)
                    .categoria(categoria)
                    .build();
            result = contaCreditoRestClient().save(result);
        }
        
        return result;
    }

}
