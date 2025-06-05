package com.ats.cvcreator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TipoHabilidade {
    TECNOLOGIA("Languages & Technologies"),
    METODOLOGIA("Development & Methodologies"),
    IDIOMA("Languages");

    private final String descricao;
}
