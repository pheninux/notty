package com.model;

import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@Data
public class ResponseDto implements Serializable {

    List<NoteDto> noteDtos;

    List<File> files;

    public ResponseDto(List<NoteDto> noteDtos, List<File> files) {
        this.noteDtos = noteDtos;
        this.files = files;
    }

}
