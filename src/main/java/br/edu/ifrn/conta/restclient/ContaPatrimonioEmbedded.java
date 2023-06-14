package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ContaPatrimonio;

import lombok.Data;

@Data
public class ContaPatrimonioEmbedded {
    private Iterable<ContaPatrimonio> contasPatrimonio;
}
