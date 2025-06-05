package com.ats.cvcreator.service;

import com.ats.cvcreator.model.Curriculo;
import com.ats.cvcreator.model.GeradorCurriculo;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class GeradorCurriculoHtml extends GeradorCurriculoBase implements GeradorCurriculo {

    public GeradorCurriculoHtml(TemplateEngine templateEngine) {
        super(templateEngine);
    }

    public void gerar(Curriculo curriculo) {
        Context context = createContext(curriculo);
        String curriculoHtml = templateEngine.process("curriculo", context);
        String outputPath = getOutputPath(curriculo.getDadosPessoais().getNome(), ".html");

        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(curriculoHtml);
            System.out.println("Currículo gerado em HTML: " + outputPath);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o currículo: " + e.getMessage());
        }
    }
}
