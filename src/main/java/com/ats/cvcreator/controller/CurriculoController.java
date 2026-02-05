package com.ats.cvcreator.controller;

import com.ats.cvcreator.DTO.HabilidadeDTO;
import com.ats.cvcreator.enums.TipoHabilidade;
import com.ats.cvcreator.model.Contato;
import com.ats.cvcreator.model.Curso;
import com.ats.cvcreator.model.Curriculo;
import com.ats.cvcreator.model.DadosPessoais;
import com.ats.cvcreator.model.Endereco;
import com.ats.cvcreator.model.ExperienciaProfissional;
import com.ats.cvcreator.model.FormacaoAcademica;
import com.ats.cvcreator.service.CurriculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/curriculo")
public class CurriculoController {
    private final CurriculoService curriculoService;
    private final Curriculo curriculo = new Curriculo();

    public CurriculoController(CurriculoService curriculoService) {
        this.curriculoService = curriculoService;
        curriculo.setExperiencias(new ArrayList<>());
        curriculo.setFormacoes(new ArrayList<>());
        curriculo.setCursos(new ArrayList<>());
        curriculo.setHabilidades(new ArrayList<>());
    }

    @PostMapping("/dados-pessoais")
    public ResponseEntity<String> adicionarDadosPessoais(@RequestBody DadosPessoaisRequest request) {
        Endereco endereco = new Endereco();
        endereco.setCidade(request.cidade());
        endereco.setEstado(request.estado());
        endereco.setPais(request.pais());

        Contato contato = new Contato();
        contato.setEmail(request.email());
        contato.setTelefone(request.telefone());
        contato.setLinks(request.links());

        DadosPessoais dados = new DadosPessoais();
        dados.setNome(request.nome());
        dados.setTituloCargo(request.tituloCargo());
        dados.setEndereco(endereco);
        dados.setContato(contato);

        curriculo.setDadosPessoais(dados);
        return ResponseEntity.ok("Dados pessoais adicionados.");
    }

    @PostMapping("/experiencias")
    public ResponseEntity<String> adicionarExperiencia(@RequestBody ExperienciaRequest request) {
        ExperienciaProfissional exp = new ExperienciaProfissional();
        exp.setCargo(request.cargo());
        exp.setEmpresa(request.empresa());
        exp.setPeriodo(request.periodo());
        exp.setDescricao(request.descricao());
        curriculo.getExperiencias().add(exp);
        return ResponseEntity.ok("Experiência adicionada.");
    }

    @PostMapping("/formacoes")
    public ResponseEntity<String> adicionarFormacao(@RequestBody FormacaoRequest request) {
        FormacaoAcademica formacao = new FormacaoAcademica();
        formacao.setTitulo(request.curso());
        formacao.setInstituicao(request.instituicao());
        formacao.setPeriodo(request.periodo());
        formacao.setNivel(request.nivel());
        curriculo.getFormacoes().add(formacao);
        return ResponseEntity.ok("Formação acadêmica adicionada.");
    }

    @PostMapping("/cursos")
    public ResponseEntity<String> adicionarCurso(@RequestBody CursoRequest request) {
        Curso curso = new Curso();
        curso.setTitulo(request.titulo());
        curso.setInstituicao(request.instituicao());
        curso.setPeriodo(request.periodo());
        curso.setCargaHoraria(request.cargaHoraria());
        curriculo.getCursos().add(curso);
        return ResponseEntity.ok("Curso adicionado.");
    }

    @PostMapping("/habilidades")
    public ResponseEntity<String> adicionarHabilidade(@RequestBody HabilidadeRequest request) {
        TipoHabilidade tipoHabilidade = TipoHabilidade.valueOf(request.tipo().toUpperCase());
        curriculo.getHabilidades().add(HabilidadeDTO.builder()
                .tipoHabilidade(tipoHabilidade)
                .habilidades(request.habilidades())
                .build());
        return ResponseEntity.ok("Habilidade adicionada.");
    }

    @PostMapping("/resumo")
    public ResponseEntity<String> adicionarResumo(@RequestBody ResumoRequest request) {
        curriculo.setResumo(request.resumo());
        return ResponseEntity.ok("Resumo adicionado.");
    }

    @PostMapping("/gerar")
    public ResponseEntity<String> gerarCurriculo() {
        curriculoService.gerarCurriculo(curriculo);
        return ResponseEntity.ok("Currículo gerado.");
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Curriculo> obterCurriculo() {
        return ResponseEntity.ok(curriculo);
    }

    public record DadosPessoaisRequest(
            String nome,
            String tituloCargo,
            String cidade,
            String estado,
            String pais,
            String email,
            String telefone,
            List<String> links
    ) {
    }

    public record ExperienciaRequest(String cargo, String empresa, String periodo, String descricao) {
    }

    public record FormacaoRequest(String curso, String instituicao, String periodo, String nivel) {
    }

    public record CursoRequest(String titulo, String instituicao, String periodo, int cargaHoraria) {
    }

    public record HabilidadeRequest(String tipo, List<String> habilidades) {
    }

    public record ResumoRequest(String resumo) {
    }
}
