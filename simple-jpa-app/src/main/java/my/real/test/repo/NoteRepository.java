package my.real.test.repo;

import my.real.test.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Query("select n from Note n where n.email = :email")
    List<Note> findNotesByEmail(@Param("email") final String email);

    @Query("select n from Note n where n.name = :name")
    List<Note> findNotesByName(@Param("name") final String name);
}
