package com.ats.cvcreator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TipoHabilidade {
    TECNOLOGIA("Linguagens e Tecnologias"),
    METODOLOGIA("Desenvolvimento e Metodologias"),
    IDIOMA("Idiomas");

    private final String descricao;
}
