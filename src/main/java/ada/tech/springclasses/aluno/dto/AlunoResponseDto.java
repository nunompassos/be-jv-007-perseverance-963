package ada.tech.springclasses.aluno.dto;

import ada.tech.springclasses.aluno.Aluno;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlunoResponseDto {
    private int id;
    private String nome;

    public static AlunoResponseDto from (Aluno aluno) {
        return new AlunoResponseDto(aluno.getId(), aluno.getNome());
    }
}
