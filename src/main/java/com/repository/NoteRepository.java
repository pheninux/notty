package com.repository;


import com.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

      public List<Note> findNoteByContext(String context);

      public List<Note> findNoteByContextAndDate(String context , LocalDate date);

      public List<Note> findNoteByDate(LocalDate date);

}
