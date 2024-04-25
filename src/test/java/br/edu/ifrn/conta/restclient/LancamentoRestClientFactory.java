package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.LancamentoDTO;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@SuperBuilder
@Data
public class LancamentoRestClientFactory {

    private String protocol;
    
    private String servername;
    
    private int port;

    private RestTemplate restTemplate;

    public LancamentoRestClient lancamentoRestClient() {
        return LancamentoRestClient.builder()
                .entityRestHelper(RestClientHelper.<LancamentoDTO>builder()
                    .endpoint("lancamento")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }

}
