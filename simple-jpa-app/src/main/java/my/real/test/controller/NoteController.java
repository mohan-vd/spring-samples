package my.real.test.controller;

import my.real.test.domain.Note;
import my.real.test.repo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/notes")
@Transactional
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(path="/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Iterable<Note> getAllNotes() {
        // This returns a JSON or XML with the users
        final List<Note> allNotes = noteRepository.findAll();
        return allNotes;
    }
}
