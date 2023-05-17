package com.controller;

import com.Exeption.NoItemFoundException;
import com.model.Note;
import com.model.NoteDto;
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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping(value = "/note")
public class NoteController {

    public final static Logger LOGGER = LoggerFactory.getLogger(NoteController.class.getName());


    public final NoteService noteService;
    public final NoteRepository noteRepository;

    public NoteController(NoteService noteService, NoteRepository noteRepository) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
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
    public ResponseEntity<List<NoteDto>> findNoteByContext(@RequestBody String context) {

        LOGGER.info("[NoteController][notesByContext][RequestParam]: {}", context);

        try {

            List<Note> notes = noteRepository.findNoteByContext(context);
            if (notes.size() == 0 || notes.isEmpty()) {
                throw new NoItemFoundException();
            }
            return new ResponseEntity<>(Arrays.stream(new ModelMapper()
                    .map(noteRepository
                            .findNoteByContext(context)
                            .stream()
                            .sorted(Comparator
                                    .comparing(Note::getDate)), NoteDto[].class)).toList(), HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
