package notty.service;

import notty.model.Note;

import java.util.List;

public interface NoteService {


    List<Note> findAllByContextLike(String context);


    List<Note> findAllByContextLikeOrDescriptionLike(String context, String description);
}



