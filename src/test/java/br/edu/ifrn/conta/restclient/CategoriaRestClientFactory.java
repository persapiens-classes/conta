package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.CategoriaDTO;
import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@SuperBuilder
@Data
public class CategoriaRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;

    public CategoriaRestClient categoriaRestClient() {
        return CategoriaRestClient.builder()
                .entityRestHelper(RestClientHelper.<CategoriaDTO>builder()
                    .endpoint("categoria")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }

    public CategoriaDTO categoria(String descricao) {
        Optional<CategoriaDTO> findByDescricao = categoriaRestClient().findByDescricao(descricao);
        if (findByDescricao.isEmpty()) {
            CategoriaDTO result = CategoriaDTO.builder().descricao(descricao).build();
            return categoriaRestClient().save(result);
        } else {
            return findByDescricao.get();
        }
    }

}
