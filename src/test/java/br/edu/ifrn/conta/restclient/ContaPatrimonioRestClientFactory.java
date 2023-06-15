package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import lombok.Builder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@Builder
@Data
public class ContaPatrimonioRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;
    
    private CategoriaRestClientFactory categoriaRestClientFactory;

    public ContaPatrimonioRestClient contaPatrimonioRestClient() {
        return ContaPatrimonioRestClient.builder()
                .entityRestHelper(RestClientHelper.<ContaPatrimonio>builder()
                    .endpoint("contaPatrimonio")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }
    
    public ContaPatrimonio contaPatrimonio(String descricao, String categoriaDescricao) {
        ContaPatrimonio result = contaPatrimonioRestClient().findByDescricao(descricao);
        if (result == null) {
            Categoria categoria = categoriaRestClientFactory.categoria(categoriaDescricao);
            
            result = ContaPatrimonio.builder().descricao(descricao)
                    .categoria(categoria)
                    .build();
            result = contaPatrimonioRestClient().save(result);
            
            result = contaPatrimonioRestClient().findByDescricao(descricao);
        }
        
        return result;
    }

}
