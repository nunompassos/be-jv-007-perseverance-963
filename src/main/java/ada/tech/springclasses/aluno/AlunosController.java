package ada.tech.springclasses.aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ada.tech.springclasses.aluno.dto.AlunoRequestDto;
import ada.tech.springclasses.aluno.utils.AlunoId;

@RestController
@RequestMapping(path = {"/alunos"})
public class AlunosController {

    private final List<Aluno> alunosDB = new ArrayList<>();

    @PostMapping
    public Aluno criarAluno(
        @RequestBody final AlunoRequestDto request
        ) {
        final Aluno aluno = new Aluno(AlunoId.incrementId(), request.getNome());
        alunosDB.add(aluno);
        return aluno;
    }

    @GetMapping
    public List<Aluno> listarAlunos(
        @RequestParam final Optional<String> prefixo
        ) {
        return prefixo.map(s -> alunosDB
            .stream()
            .filter(it -> it.getNome().startsWith(s))
            .collect(Collectors.toList()))
            .orElse(alunosDB);
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

    @PutMapping("/{id}")
    public Aluno atualizaraluno(
        @PathVariable final int id,
        @RequestBody final AlunoRequestDto request
    ) {
        final Aluno aluno = alunosDB
            .stream()
            .filter(it -> it.getId() == id)
            .findFirst()
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado")
            );
        aluno.setNome(request.getNome());
        return aluno;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void apagarAluno(
        @PathVariable final int id
    ) {
        final Aluno aluno = alunosDB
            .stream()
            .filter(it -> it.getId() == id)
            .findFirst()
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado")
            );
        alunosDB.remove(aluno);
    }
}
