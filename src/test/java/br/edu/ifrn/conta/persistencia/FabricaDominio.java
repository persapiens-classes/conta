package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Categoria;
import br.edu.ifrn.conta.dominio.Conta;
import br.edu.ifrn.conta.dominio.ContaCredito;
import br.edu.ifrn.conta.dominio.ContaDebito;
import br.edu.ifrn.conta.dominio.ContaPatrimonio;
import br.edu.ifrn.conta.dominio.Dono;
import br.edu.ifrn.conta.dominio.Lancamento;
import br.edu.ifrn.conta.dominio.ValorInicialDoDonoNaContaPatrimonio;
import java.math.BigDecimal;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FabricaDominio {

    public final static String PAPAI = "Papai";
    public final static String MAMAE = "Mamãe";
    public final static String SALARIO = "Salário";
    public final static String TRANSPORTE = "Transporte";
    public final static String BANCO = "Banco";
    public final static String GASOLINA = "gasolina";
    public final static String POUPANCA = "poupança";
    public final static String ESTAGIO = "estágio";
    
    @Inject
    private DonoRepository donoRepository;

    @Inject
    private CategoriaRepository categoriaRepository;

    @Inject
    private ContaCreditoRepository contaCreditoRepository;

    @Inject
    private ContaDebitoRepository contaDebitoRepository;

    @Inject
    private ContaPatrimonioRepository contaPatrimonioRepository;
    
    @Inject
    private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;
    
    @Inject
    private LancamentoRepository lancamentoRepository;

    public Dono papai() {
        Dono papai = Dono.builder().descricao(PAPAI).build();
        donoRepository.save(papai);
        return papai;
    }

    public Dono mamae() {
        Dono mamae = Dono.builder().descricao(MAMAE).build();
        donoRepository.save(mamae);
        return mamae;
    }

    public Categoria transporte() {
        Categoria categoria = Categoria.builder().descricao(TRANSPORTE).build();
        categoriaRepository.save(categoria);
        return categoria;
    }
    
    public Categoria banco() {
        Categoria categoria = Categoria.builder().descricao(BANCO).build();
        categoriaRepository.save(categoria);
        return categoria;
    }
    
    public Categoria salario() {
        Categoria categoria = Categoria.builder().descricao(SALARIO).build();
        categoriaRepository.save(categoria);
        return categoria;
    }

    public ContaCredito estagio() {
        ContaCredito contaCredito = ContaCredito.builder()
            .descricao(ESTAGIO)
            .categoria(salario())
            .build();
        contaCreditoRepository.save(contaCredito);
        return contaCredito;
    }

    public ContaDebito gasolina() {
        // cria o ambiente de teste
        ContaDebito contaDebito = ContaDebito.builder()
            .descricao(GASOLINA)
            .categoria(transporte())
            .build();
        contaDebitoRepository.save(contaDebito);
        return contaDebito;
    }

    public ContaPatrimonio poupanca () {
        ContaPatrimonio contaPatrimonio = ContaPatrimonio.builder()
            .descricao(POUPANCA)
            .categoria(banco())
            .build();
        contaPatrimonioRepository.save(contaPatrimonio);
        return contaPatrimonio;
    } 

    public ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio(
                Dono dono, ContaPatrimonio contaPatrimonio, BigDecimal valor) {        
        ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio
            = ValorInicialDoDonoNaContaPatrimonio.builder()
                .dono(dono)
                .contaPatrimonio(contaPatrimonio)
                .valorInicial(valor.setScale(2))
              .build();
        valorInicialDoDonoNaContaPatrimonioRepository.save(valorInicialDoDonoNaContaPatrimonio);
        valorInicialDoDonoNaContaPatrimonioRepository.save(valorInicialDoDonoNaContaPatrimonio);
        return valorInicialDoDonoNaContaPatrimonio;
    }

    public Lancamento lancamento(Dono dono, Conta contaEntrada, Conta contaSaida, BigDecimal valor) {        
        Lancamento lancamento = Lancamento.builder()
            .contaEntrada(contaEntrada)
            .contaSaida(contaSaida)
            .data(new Date())
            .dono(dono)
            .valor(valor.setScale(2))
            .build();
        lancamentoRepository.save(lancamento);
        return lancamento;
    }
}
