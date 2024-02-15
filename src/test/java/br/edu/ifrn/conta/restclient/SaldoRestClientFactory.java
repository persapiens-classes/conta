package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.dto.LancamentoDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@SuperBuilder
@Data
public class SaldoRestClientFactory {

	private String protocol;

	private String servername;

	private int port;

	private RestTemplate restTemplate;

	public SaldoRestClient saldoRestClient() {
		return SaldoRestClient.builder()
			.entityRestHelper(RestClientHelper.<LancamentoDTO>builder()
				.endpoint("")
				.protocol(protocol)
				.servername(servername)
				.port(port)
				.restTemplate(restTemplate)
				.build())
			.build();
	}

}
