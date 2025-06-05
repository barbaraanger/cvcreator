package com.ats.cvcreator.command;

import com.ats.cvcreator.DTO.HabilidadeDTO;
import com.ats.cvcreator.enums.TipoHabilidade;
import com.ats.cvcreator.model.*;
import com.ats.cvcreator.service.CurriculoService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class CurriculoCommands {
    private final CurriculoService curriculoService;

    private final Curriculo curriculo = new Curriculo();

    public CurriculoCommands(CurriculoService curriculoService) {
        this.curriculoService = curriculoService;

        curriculo.setExperiencias(new ArrayList<>());
        curriculo.setFormacoes(new ArrayList<>());
        curriculo.setCursos(new ArrayList<>());
        curriculo.setHabilidades(new ArrayList<>());
    }

    @ShellMethod(key = "dados-pessoais", value = "Adiciona dados pessoais")
    public void adicionarDadosPessoais(
            @ShellOption(value = "--nome", help = "Nome completo") String nome,
            @ShellOption(value = "--titulo", help = "Título do cargo") String tituloCargo,
            @ShellOption(value = "--cidade", help = "Cidade") String cidade,
            @ShellOption(value = "--estado", help = "Estado") String estado,
            @ShellOption(value = "--pais", help = "País") String pais,
            @ShellOption(value = "--email", help = "Email") String email,
            @ShellOption(value = "--telefone", help = "Telefone") String telefone,
            @ShellOption(value = "--links", help = "Links") List<String> links
    ) {
        // Cria o endereço
        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setPais(pais);

        // Cria o contato
        Contato contato = new Contato();
        contato.setEmail(email);
        contato.setTelefone(telefone);
        contato.setLinks(links);

        // Cria os dados pessoais
        DadosPessoais dados = new DadosPessoais();
        dados.setNome(nome);
        dados.setTituloCargo(tituloCargo);
        dados.setEndereco(endereco);
        dados.setContato(contato);

        // Define os dados pessoais no currículo
        curriculo.setDadosPessoais(dados);
        System.out.println("Dados pessoais adicionados.");
    }

    @ShellMethod(key = "experiencia", value = "Adiciona uma experiência profissional")
    public void adicionarExperiencia(
            @ShellOption(value = "--cargo", help = "Cargo") String cargo,
            @ShellOption(value = "--empresa", help = "Empresa") String empresa,
            @ShellOption(value = "--periodo", help = "Período") String periodo,
            @ShellOption(value = "--descricao", help = "Descrição") String descricao
    ) {
        ExperienciaProfissional exp = new ExperienciaProfissional();
        exp.setCargo(cargo);
        exp.setEmpresa(empresa);
        exp.setPeriodo(periodo);
        exp.setDescricao(descricao);
        curriculo.getExperiencias().add(exp);
        System.out.println("Experiência adicionada.");
    }

    @ShellMethod(key = "formacao", value = "Adiciona uma formação acadêmica")
    public void adicionarFormacao(
            @ShellOption(value = "--curso", help = "Curso") String curso,
            @ShellOption(value = "--instituicao", help = "Instituição") String instituicao,
            @ShellOption(value = "--periodo", help = "Período") String periodo,
            @ShellOption(value = "--nivel", help = "Nível (ex: Graduação, Mestrado)") String nivel
    ) {
        FormacaoAcademica formacao = new FormacaoAcademica();
        formacao.setTitulo(curso);
        formacao.setInstituicao(instituicao);
        formacao.setPeriodo(periodo);
        formacao.setNivel(nivel);
        curriculo.getFormacoes().add(formacao);
        System.out.println("Formação acadêmica adicionada.");
    }

    @ShellMethod(key = "curso", value = "Adiciona um curso")
    public void adicionarCurso(
            @ShellOption(value = "--titulo", help = "Título do curso") String titulo,
            @ShellOption(value = "--instituicao", help = "Instituição") String instituicao,
            @ShellOption(value = "--periodo", help = "Período") String periodo,
            @ShellOption(value = "--carga-horaria", help = "Carga horária") int cargaHoraria
    ) {
        Curso curso = new Curso();
        curso.setTitulo(titulo);
        curso.setInstituicao(instituicao);
        curso.setPeriodo(periodo);
        curso.setCargaHoraria(cargaHoraria);
        curriculo.getCursos().add(curso);
        System.out.println("Curso adicionado.");
    }

    @ShellMethod(key = "habilidade", value = "Adiciona uma habilidade")
    public void adicionarHabilidade(
            @ShellOption(value = "--tipo", help = "Tipo da habilidade (tecnologia, metodologia, idioma)") String tipo,
            @ShellOption(value = "--habilidades", help = "Lista de habilidades separadas por vírgula") List<String> habilidades
    ) {
        TipoHabilidade tipoHabilidade = TipoHabilidade.valueOf(tipo.toUpperCase());
        curriculo.getHabilidades().add(HabilidadeDTO.builder()
                .tipoHabilidade(tipoHabilidade)
                .habilidades(habilidades)
                .build());
        System.out.println("Habilidade adicionada.");
    }

    @ShellMethod(key = "resumo", value = "Adiciona um resumo")
    public void adicionarResumo(
            @ShellOption(value = "--resumo", help = "Resumo") String resumo
    ) {
        curriculo.setResumo(resumo);
        System.out.println("Resumo adicionado.");
    }

    @ShellMethod(key = "gerar", value = "Gera o currículo")
    public void gerarCurriculo() {
        curriculoService.gerarCurriculo(curriculo);
        System.out.println("Currículo gerado.");
    }
}