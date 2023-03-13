package ada.tech.alunos.model;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class AlunoRepositorio implements PanacheRepositoryBase<Aluno, Integer> {
    public List<Aluno> findComPrefixo(String prefixo) {
        return find("#Aluno.getPorPrefixo", prefixo + "%").list();
        //return find("nome like ?1", prefixo + "%").list();
    }
}
