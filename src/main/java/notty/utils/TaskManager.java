package notty.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import notty.model.FileDto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TaskManager implements Runnable {

    private CountDownLatch countDownLatch;
    private List<FileDto> result;
    private File file;
    private boolean deep;
    private String valueToFind;


    public TaskManager(List<FileDto> result, File file, Boolean deep, String valueToFind, CountDownLatch countDownLatch) {
        this.result = result;
        this.file = file;
        this.deep = deep;
        this.valueToFind = valueToFind;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        synchronized (this) {
            try {
                PdfReader pdfReader = new PdfReader(file);
                PdfDocument pdfDocument = new PdfDocument(pdfReader);
                var nbrPage = pdfDocument.getNumberOfPages();
                List<Integer> pageNumbers = new ArrayList<>();
                FileDto fileDto = new FileDto();

                for (int j = 1; j <= nbrPage; j++) {
                    var page = pdfDocument.getPage(j);
                    if (new Utils().pdfPageContains(valueToFind, page)) {
                        fileDto.setFile(file);
                        pageNumbers.add(j);
                        fileDto.setPagesNumber(pageNumbers);
                        result.add(fileDto);
                        System.out.println("yes  contain it at page nbr => " + j);
                        if (!deep) {
                            break;
                        }

                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            countDownLatch.countDown();
        }
    }
}

