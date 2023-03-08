package ada.tech.springclasses.disciplina.persistance;

import java.util.List;
import javax.persistence.*;

import ada.tech.springclasses.aluno.persistance.Aluno;
import ada.tech.springclasses.curso.persistance.Curso;
import ada.tech.springclasses.professor.persistance.Professor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    private Curso curso;
    @ManyToMany
    private List<Professor> professores;
    @OneToMany(mappedBy = "disciplina")
    private List<Aluno> alunos;
}
