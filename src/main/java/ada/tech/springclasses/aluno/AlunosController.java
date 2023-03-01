package ada.tech.springclasses.aluno;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/alunos"})
public class AlunosController {

    @PostMapping
    public Aluno criarAluno(
        String id,
        String nome
    ) {
        return new Aluno(id, nome);
    }
}
