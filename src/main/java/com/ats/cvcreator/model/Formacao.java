package com.ats.cvcreator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class Formacao implements ItemCurriculo {
    private String instituicao;
    private String titulo;
    private String periodo;
}
