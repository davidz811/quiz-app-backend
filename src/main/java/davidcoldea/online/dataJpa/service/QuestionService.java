package davidcoldea.online.dataJpa.service;

import davidcoldea.online.dataJpa.model.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);

    List<Question> getAllQuestions();

    Question findQuestionById(Long id);

    List<String> getAllSubjects();

    void deleteQuestion(Long id);

    Question updateQuestion(Long id, Question newQuestion);

    List<Question> getAllQuestionsForASubject(Integer numberOfQuestions, String subject);
}
