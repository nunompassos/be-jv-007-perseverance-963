package ada.tech.alunos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response buscarAlunos(@QueryParam("prefix") String prefix) {
        return Response.ok(
            prefix == null ?
                alunos.stream().map(AlunoResponseDto::from).collect(Collectors.toList()) :
                alunos.stream().filter(it -> it.getNome().startsWith(prefix)).map(AlunoResponseDto::from).collect(
                Collectors.toList())
        ).build();
    }

    @POST
    public Response criarAluno (final AlunoRequestDto aluno) {
        final int id = alunos.stream().mapToInt(Aluno::getId).max().orElse(0);
        alunos.add(new Aluno(id, aluno.getNome()));
        return Response
            .status(Response.Status.CREATED)
            .entity(alunos.get(alunos.size()-1))
            .build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarAluno(@PathParam("id") int id) {
        final Optional<Aluno> aluno = alunos.stream().filter(it -> it.getId() == id).findFirst();
        if (aluno.isPresent()) {
            alunos.remove(aluno.get());
            return Response.ok(aluno.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarAluno(@PathParam("id") int id, final AlunoRequestDto request) {
        final Optional<Aluno> aluno = alunos.stream().filter(it -> it.getId() == id).findFirst();
        if (aluno.isPresent()) {
            aluno.get().setNome(request.getNome());
            return Response.ok(AlunoResponseDto.from(aluno.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
