package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;
import com.example.demo.models.QuestionWrapper;
import com.example.demo.models.User;

@RestController
@RequestMapping("api/quiz")
public class QuizController {

    @Autowired
    private RestTemplate restTemplate;

    private final String QUESTION_SERVICE_URL = "http://localhost:8081/api/Questions";

   
    @GetMapping("/fetch/{subject}")
    public ResponseEntity<List<QuestionWrapper>> fetchQuizQuestions(@PathVariable String subject) {
        String url = QUESTION_SERVICE_URL + "/ByCategory/" + subject;
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        List<Integer> questionIds = response.getBody();

        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        
        for (Integer id : questionIds) {
            String questionUrl = "http://localhost:8081/api/Questions/" + id;
            ResponseEntity<Question> questionResponse = restTemplate.getForEntity(questionUrl, Question.class);
            
            System.out.println("Response Status: " + questionResponse.getStatusCode());
            System.out.println("Response Body: " + questionResponse.getBody());

            if (questionResponse.getStatusCode().is2xxSuccessful()) {
                Question question = questionResponse.getBody();
                
                QuestionWrapper wrapper = new QuestionWrapper();
                wrapper.setId(question.getId());
                wrapper.setContent(question.getContent());
                wrapper.setOptions(question.getOptions());  // Map of options
                questionWrappers.add(wrapper);
            } else {
                System.out.println("Failed to fetch question with ID: " + id);
            }
        }

        return ResponseEntity.ok(questionWrappers);
    }



    
   
    @PostMapping("/submit")
    public ResponseEntity<Integer> submitAnswers(@RequestBody List<Answer> answers) {
       // System.out.println("Received Answers: " + answers);
    	
    	for(Answer a: answers) {
			System.out.println(""+a.getId()+"    "+ a.getAns());
		}
        String url = QUESTION_SERVICE_URL + "/Answer";
        //System.out.println(url);
        try {
            
            ResponseEntity<Integer> response = restTemplate.postForEntity(url, answers, Integer.class);

           // System.out.println("HII");

            if (response.getStatusCode().is2xxSuccessful()) {
            //	System.out.println(response.getBody());
                return ResponseEntity.ok(response.getBody());
            } else {
              //  System.out.println("Failed to submit answers. Response Status: " + response.getStatusCode());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
            }
        } catch (Exception e) {
           // System.out.println("Exception occurred while submitting answers: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    }

    @PostMapping("/SaveUser")
    public ResponseEntity<Integer> submitQuiz(@RequestBody User user) {

        String saveUserUrl = QUESTION_SERVICE_URL + "/SaveUser";

        try {
            ResponseEntity<String> saveResponse = restTemplate.postForEntity(
                saveUserUrl, user, String.class);

            if (!saveResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }

        return ResponseEntity.ok(1); // Just return success response for now
    }




}
