package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.DonoDTO;
import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@SuperBuilder
@Data
public class DonoRestClientFactory {

    private String protocol;
    
    private String servername;

    private int port;

    private RestTemplate restTemplate;

    public DonoRestClient donoRestClient() {
        return DonoRestClient.builder()
                .entityRestHelper(RestClientHelper.<DonoDTO>builder()
                    .endpoint("dono")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }
    
    public DonoDTO dono(String descricao) {
        Optional<DonoDTO> findByDescricao = donoRestClient().findByDescricao(descricao);
        if (findByDescricao.isEmpty()) {
            DonoDTO result = DonoDTO.builder().descricao(descricao).build();
            return donoRestClient().save(result);
        } else {        
            return findByDescricao.get();
        }
    }

}
