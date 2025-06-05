package com.ats.cvcreator.service;

import com.ats.cvcreator.model.Curriculo;
import com.ats.cvcreator.model.GeradorCurriculo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculoService {
    private final List<GeradorCurriculo> geradores;

    public CurriculoService(List<GeradorCurriculo> geradores) {
        this.geradores = geradores;
    }

    public void gerarCurriculo(Curriculo curriculo) {
        geradores.forEach(gerador -> gerador.gerar(curriculo));
    }
}