package br.edu.ifrn.conta.dominio;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"lancamentos", "valoresIniciaisNasContasPatrimonio"})
@EqualsAndHashCode(exclude = {"lancamentos", "valoresIniciaisNasContasPatrimonio"})
@Builder
@Entity
@SequenceGenerator(sequenceName = "seq_dono", name = "ID_SEQUENCE", allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Dono implements Serializable, Comparable<Dono> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;
    
    @Column(nullable = false)
    private String descricao;

    @Singular
    @OneToMany(mappedBy = "dono")
    private Set<Lancamento> lancamentos;
    
    @Singular("valorInicialNaContaPatrimonio")
    @OneToMany(mappedBy = "dono")
    private Set<ValorInicialDoDonoNaContaPatrimonio> valoresIniciaisNasContasPatrimonio;

    @Override
    public int compareTo(Dono o) {
        return descricao.compareTo(o.descricao);
    }
    
}
