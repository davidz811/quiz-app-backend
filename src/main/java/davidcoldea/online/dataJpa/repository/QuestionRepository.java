package davidcoldea.online.dataJpa.repository;

import davidcoldea.online.dataJpa.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question , Long> {
    @Query("SELECT DISTINCT q.subject from Question q")
    List<String> findAllSubjects();

    List<Question> findAllBySubject(String subject);
}
