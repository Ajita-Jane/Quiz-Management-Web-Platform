package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;
import com.example.demo.models.QuestionWrapper;
import com.example.demo.models.User;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;

@Service
public class QuestionService {
	@Autowired 
	private QuestionRepository repository;
	
	@Autowired
	private UserRepository quizRepository;
	
	public ResponseEntity<String> addQuestion(Question question){
		repository.save(question);
		return new ResponseEntity<>("Record Added Successfully",HttpStatus.OK);
	}
	public ResponseEntity<Question> getQuestionById(int id) {
		
		System.out.println(id);
		return new ResponseEntity<>(repository.findById(id).get(),HttpStatus.OK);	
	}
	
	public ResponseEntity<String> addQuestions( List<Question> questions)
	{
		for(Question q:questions) {
			repository.save(q);
		}
		return new ResponseEntity<>("Record Added Successfully",HttpStatus.OK);
	}

	public ResponseEntity<List<Integer>> getQuestionBySubject(String subject) {
		
		List<Question> sq =repository.findBySubject(subject);
		ArrayList <Integer> QuizQuestions= new ArrayList <Integer>();
		for(Question q: sq) {
			QuizQuestions.add(q.id);
			
		}
		
		return new ResponseEntity<>(QuizQuestions,HttpStatus.OK);
		
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestParam String subject) {
		
		
        List<Integer> questionIds = getQuestionBySubject(subject).getBody();
        List<QuestionWrapper> quizQuestions = new ArrayList<QuestionWrapper>();
        for(Integer q: questionIds) {
        	QuestionWrapper wrapper= new QuestionWrapper();
        	Question ques= getQuestionById(q).getBody();
        	wrapper.id=ques.id;
        	wrapper.content=ques.content;
        	wrapper.options=ques.options;
        	quizQuestions.add(wrapper);
        	
        }
        return ResponseEntity.ok(quizQuestions);
    }
	
	public ResponseEntity<Integer> getAnswer(List<Answer> anslist) {
	    int marks = 0; // Initialize marks counter

	    for (Answer answer : anslist) {
	    //	System.out.println("From Quiz: "+answer.getId()+ "    "+ answer.getAns());
	        // Fetch the Question object using the repository
	        Question ques = repository.findById(answer.getId()).orElse(null);
	       // System.out.println(ques.id +"   "+ques.getCorrectanswer());

	        // Check if the Question object is null before accessing its methods
	        if (ques != null && answer.getAns().equals(ques.getCorrectanswer())) {
	            marks++; // Increment marks for correct answer
	        } else if (ques == null) {
	         //   System.out.println("Question object not found for ID: " + answer.getId());
	        }
	    }

	    return ResponseEntity.ok(marks); // Return the total marks
	}

	

	public ResponseEntity<String> saveUserInfo(User userinfo) {
	    quizRepository.save(userinfo);
	    return new ResponseEntity<>("User info list saved successfully", HttpStatus.OK);
	}






	

	
}
