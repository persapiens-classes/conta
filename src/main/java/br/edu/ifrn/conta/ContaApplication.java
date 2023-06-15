package br.edu.ifrn.conta;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.Conta;
import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.domain.ContaDebito;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Conta Configuration class.
 */
@SpringBootApplication
public class ContaApplication implements RepositoryRestConfigurer {

    protected ContaApplication() {
    }

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Dono.class, Categoria.class, Conta.class,
    ContaCredito.class, ContaDebito.class, ContaPatrimonio.class, 
    ValorInicialDoDonoNaContaPatrimonio.class);
    }
}
