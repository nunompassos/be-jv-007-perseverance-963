package ada.tech.alunos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Status;

import ada.tech.alunos.dto.AlunoRequestDto;
import ada.tech.alunos.dto.AlunoResponseDto;
import ada.tech.alunos.model.Aluno;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosController {

    final private List<Aluno> alunos = new ArrayList<>();

    public AlunosController() {
        alunos.add(new Aluno(0, "Ana"));
        alunos.add(new Aluno(1, "Mario"));
        alunos.add(new Aluno(2, "André"));
    }

    @GET
    public List<AlunoResponseDto> buscarAlunos(@QueryParam("prefix") Optional<String> prefix) {
        return alunos
            .stream()
            .filter(it -> prefix.map(s -> it.getNome().startsWith(s)).orElse(true))
            .map(AlunoResponseDto::from)
            .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public AlunoResponseDto encontrarAluno(@PathParam("id") final int id) {
        return alunos
            .stream()
            .filter(it -> it.getId() == id)
            .map(AlunoResponseDto::from)
            .findFirst()
            .orElseThrow(NotFoundException::new);
    }

    @POST
    public AlunoResponseDto criarAluno (final AlunoRequestDto request) {
        final Aluno aluno = new Aluno(
            alunos.stream().mapToInt(Aluno::getId).max().orElse(0),
            request.getNome()
        );
        alunos.add(aluno);
        return AlunoResponseDto.from(aluno);
    }

    @DELETE
    @Path("/{id}")
    public void apagarAluno(@PathParam("id") int id) {
        final Aluno aluno = alunos.stream().filter(it -> it.getId() == id).findFirst().orElseThrow(NotFoundException::new);
        alunos.remove(aluno);
    }

    @PUT
    @Path("/{id}")
    public AlunoResponseDto atualizarAluno(@PathParam("id") int id, final AlunoRequestDto request) {
        final Aluno aluno = alunos.stream().filter(it -> it.getId() == id).findFirst().orElseThrow(NotFoundException::new);
        aluno.setNome(request.getNome());
        return AlunoResponseDto.from(aluno);
    }
}
