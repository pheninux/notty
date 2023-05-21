package com.service;

import com.model.FileDto;
import com.model.Note;
import com.model.NoteDto;
import com.model.ResponseDto;
import com.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @Override
    public List<Note> findAllByContextLike(String context) {
        return noteRepository.findAllByContextLike(context);
    }
}
