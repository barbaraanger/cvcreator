package com.ats.cvcreator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DadosPessoais {
    private String nome;
    private String tituloCargo;
    private Endereco endereco;
    private Contato contato;
}
