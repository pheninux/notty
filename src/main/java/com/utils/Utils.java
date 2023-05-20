package com.utils;

import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    public  boolean pdfPageContains(String find, PdfPage pdfPage) {
        String content = PdfTextExtractor.getTextFromPage(pdfPage);
        if ((content).isEmpty()) {
            return false;
        }
        return content.contains(find);
    }
}
