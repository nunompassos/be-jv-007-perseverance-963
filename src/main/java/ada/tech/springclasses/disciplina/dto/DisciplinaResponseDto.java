package ada.tech.springclasses.disciplina.dto;

import ada.tech.springclasses.curso.dto.CursoResponseDto;
import ada.tech.springclasses.curso.persistance.Curso;
import ada.tech.springclasses.disciplina.persistance.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DisciplinaResponseDto {
    private int id;
    private String nome;

    public static DisciplinaResponseDto from (Disciplina disciplina) {
        return new DisciplinaResponseDto(disciplina.getId(), disciplina.getNome());
    }
}
