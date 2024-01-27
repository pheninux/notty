package notty.service;

import notty.model.Note;
import notty.repository.NoteRepository;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Note> findAllByContextLikeOrDescriptionLike(String context, String description) {
        return noteRepository.findAllByContextLikeOrDescriptionLike(context, description);
    }
}
