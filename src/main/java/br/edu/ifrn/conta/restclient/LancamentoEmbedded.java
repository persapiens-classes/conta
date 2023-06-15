package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Lancamento;

import lombok.Data;

@Data
public class LancamentoEmbedded {
    private Iterable<Lancamento> lancamentos;
}
