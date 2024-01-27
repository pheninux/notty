package notty.controller;


import notty.model.*;
import notty.repository.NoteRepository;
import notty.service.NoteFilesService;
import notty.service.NoteService;
import notty.utils.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(value = "/note")
public class NoteController {

    public final static Logger LOGGER = LoggerFactory.getLogger(NoteController.class.getName());

    public final Utils utils;
    public final NoteService noteService;
    public final NoteRepository noteRepository;
    public final ModelMapper modelMapper;

    public final NoteFilesService noteFilesService;

    public NoteController(NoteService noteService, NoteRepository noteRepository, ModelMapper modelMapper, Utils utils, NoteFilesService noteFilesService) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
        this.utils = utils;
        this.noteFilesService = noteFilesService;
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

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> findNoteByContext(@RequestBody SearchDto searchDto) {

        LOGGER.info("[NoteController][notesByContext][RequestParam]: {}", searchDto.toString());
        List<Note> notes ;
        ResponseDto responseDto = new ResponseDto();
        switch (utils.manageSearchOption(searchDto)) {
            case "CDFD", "CDF":
                return responseDto(searchDto, responseDto);
            case "CD":
                notes = noteService.findAllByContextLikeOrDescriptionLike(searchDto.getContext(), searchDto.getDescription());
                responseDto.setNoteDtos(Arrays.stream(modelMapper.map(notes, NoteDto[].class)).toList());
                if (notes.isEmpty()) {
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            case "FD":
                break;
            case "C":
                break;
            case "D":
                break;
            case "F":
                break;
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

        /*** in context ***/


        /*** in description ***/


        /*** in description && context ***/


        /*** just in files with light search ***/


        /*** files in deep search ***/


        /*** in context && files with light search ***/


        /*** in context && files with deep search ***/


    }

    private ResponseEntity<ResponseDto> responseDto(SearchDto searchDto, ResponseDto responseDto) {
        List<Note> notes;
        List<FileDto> fileDtos;
        notes = noteService.findAllByContextLike(searchDto.getContext());
        fileDtos = noteFilesService.findFiles(searchDto, "C:\\Users\\ahaddad\\Downloads\\test-file");
        responseDto.setNoteDtos(Arrays.stream(modelMapper.map(notes, NoteDto[].class)).toList());
        responseDto.setFiles(fileDtos);
        if (fileDtos.isEmpty() && notes.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
