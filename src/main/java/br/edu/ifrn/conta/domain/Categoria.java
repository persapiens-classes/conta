package br.edu.ifrn.conta.domain;

import java.io.Serializable;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

/**
 * Categoria entity.
 */
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@Getter
@Setter
@ToString(exclude = "contas")
@EqualsAndHashCode(of = "descricao")
@SuperBuilder
@Entity
@SequenceGenerator(sequenceName = "seq_categoria", name = "ID_SEQUENCE", allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Categoria implements Serializable, Comparable<Categoria> {

    private static final long serialVersionUID = 1L;
    public final static String CATEGORIA_DESPESA_TRANSFERENCIA = "categoria despesa de transferência";
    public final static String CATEGORIA_RECEITA_TRANSFERENCIA = "categoria receita de transferência";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;

    @Column(nullable = false, unique = true)
    private String descricao;

    @Singular
    @OneToMany(mappedBy = "categoria")
    private Set<Conta> contas;

    @Override
    public int compareTo(Categoria o) {
        return this.descricao.compareTo(o.getDescricao());
    }

}
