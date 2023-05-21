package com.service;

import com.model.FileDto;
import com.model.SearchDto;
import com.utils.TaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public interface NoteFilesService {

    final static Logger LOGGER = LoggerFactory.getLogger(NoteFilesService.class.getName());

    default List<FileDto> findFilesByDeepSearch(SearchDto searchDto, String dir) {
        List<FileDto> result = new ArrayList<>();
        try {
            File files = new File(dir);
            List<File> filtredFiles = Arrays.stream(files.listFiles())
                    .filter(f -> f.isFile() && (f.getName().contains(".PDF") || f.getName().contains(".pdf"))).collect(Collectors.toList());
            CountDownLatch countDownLatch = new CountDownLatch(filtredFiles.size());

            for (int i = 0; i < filtredFiles.size(); i++) {
                Thread thread = new Thread(new TaskManager(result, filtredFiles.get(i), searchDto.isDeepSearch(), searchDto.getContext(), countDownLatch));
                thread.start();
            }

            countDownLatch.await();

        } catch (Exception e) {
            LOGGER.error("[NoteFilesService][findFilesByDeepSearch] : {}", e.getMessage());
        }
        return result;
    }


}
