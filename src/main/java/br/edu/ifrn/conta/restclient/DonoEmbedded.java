package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Dono;

import lombok.Data;

@Data
public class DonoEmbedded {
    
    private Iterable<Dono> donos;

}
