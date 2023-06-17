package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Categoria;
import java.util.Optional;

@Component
public class CategoriaFactory {

    public final static String SALARIO = "Salário";
    public final static String TRANSPORTE = "Transporte";
    public final static String BANCO = "Banco";

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria categoria(String descricao) {
        Optional<Categoria> findByDescricao = this.categoriaRepository.findByDescricao(descricao);
        if (findByDescricao.isEmpty()) {
            Categoria categoria = Categoria.builder().descricao(descricao).build();
            return this.categoriaRepository.save(categoria);
        } else {
            return findByDescricao.get();
        }
    }

    public Categoria transporte() {
        return categoria(TRANSPORTE);
    }

    public Categoria banco() {
        return categoria(BANCO);
    }

    public Categoria salario() {
        return categoria(SALARIO);
    }
}
