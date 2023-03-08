package ada.tech.springclasses.curso.persistance;

import java.util.ArrayList;
import java.util.List;
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
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descrição;
    private int duracao;
    @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
    private List<Disciplina> disciplinas = new ArrayList<>();
}
