package com.ats.cvcreator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ExperienciaProfissional implements ItemCurriculo {
    private String cargo;
    private String empresa;
    private String periodo;
    private String descricao;

    @Override
    public String getTitulo() {
        return cargo;
    }
}
