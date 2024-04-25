package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.CategoriaDTO;
import br.edu.ifrn.conta.dto.ContaDebitoDTO;
import java.util.Optional;
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
        Optional<ContaDebitoDTO> findByDescricao = contaDebitoRestClient().findByDescricao(descricao);
        if (findByDescricao.isEmpty()) {
            CategoriaDTO categoria = categoriaRestClientFactory.categoria(categoriaDescricao);
            
            ContaDebitoDTO result = ContaDebitoDTO.builder().descricao(descricao)
                    .categoria(categoria)
                    .build();
            return contaDebitoRestClient().save(result);
        } else {        
            return findByDescricao.get();
        }
    }

}
