package com.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.model.FileDto;
import com.model.Note;
import com.model.ResponseDto;
import com.utils.TaskManager;
import com.utils.Utils;
import org.aspectj.weaver.ast.Not;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public interface NoteService {


    public List<Note> findAllByContextLike(String context);


}



