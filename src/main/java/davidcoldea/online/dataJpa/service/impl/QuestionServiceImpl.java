package davidcoldea.online.dataJpa.service.impl;

import davidcoldea.online.dataJpa.model.Question;
import davidcoldea.online.dataJpa.repository.QuestionRepository;
import davidcoldea.online.dataJpa.service.QuestionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question findQuestionById(Long id) throws NoSuchElementException {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            Question foundQuestions = question.get();
            return foundQuestions;
        }   else {
            throw new NoSuchElementException("Questions has been not found");
        }
    }

    @Override
    public List<String> getAllSubjects() {
        return questionRepository.findAllSubjects();
    }

    @Override
    public void deleteQuestion(Long id) {
        Optional<Question> foundQuestion = questionRepository.findById(id);
        if (foundQuestion.isPresent()) {
            questionRepository.deleteById(id);
        }
    }

    @Override
    public Question updateQuestion(Long id, Question newQuestion) throws NoSuchElementException {
        Optional<Question> foundQuestion = questionRepository.findById(id);
        if (foundQuestion.isPresent()) {
            Question questionToUpdate = foundQuestion.get();
            questionToUpdate.setQuestionText(newQuestion.getQuestionText());
            questionToUpdate.setChoices(newQuestion.getChoices());
            questionToUpdate.setCorrectAnswers(newQuestion.getCorrectAnswers());

            return questionRepository.save(questionToUpdate);
        }   else {
            throw new NoSuchElementException("Question with id: " + id + " not found");
        }
    }

    @Override
    public List<Question> getAllQuestionsForASubject(Integer numberOfQuestions, String subject) {
//        Pageable pageable = PageRequest.of(0 , 1);  //0 -> first page ; numberOfQuestions -> size of a page
        List<Question> questionList = questionRepository.findAllBySubject(subject);
        Collections.shuffle(questionList);
        int numberOfQuestionsToReturn = Math.min(numberOfQuestions , questionList.size());
        return questionList.subList(0 , numberOfQuestionsToReturn);
    }
}
