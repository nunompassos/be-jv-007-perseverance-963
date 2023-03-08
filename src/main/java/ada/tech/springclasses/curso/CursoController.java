package ada.tech.springclasses.curso;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ada.tech.springclasses.curso.dto.CursoRequestDto;
import ada.tech.springclasses.curso.dto.CursoResponseDto;
import ada.tech.springclasses.curso.persistance.CursoService;
import ada.tech.springclasses.disciplina.dto.DisciplinaRequestDto;
import ada.tech.springclasses.disciplina.persistance.Disciplina;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    final private CursoService servico;

    public CursoController(CursoService servico) {
        this.servico = servico;
    }

    @PostMapping
    public CursoResponseDto criarCurso(
        @RequestBody final CursoRequestDto request
    ) {
        return servico.gravarCurso(request);
    }

    @GetMapping
    public List<CursoResponseDto> listarCurso(
        @RequestParam final Optional<String> prefixo
    ) {
        return servico.listarCurso(prefixo);
    }

    @GetMapping("/{id}")
    public CursoResponseDto buscarCurso(
        @PathVariable("id") final int id
    ) {
        return servico.buscarCurso(id);
    }

    @PutMapping("/{id}")
    public CursoResponseDto atualizarCurso(
        @PathVariable final int id,
        @RequestBody final CursoRequestDto request
    ) {
        return servico.atualizarCurso(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void apagarCurso(
        @PathVariable final int id
    ) {
        servico.apagarCurso(id);
    }

    @PatchMapping("/{id}/disciplina")
    public CursoResponseDto adicionarDisciplina(
        @PathVariable final int id,
        @RequestBody final DisciplinaRequestDto request
        ) {
        return servico.adicionarDisciplina(id, request);
    }
}
