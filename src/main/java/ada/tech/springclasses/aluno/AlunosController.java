package ada.tech.springclasses.aluno;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ada.tech.springclasses.aluno.dto.AlunoRequestDto;
import ada.tech.springclasses.aluno.utils.AlunoId;

@RestController
@RequestMapping(path = {"/alunos"})
public class AlunosController {

    private List<Aluno> alunosDB = new ArrayList<>();

    @PostMapping
    public Aluno criarAluno(
        @RequestBody final AlunoRequestDto request
        ) {
        final Aluno aluno = new Aluno(AlunoId.incrementId(), request.getNome());
        alunosDB.add(aluno);
        return aluno;
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunosDB;
    }

    @GetMapping("/{id}")
    public Aluno buscarAluno(
        @PathVariable("id") final int id
    ) {
        return alunosDB
            .stream()
            .filter(it -> it.getId() == id)
            .findFirst()
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado")
            );
    }
}
