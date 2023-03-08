package ada.tech.springclasses.curso.dto;

import java.util.List;
import java.util.stream.Collectors;

import ada.tech.springclasses.aluno.dto.AlunoResponseDto;
import ada.tech.springclasses.aluno.persistance.Aluno;
import ada.tech.springclasses.curso.persistance.Curso;
import ada.tech.springclasses.disciplina.dto.DisciplinaResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CursoResponseDto {
    private int id;
    private String nome;
    private String descricao;
    private int duracao;
    private List<DisciplinaResponseDto> disciplinas;

    public static CursoResponseDto from (Curso curso) {
        return new CursoResponseDto(
            curso.getId(),
            curso.getNome(),
            curso.getDescrição(),
            curso.getDuracao(),
            curso.getDisciplinas().stream().map(DisciplinaResponseDto::from).collect(Collectors.toList()));
    }
}
