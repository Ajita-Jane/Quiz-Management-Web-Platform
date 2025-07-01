package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.boot.model.source.internal.hbm.CompositeIdentifierSingularAttributeSourceManyToOneImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Question;
import com.example.demo.models.Answer;
import com.example.demo.models.QuestionWrapper;
import com.example.demo.models.User;
import com.example.demo.repository.QuestionRepository;

import com.example.demo.services.QuestionService;
 
@RestController
@RequestMapping("api/Questions")
public class QuestionController {
	@Autowired
	private QuestionRepository repository;
	
	
	
	@Autowired
	private QuestionService service;
	public ResponseEntity<String> addQuestion(@RequestBody Question question){
		
		return service.addQuestion(question);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
		return service.getQuestionById(id);
	}
	
	@PostMapping("/Questions")
	public ResponseEntity<String> addQuestions(@RequestBody List<Question> questions)
	{
		return service.addQuestions(questions);
	}
	@GetMapping("/ByCategory/{subject}")
	public ResponseEntity<List<Integer>> getQuestionBySubject(@PathVariable String subject) {
		
		return service.getQuestionBySubject(subject);
		
		
	}
	@GetMapping("/ByCat/{subject}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable String subject) {
	    return service.getQuizQuestions(subject);
	}
	
	@PostMapping("/Answer")
	public ResponseEntity<Integer> getAnswer(@RequestBody List<Answer> anslist) {
		//System.out.println("@@@"+anslist.get(0));
		for(Answer a: anslist) {
			System.out.println(a.getId()+"    "+ a.getAns());
		}
	    return service.getAnswer(anslist);
	}

	@PostMapping("/SaveUser")
	public ResponseEntity<String> saveUserInfo(@RequestBody User userinfo) {
	    return service.saveUserInfo(userinfo);
	}

	


	

}
