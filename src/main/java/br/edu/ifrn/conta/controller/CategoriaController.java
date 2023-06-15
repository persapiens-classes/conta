package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Categoria controller.
 */
@RestController
@RequestMapping("/categoria")
public class CategoriaController extends CrudController<CategoriaDTO, Categoria, Long> {

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/findByDescricao")
    public CategoriaDTO findByDescricao(@RequestParam String descricao) {
        return toDTO(categoriaService.findByDescricao(descricao));
    }

    @Override
    protected Categoria toEntity(CategoriaDTO dto) {
        return Categoria.builder()
           .descricao(dto.getDescricao())
           .build();
    }

    @Override
    protected CategoriaDTO toDTO(Categoria entity) {
        return CategoriaDTO.builder()
           .descricao(entity.getDescricao())
           .build();
    }

}
