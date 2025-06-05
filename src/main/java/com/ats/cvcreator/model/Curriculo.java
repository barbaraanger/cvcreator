package com.ats.cvcreator.model;

import com.ats.cvcreator.DTO.HabilidadeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curriculo {
    private DadosPessoais dadosPessoais;
    private String resumo;
    private List<ExperienciaProfissional> experiencias;
    private List<FormacaoAcademica> formacoes;
    private List<HabilidadeDTO> habilidades;
    private List<Curso> cursos;
}