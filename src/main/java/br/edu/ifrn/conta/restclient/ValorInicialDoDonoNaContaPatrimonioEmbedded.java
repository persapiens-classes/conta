package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;

import lombok.Data;

@Data
public class ValorInicialDoDonoNaContaPatrimonioEmbedded {
    private Iterable<ValorInicialDoDonoNaContaPatrimonio> valoresIniciaisDoDonoNaContaPatrimonio;
}
