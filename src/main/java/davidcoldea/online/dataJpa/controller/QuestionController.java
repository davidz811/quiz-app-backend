package davidcoldea.online.dataJpa.controller;

import davidcoldea.online.dataJpa.model.Question;
import davidcoldea.online.dataJpa.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("")
    ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.createQuestion(question) , HttpStatus.CREATED);
    }

    @GetMapping("/questions")
    ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questionList = questionService.getAllQuestions();
        return ResponseEntity.ok(questionList);
    }

    @GetMapping("/questions/{id}")
    ResponseEntity<Question> findQuestionById(@PathVariable Long id) {
        Question question = questionService.findQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/subjects")
    ResponseEntity<List<String>> getAllSubjects() {
        List<String> subjects = questionService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @DeleteMapping("/questions/{id}")
    ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Question deleted successfully");
    }

    @PutMapping("/questions/{id}")
    ResponseEntity<Question> updateQuestion(@PathVariable Long id , @RequestBody Question question ) {
        Question questionToUpdate = questionService.updateQuestion(id, question);
        return ResponseEntity.ok(questionToUpdate);
    }

    @GetMapping("/questions/subject")
    ResponseEntity<List<Question>> getAllQuestionsForASubject(@RequestParam Integer numberOfQuestions , @RequestParam String subject) {
        List<Question> allQuestionsList = questionService.getAllQuestionsForASubject(numberOfQuestions, subject);
        return ResponseEntity.ok(allQuestionsList);
    }

    //testing RequestParam
    @GetMapping("/test")
    public String tryName(@RequestParam String name , @RequestParam Integer age) {
        return "My name is " + name + " and I am " + age;
    }
}
