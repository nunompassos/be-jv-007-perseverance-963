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

    final private List<Aluno> alunos = new ArrayList();

    @GET
    public Response buscarAlunos() {
        return Response.ok(alunos.stream().map(AlunoResponseDto::from).collect(Collectors.toList())).build();
    }

    @POST
    public Response criarAluno (final AlunoRequestDto aluno) {
        alunos.add(new Aluno(alunos.size(), aluno.getNome()));
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
}
