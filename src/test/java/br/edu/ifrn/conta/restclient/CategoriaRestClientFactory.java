package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Categoria;
import lombok.Builder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@Builder
@Data
public class CategoriaRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;

    public CategoriaRestClient categoriaRestClient() {
        return CategoriaRestClient.builder()
                .entityRestHelper(RestClientHelper.<Categoria>builder()
                    .endpoint("categoria")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }

    public Categoria categoria(String descricao) {
        Categoria result = categoriaRestClient().findByDescricao(descricao);
        if (result == null) {
            result = Categoria.builder().descricao(descricao).build();
            result = categoriaRestClient().save(result);
        }
        
        return result;
    }

}