package com.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.model.Note;
import com.utils.TaskManager;
import com.utils.Utils;
import org.aspectj.weaver.ast.Not;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public interface NoteService {


    default public List<File> findFileByContext(String val) throws Exception {

        PdfReader pdfReader = null;
        PdfDocument pdfDocument;
        int nbrPage = 0;
        File dir = new File("C:\\files\\");
        List<File> filesResult = new ArrayList<>();
        /*** get all file directory ***/

        File[] files = dir.listFiles();
        CountDownLatch countDownLatch = new CountDownLatch(files.length);
        if (files.length > 1) {
            for (int i = 0; i < files.length; i++) {
                pdfReader = new PdfReader(files[i]);
                pdfDocument = new PdfDocument(pdfReader);
                nbrPage = pdfDocument.getNumberOfPages();
                Thread thread = new Thread(new TaskManager(pdfDocument, nbrPage, i, countDownLatch, val, filesResult, files));
                thread.start();
            }
        }

        if (filesResult.isEmpty() || filesResult.size() == 0) {
            throw new Exception("NO CONTENT FOUND");
        }
        return filesResult;
    }


}



