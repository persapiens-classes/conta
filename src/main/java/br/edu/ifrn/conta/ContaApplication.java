package br.edu.ifrn.conta;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * Conta Configuration class.
 */
@SpringBootApplication
public class ContaApplication implements RepositoryRestConfigurer {

    protected ContaApplication() {
    }
}
