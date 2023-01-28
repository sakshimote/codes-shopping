package com.lms.api.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.lms.api.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

	List<Question> findByTopicId(Long id);

/*
@Query("select q  from question q join q.user u where u.id=?1")
List<Question> findByUserId(Long uid);
*/
	

	



	

}
