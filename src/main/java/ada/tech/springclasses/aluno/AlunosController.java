package ada.tech.springclasses.aluno;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/alunos"})
public class AlunosController {

    @PostMapping(path = {"/aluno"})
    public Aluno criarAluno(
        String id,
        String nome
    ) {
        final Aluno aluno = new Aluno(id, nome);

        return aluno;
    }
}
