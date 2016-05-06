package br.edu.ifrn.conta.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "id")
@Builder
@Entity
@SequenceGenerator(sequenceName = "seq_valorInicial", name = "ID_SEQUENCE", allocationSize = 1)
public class ValorInicialDoDonoNaContaPatrimonio implements Serializable, Comparable<ValorInicialDoDonoNaContaPatrimonio> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;
    
    @Column(nullable = false)
    private BigDecimal valorInicial;
    
    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_valorInicial_dono"))
    private Dono dono;
    
    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_valorInicial_contaPatrimonio"))
    private ContaPatrimonio contaPatrimonio;

    @Override
    public int compareTo(ValorInicialDoDonoNaContaPatrimonio o) {
        int result = valorInicial.compareTo(o.valorInicial);
        if (result == 0)
        {
            result = dono.compareTo(o.dono);
        }
        if (result == 0)
        {
            result = contaPatrimonio.compareTo(o.contaPatrimonio);
        }
        return result;
    }
    
}
