package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaDebito;
import lombok.Builder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@Builder
@Data
public class ContaDebitoRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;
    
    private CategoriaRestClientFactory categoriaRestClientFactory;

    public ContaDebitoRestClient contaDebitoRestClient() {
        return ContaDebitoRestClient.builder()
                .entityRestHelper(RestClientHelper.<ContaDebito>builder()
                    .endpoint("contaDebito")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }
    
    public ContaDebito contaDebito(String descricao, String categoriaDescricao) {
        ContaDebito result = contaDebitoRestClient().findByDescricao(descricao);
        if (result == null) {
            Categoria categoria = categoriaRestClientFactory.categoria(categoriaDescricao);
            
            result = ContaDebito.builder().descricao(descricao)
                    .categoria(categoria)
                    .build();
            result = contaDebitoRestClient().save(result);
        }
        
        return result;
    }

}
