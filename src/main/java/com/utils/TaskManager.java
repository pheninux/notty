package com.utils;

import com.itextpdf.kernel.pdf.PdfDocument;

import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TaskManager implements Runnable{

    private PdfDocument pdfDocument ;
    private  int nbrPage ;

    private int indexFile ;

    private String val ;

    private CountDownLatch countDownLatch ;

    private List<File> resultFile ;

    private File[] files;

    public TaskManager(PdfDocument pdfDocument , int nbrPage , int indexFile , CountDownLatch countDownLatch , String val , List<File> resultFile , File[] files){

        this.countDownLatch = countDownLatch ;
        this.indexFile = indexFile ;
        this.pdfDocument = pdfDocument ;
        this.nbrPage = nbrPage ;
        this.val = val ;
        this.resultFile =  resultFile ;

    }

    @Override
    public void run() {

        synchronized (this) {

            for (int j = 1; j <= nbrPage; j++) {
                var page = pdfDocument.getPage(j);
                if (new Utils().pdfPageContains(val, page)) {
                    resultFile.add(files[indexFile]);
                    System.out.println("yes  contain it at page nbr => " + j);
                    break;
                }
            }
            countDownLatch.countDown();
        }


    }
}
