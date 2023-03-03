package ada.tech.springclasses.aluno.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ada.tech.springclasses.aluno.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    List<Aluno> findByNomeStartsWith(String prefixo);
}
