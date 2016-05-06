package br.edu.ifrn.conta.dominio;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"id", "contas"})
@Builder
@Entity
@SequenceGenerator(sequenceName = "seq_categoria", name = "ID_SEQUENCE", allocationSize = 1)
public class Categoria implements Serializable, Comparable<Categoria> {

	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;

    private String descricao;
    
    @Singular
    @OneToMany(mappedBy = "categoria")
    private Set<Conta> contas;

    @Override
    public int compareTo(Categoria o) {
        return descricao.compareTo(o.descricao);
    }
    
}
