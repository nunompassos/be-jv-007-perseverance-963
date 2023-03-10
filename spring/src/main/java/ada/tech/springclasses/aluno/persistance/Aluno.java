package ada.tech.springclasses.aluno.persistance;

import javax.persistence.*;

import ada.tech.springclasses.disciplina.persistance.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @ManyToOne
    private Disciplina disciplina;
}
