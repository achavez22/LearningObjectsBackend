package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        Quiz newquiz =  this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(newquiz);
    }

    @GetMapping("/{quizId}")
    public Quiz getQuiz(@PathVariable("quizId") Long quizId ){
        return this.quizService.getQuiz(quizId);
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    @PutMapping("/")
    public Quiz updateQuiz(@RequestBody Quiz quiz) {
        return this.quizService.updateQuiz(quiz);
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId){
        this.quizService.deleteQuiz(quizId);
    }


    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid){
        Category category = new Category();
        category.setCid(cid);

        return this.quizService.getQuizzesOfCatgory(category);
    }

    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }

    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid){
        Category category = new Category();
        category.setCid(cid);

        return this.quizService.getActiveQuizzesOfCatgory(category);
    }
}
