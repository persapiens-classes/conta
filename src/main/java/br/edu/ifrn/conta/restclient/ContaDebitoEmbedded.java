package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.ContaDebito;

import lombok.Data;

@Data
public class ContaDebitoEmbedded {
    private Iterable<ContaDebito> contasDebito;
}
