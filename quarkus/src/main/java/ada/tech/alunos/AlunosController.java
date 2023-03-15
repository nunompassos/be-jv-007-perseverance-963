package ada.tech.alunos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import ada.tech.alunos.dto.AlunoRequestDto;
import ada.tech.alunos.dto.AlunoResponseDto;
import ada.tech.alunos.model.Aluno;
import ada.tech.alunos.model.AlunoRepositorio;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosController {

    final private AlunoRepositorio repositorio;

    public AlunosController(AlunoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GET
    public List<AlunoResponseDto> buscarAlunos(@QueryParam("prefix") Optional<String> prefix) {
        return prefix
            .map(s -> repositorio
                .findComPrefixo(s)
                .stream()
                .map(AlunoResponseDto::from)
                .collect(Collectors.toList()))
            .orElseGet(() -> Aluno
                .findAll()
                .list()
                .stream()
                .map(it -> (Aluno) it)
                .map(AlunoResponseDto::from)
                .collect(Collectors.toList()));
    }

    @GET
    @Path("/{id}")
    public AlunoResponseDto encontrarAluno(@PathParam("id") final int id) {
        return AlunoResponseDto.from((Aluno) Aluno
            .findByIdOptional(id)
            .orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    public AlunoResponseDto criarAluno (final AlunoRequestDto request) {
        final Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.persist();
        return AlunoResponseDto.from(aluno);
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public void apagarAluno(@PathParam("id") int id) {
        System.out.println(repositorio.deleteById(id));
        /*Aluno
            .findByIdOptional(id)
            .orElseThrow(NotFoundException::new).delete();*/
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public AlunoResponseDto atualizarAluno(@PathParam("id") int id, final AlunoRequestDto request) {
        final Aluno aluno = (Aluno) Aluno
            .findByIdOptional(id)
            .orElseThrow(NotFoundException::new);
        aluno.setNome(request.getNome());
        aluno.persist();
        return AlunoResponseDto.from(aluno);
    }
}
