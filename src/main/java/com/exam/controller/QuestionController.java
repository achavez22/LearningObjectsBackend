package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("quizId") Long quizId ){
//        Quiz quiz = new Quiz();
//        quiz.setqId(quizId);
//        Set<Question> questionSet = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionSet);
        Quiz quiz = this.quizService.getQuiz(quizId);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if(list.size() > Integer.parseInt(quiz.getNumberofQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberofQuestions() + 1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId){
        return this.questionService.getQuestion(quesId);
    }

    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }

    //eval quiz
    @PostMapping("/eval-quiz")
    public  ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
        double marksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;

        for( Question q: questions) {
            //single question
            Question question = this.questionService.get(q.getQuesId());
            if (question.getAnswer().trim().equals(q.getGivenAnswer())) {
                //correct.
                correctAnswers++;
                double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
                marksGot += marksSingle;
            }

            if(q.getGivenAnswer() != null){ //!q.getGivenAnswer().trim().equals("") ||
                attempted++;

            }

        }
        Map<String, Object> map  = Map.of("marksGot",marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
        return ResponseEntity.ok(map);
    }
}
