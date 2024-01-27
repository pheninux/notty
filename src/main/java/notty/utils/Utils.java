package notty.utils;

import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import notty.model.SearchDto;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    public boolean pdfPageContains(String find, PdfPage pdfPage) {
        String content = PdfTextExtractor.getTextFromPage(pdfPage);
        if ((content).isEmpty()) {
            return false;
        }
        return content.contains(find);
    }


    public String manageSearchOption(SearchDto searchDto) {

        if (!searchDto.getContext().isEmpty() && !searchDto.getDescription().isEmpty() && searchDto.isInFile() && searchDto.isDeepSearch()) {
            return "CDFD";

        } else if (!searchDto.getContext().isEmpty() && !searchDto.getDescription().isEmpty() && searchDto.isInFile()) {
            return "CDF";
        } else if (!searchDto.getContext().isEmpty() && !searchDto.getDescription().isEmpty()) {
            return "CD";
        } else if (!searchDto.getContext().isEmpty()) {
            return "C";
        } else if (!searchDto.getDescription().isEmpty()) {
            return "D";
        } else if (searchDto.isInFile() && searchDto.isDeepSearch()) {
            return "FD";
        } else if (searchDto.isInFile()) {
            return "F";
        }
        return null;
    }
}
