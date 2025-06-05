package com.ats.cvcreator.service;

import com.ats.cvcreator.model.Curriculo;
import com.ats.cvcreator.model.GeradorCurriculo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Component
public class GeradorCurriculoPdf extends GeradorCurriculoBase implements GeradorCurriculo {

    public GeradorCurriculoPdf(TemplateEngine templateEngine) {
        super(templateEngine);
    }

    public void gerar(Curriculo curriculo) {
        Context context = createContext(curriculo);
        String html = templateEngine.process("curriculo", context);
        String xhtml = Jsoup.parse(html).outputSettings(new Document.OutputSettings().syntax(Document.OutputSettings.Syntax.xml)).html();
        String outputPath = getOutputPath(curriculo.getDadosPessoais().getNome(), ".pdf");

        try (OutputStream outputStream = new FileOutputStream(outputPath)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(xhtml);
            renderer.layout();
            renderer.createPDF(outputStream);
            System.out.println("Currículo gerado em PDF: " + outputPath);
        } catch (Exception e) {
            System.out.println("Erro ao gerar PDF: " + e.getMessage());
        }
    }
}