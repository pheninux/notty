package com.controller;

import com.Exeption.NoItemFoundException;
import com.model.Note;
import com.model.NoteDto;
import com.model.ResponseDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import com.repository.NoteRepository;
import com.service.NoteService;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping(value = "/note")
public class NoteController {

    public final static Logger LOGGER = LoggerFactory.getLogger(NoteController.class.getName());


    public final NoteService noteService;
    public final NoteRepository noteRepository;
    public final ModelMapper modelMapper;

    public NoteController(NoteService noteService, NoteRepository noteRepository, ModelMapper modelMapper) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> save(@RequestBody Note note) {

        LOGGER.info("[NoteController][save][RequestBody]: {}", note);

        try {
            return new ResponseEntity<>(new ModelMapper().map(noteRepository.save(note), NoteDto.class), HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/byContext", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> findNoteByContext(@RequestBody String val) {

        LOGGER.info("[NoteController][notesByContext][RequestParam]: {}", val);

        List<Note> notes;
        List<NoteDto> noteDtos = null;
        ResponseDto responseDto = null;
        try {
            notes = noteRepository.findAllByContextLikeOrDescriptionLike("%" + val + "%", "%" + val + "%");
            if (notes.size() == 0 || notes.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            noteDtos = Arrays.stream(modelMapper.map(notes, NoteDto[].class)).toList();
            List<File> files = noteService.findFileByContext(val);
            responseDto = new ResponseDto(noteDtos, files);
        } catch (Exception e) {
            LOGGER.error("[NoteController][notesByContext][ERROR]: {}", e.getMessage());
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
