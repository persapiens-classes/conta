package br.edu.ifrn.conta.persistencia;

import br.edu.ifrn.conta.dominio.Dono;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DonoFabrica {

    public final static String PAPAI = "Papai";
    public final static String MAMAE = "Mamãe";
    
    @Inject
    private DonoRepository donoRepository;

    private Dono dono(String descricao) {
        Dono dono = donoRepository.findByDescricao(descricao);
        if (dono == null)
        {
            dono = Dono.builder().descricao(descricao).build();
            donoRepository.save(dono);            
        }
        return dono;
    }

    public Dono papai() {
        return dono(PAPAI);
    }

    public Dono mamae() {
        return dono(MAMAE);
    }
}
