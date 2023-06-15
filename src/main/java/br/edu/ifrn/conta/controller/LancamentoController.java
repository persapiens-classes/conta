package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.Lancamento;
import br.edu.ifrn.conta.service.ContaPatrimonioService;
import br.edu.ifrn.conta.service.ContaService;
import br.edu.ifrn.conta.service.DonoService;
import br.edu.ifrn.conta.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Lancamento controller.
 */
@RestController
@RequestMapping("/lancamento")
public class LancamentoController extends CrudController<LancamentoDTO, Lancamento, Long> {

    @Autowired
    private LancamentoService lancamentoService;
    @Autowired
    private DonoService donoService;
    @Autowired
    private ContaPatrimonioService contaPatrimonioService;
    @Autowired
    private ContaService contaService;

    @Override
    protected Lancamento toEntity(LancamentoDTO dto) {
        return Lancamento.builder()
            .contaEntrada(contaService.findByDescricao(
        dto.getContaEntrada().getDescricao()))
            .contaSaida(contaService.findByDescricao(
        dto.getContaSaida().getDescricao()))
            .dono(donoService.findByDescricao(dto.getDono().getDescricao()))
            .data(dto.getData())
            .valor(dto.getValor())
            .descricao(dto.getDescricao())
            .build();
    }

    @Override
    protected LancamentoDTO toDTO(Lancamento entity) {
        return LancamentoDTO.builder()
            .contaEntrada(ContaDTO.builder()
                .descricao(entity.getContaEntrada().getDescricao())
                .categoria(CategoriaDTO.builder().descricao(
            entity.getContaEntrada().getCategoria().getDescricao()).build())
                .build())
            .contaSaida(ContaDTO.builder()
                .descricao(entity.getContaSaida().getDescricao())
                .categoria(CategoriaDTO.builder().descricao(
            entity.getContaSaida().getCategoria().getDescricao()).build())
                .build())
            .dono(DonoDTO.builder().descricao(entity.getDono().getDescricao()).build())
            .data(entity.getData())
            .valor(entity.getValor())
            .descricao(entity.getDescricao())
            .build();
    }
}
