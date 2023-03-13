package ada.tech.alunos.model;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alunos")
@NamedQueries({
    @NamedQuery(name = "Aluno.getPorPrefixo",
        query = "from Aluno where nome like ?1")
})
public class Aluno extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
}
