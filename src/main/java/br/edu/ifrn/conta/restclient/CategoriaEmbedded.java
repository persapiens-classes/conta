package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Categoria;

import lombok.Data;

@Data
public class CategoriaEmbedded {
    
    private Iterable<Categoria> categorias;

}
