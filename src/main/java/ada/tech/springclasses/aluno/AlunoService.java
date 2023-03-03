package ada.tech.springclasses.aluno;

import java.util.List;
import java.util.Optional;

import ada.tech.springclasses.aluno.dto.AlunoRequestDto;
import ada.tech.springclasses.aluno.dto.AlunoResponseDto;

public interface AlunoService {

    AlunoResponseDto gravarAluno(AlunoRequestDto aluno);
    List<AlunoResponseDto> listarAlunos(Optional<String> prefixo);
    AlunoResponseDto buscarAluno(int id);
    void apagarAluno(int id);
    AlunoResponseDto atualizarAluno(int id, AlunoRequestDto aluno);
}
