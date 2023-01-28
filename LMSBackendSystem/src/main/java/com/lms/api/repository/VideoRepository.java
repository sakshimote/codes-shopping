package com.lms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.api.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

	List<Video> getByModuleId(Long id);
@Query("select v from Video v join v.module m join m.course c where c.id=?1")
	List<Video> getByCourseId(Long cid);

}
