package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ContaCredito;

import lombok.Data;

@Data
public class ContaCreditoEmbedded {
    private Iterable<ContaCredito> contasCredito;
}
