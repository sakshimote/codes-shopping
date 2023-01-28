package com.lms.api.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.AnswerDto;
import com.lms.api.dto.AnswerDto.TopicStats;
import com.lms.api.dto.PaginationDto;
import com.lms.api.dto.TopicDto;
import com.lms.api.dto.TopicDto.questionDto;
import com.lms.api.model.Answer;
import com.lms.api.model.Question;
import com.lms.api.model.Topic;
import com.lms.api.model.User;
import com.lms.api.repository.AnswerRepository;
import com.lms.api.repository.QuestionRepository;
import com.lms.api.repository.TopicRepository;
import com.lms.api.repository.UserRepository;


@RestController
public class TopicController {
	@Autowired
	private TopicRepository topicRepoitory;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private UserRepository userRepository;

	/*
	 * post API for topic
	 */
	@PostMapping("/topic/insert")
	public Topic postTopic(@RequestBody Topic topic) {
		return topicRepoitory.save(topic);
	}
	
	/*
	 * 7. POST api for adding question. 
	 * 
	 */
	@PostMapping("/question/insert")
	public Question postQuestion(@RequestBody Question question,Principal principal) {

		question.setDateOfPost(LocalDate.now());
		question.setUsername(principal.getName());
		return questionRepository.save(question);
		
	}
	/*
	 * 6. POST api for answer
	 */
	@PostMapping("/answer/insert")
	public Answer postAnswerToQuestion(@RequestBody Answer answer
							,Principal principal) {
		
		answer.setDateOfPost(LocalDate.now());
		
		answer.setUsername(principal.getName());
		return answerRepository.save(answer);
		
	}
	/*
	 * 2. Add sample data in relationship table(s), topic_question & question_answer. 
	 * assign q to topic
	 */
	@PostMapping("/topic/question/{tid}/{qid}")
	public Topic assignQuestionToTopic(@PathVariable("tid")Long tid,@PathVariable("qid")Long qid) {
		
		Topic topic=topicRepoitory.getById(tid);
		Question question=questionRepository.getById(qid);
		
		List<Question> questions=topic.getQuestion();
		questions.add(question);
		topic.setQuestion(questions);
		return topicRepoitory.save(topic);
	}
	/*
	 * 2. Add sample data in relationship table(s), topic_question & question_answer. 
	 * assign answer to q
	 */
	
	@PostMapping("/question/answer/{qid}/{aid}")
	public Question assignAnswerToQuestion(@PathVariable("qid")Long qid,@PathVariable("aid")Long aid) {
		
		Question question=questionRepository.getById(qid);
		Answer answer=answerRepository.getById(aid);
		
		List<Answer> answers=question.getAnswer();
		answers.add(answer);
		question.setAnswer(answers);
		return questionRepository.save(question);
	}
	
	/*
	 *3. Create an API to display all the Topic records(id&topics) in paged manner. 
	 *Also add number of questions available in the DB for each topic. 
	 */
	@GetMapping("/topic-records")
	public List<PaginationDto> getTopicRecords(@RequestParam(name="page",required = false,defaultValue = "0")Integer page,
			@RequestParam(name="size",required = false,defaultValue = "2")Integer size) {
		
		Pageable pageable=PageRequest.of(page,size);
		List<Topic> topicList=topicRepoitory.findAll(pageable).getContent();
		
		List<PaginationDto> dtoList=new ArrayList<>();
		
		for(Topic t: topicList) {
			
			PaginationDto dto=new PaginationDto();
			dto.setNumberOfQuestions(t.getQuestion().size());
			dto.setTopicId(t.getId());
			dto.setTopicname(t.getTopic());
			dtoList.add(dto);
		}
		return dtoList;
	}

