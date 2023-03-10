package ada.tech.alunos.dto;

import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRequestDto {
    private String nome;
}
