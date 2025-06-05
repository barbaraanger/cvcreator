package com.ats.cvcreator.service;

import com.ats.cvcreator.model.Curriculo;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class GeradorCurriculoBase {
    protected final TemplateEngine templateEngine;

    public GeradorCurriculoBase(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    protected Context createContext(Curriculo curriculo) {
        List<String> links = curriculo.getDadosPessoais().getContato().getLinks();
        Map<String, String> linkTitleMap = formatLinks(links);
        Context context = new Context();
        context.setVariable("curriculo", curriculo);
        context.setVariable("habilidadesPorTipo", curriculo.getHabilidades());
        context.setVariable("linkTitleMap", linkTitleMap);
        return context;
    }

    protected Map<String, String>  formatLinks(List<String> links) {
        return links.stream()
                .collect(Collectors.toMap(
                        this::formatLinkTitle,
                        link -> link
                ));
    }

    protected String formatLinkTitle(String link) {
        if (link.contains("linkedin")) return "LinkedIn";
        if (link.contains("github")) return "GitHub";
        return link.replaceAll("^https?://", "");
    }

    protected String getOutputPath(String nome, String extension) {
        return Paths.get("").toAbsolutePath() + "/Resume - " + nome + extension;
    }
}