	/*
	 *4. Create a GET API, to display list of Questions based on topicID. 
	 */
	@GetMapping("/question/{tid}")
	public TopicDto getQuestionByTopicId(@PathVariable("tid")Long tid) {
		Topic topic=topicRepoitory.getById(tid);
		List<Question> questionList=questionRepository.findByTopicId(tid);
		List<Question> sortedQList=questionList.stream().sorted(Comparator.comparing(Question::getDateOfPost)
				.reversed()).collect(Collectors.toList());
		TopicDto dto=new TopicDto();
		
		List<TopicDto.questionDto> topicQuestionList=new ArrayList<>();
		
		sortedQList.stream().forEach(q->{
			TopicDto.questionDto qdto=new questionDto();
			List<Answer> listAnswers=answerRepository.findByQuestionId(q.getId());
			qdto.setId(q.getId());
			qdto.setText(q.getQuestion_text());
			qdto.setLikes(q.getLikes());
			qdto.setUsername(q.getUsername());
			qdto.setNumberOfAnswers(listAnswers.size());
			topicQuestionList.add(qdto);
		});
		dto.setTopicId(tid);
		dto.setTopicname(topic.getTopic());
		dto.setQuestions(topicQuestionList);
		dto.setNumberOfQuestions(questionList.size());
		return dto;
	}
	/*
	5. GET api for List of Answers based on questionId having following response format
	*/
	@GetMapping("/answers/{qid}")
	public AnswerDto getAnswersByQuestionId(@PathVariable("qid")Long qid) {
		List<Answer> ansList=answerRepository.findByQuestionId(qid);
		List<Answer> sortedAList=ansList.stream().sorted(Comparator.comparing(Answer::getDateOfPost)
				.reversed()).collect(Collectors.toList());
		Question question=questionRepository.getById(qid);
			AnswerDto dto=new AnswerDto();
			dto.setQuestionId(qid);
			dto.setQuestionText(question.getQuestion_text());
			dto.setqUsername(question.getUsername());
			dto.setDateOfPostQ(question.getDateOfPost());
			dto.setNumberOfAnswers(question.getAnswer().size());
			dto.setAnswers(sortedAList);
			dto.setLikes(question.getLikes());
		return dto;
	}
	/*
	 * 8. PUT api(s) for incrementing the Likes of the question & answer based on their IDs.
	 */
	
	@PutMapping("/question/likes/{qid}")
	public Question incrementLikesQ(@PathVariable("qid")Long qid) {
		Question q=questionRepository.getById(qid);
		if(q.getLikes()==0) {
			int increment=q.getLikes()+1;
			q.setLikes(increment);
		}else if(q.getLikes()>=1) {
			int i=q.getLikes()+1;
			q.setLikes(i);
		}
		return questionRepository.save(q);
	}
	
	@PutMapping("/answer/likes/{aid}")
	public Answer incrementLikesA(@PathVariable("aid")Long aid) {
		Answer a=answerRepository.getById(aid);
		if(a.getLikes()==0) {
			int increment=a.getLikes()+1;
			a.setLikes(increment);
		}else if(a.getLikes()>=1) {
			int i=a.getLikes()+1;
			a.setLikes(i);
		}
		return answerRepository.save(a);
	}
	
	/*
	 * 9. PUT api for question and answer. 
	 * Only those Users who have posted these questions and answers must be able to edit. 
	 */
	@PutMapping("answer/update/{aid}")
	public Answer updateAnswer(@PathVariable("aid")Long aid,@RequestBody Answer answer,
			Principal principal) {
		Answer a=answerRepository.getById(aid);
		if(!principal.getName().equals(a.getUsername())) {
			throw new RuntimeException("updation not allowed for this user");
		}
		
		if(answer.getAnswer_text()!=null) {
			a.setAnswer_text(answer.getAnswer_text());
		}
		return answerRepository.save(a);	
	}
	
	@PutMapping("question/update/{qid}")
	public Question updateQuestion(@PathVariable("qid")Long qid,@RequestBody Question question,
			Principal principal) {
		Question q=questionRepository.getById(qid);
	
		if(!principal.getName().equals(q.getUsername())) {
			throw new RuntimeException("updation not allowed for this user");
		}
		if(question.getQuestion_text()!=null) {
			q.setQuestion_text(question.getQuestion_text());
		}
		return questionRepository.save(q);	
	}
	
	
	/*
	 * 10. Topic stats API
	 */
	@GetMapping("/topic-stats")
	public TopicStats topicStats() {
		List<Topic> topics=topicRepoitory.findAll();
		List<Answer> answers=answerRepository.findAll();
		List<Question> questions=questionRepository.findAll();
	
		
		AnswerDto.TopicStats dto=new TopicStats();
		dto.setTotalNoTopics(topics.size());
		dto.setTotalNoOfAnswers(answers.size());
		dto.setTotalNoOfQuestions(questions.size());
		
		
		List<String> qUsernames=questions.stream().map(q->q.getUsername()).collect(Collectors.toList());
		List<String> ansUsernames=answers.stream().map(a->a.getUsername()).collect(Collectors.toList());
		
		TreeSet<String> userNames=new TreeSet<>();
		
		for(String q:qUsernames) {
			userNames.add(q);
		}
		for(String a:ansUsernames) {
			userNames.add(a);
		}
		dto.setTotalNoOfUsers(userNames.size());
		return dto;
		
	}

	@GetMapping("/question/user/{uid}")
	public List<Question> getQuestionByUserId(@PathVariable("uid")Long uid) {
		User user=userRepository.getById(uid);
		List<Question> questions=questionRepository.findAll();
		
		List<Question> questionsByUid=questions.stream()
				.filter(q->q.getUsername().equals(user.getUsername())).collect(Collectors.toList());
		return questionsByUid;	
	}

}
	


