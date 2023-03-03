package ada.tech.springclasses.aluno;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ada.tech.springclasses.aluno.dto.AlunoRequestDto;
import ada.tech.springclasses.aluno.persistance.AlunoRepository;

@RestController
@RequestMapping(path = {"/alunos"})
public class AlunosController {

    final private AlunoRepository repositorio;

    public AlunosController(AlunoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    public Aluno criarAluno(
        @RequestBody final AlunoRequestDto request
        ) {
        final Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        repositorio.save(aluno);
        return aluno;
    }

    @GetMapping
    public List<Aluno> listarAlunos(
        @RequestParam final Optional<String> prefixo
        ) {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Aluno buscarAluno(
        @PathVariable("id") final int id
    ) {
        return repositorio
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }

    @PutMapping("/{id}")
    public Aluno atualizaraluno(
        @PathVariable final int id,
        @RequestBody final AlunoRequestDto request
    ) {
        final Aluno aluno = repositorio
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        aluno.setNome(request.getNome());
        return repositorio.save(aluno);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void apagarAluno(
        @PathVariable final int id
    ) {
        repositorio.deleteById(id);
    }
}
