package com.ats.cvcreator.DTO;

import com.ats.cvcreator.enums.TipoHabilidade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class HabilidadeDTO {
    private TipoHabilidade tipoHabilidade;
    private List<String> habilidades;
}
